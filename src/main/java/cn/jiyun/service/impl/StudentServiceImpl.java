package cn.jiyun.service.impl;

import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有
     *
     * @param student 查询到的对象
     * @return 返回查询到的集合
     */
    @Override
    public List<Student> findAllStu(Student student) {
        if (StringUtils.isNoneBlank(student.getAgeStr())) {
            String[] split = student.getAgeStr().split("-");
            student.setStartAge(Integer.parseInt(split[0]));
            student.setEndAge(Integer.parseInt(split[1]));
        }
//        studentMapper.selectPage()
        return studentMapper.selectAllStu(student);
    }

    /**
     * 添加或修改
     *
     * @param student 添加或修改的对象
     * @return 返回true 表示操作成功 false 表示操作失败
     */
    @Override
    public boolean addStu(Student student) {
        log.info("添加学生：{}");
        try {
            if (student.getId() == null) {
                studentMapper.insert(student);
                redisTemplate.boundListOps("list").leftPush(student);//操作List
                redisTemplate.boundHashOps("hash").put("" + student.getId(), student);//操作hash
                redisTemplate.boundSetOps("set").add(student);//操作redis的Set
                redisTemplate.boundZSetOps("ZSet").add(student, 100);//操作redis的ZSet

            } else {
//操作redis的ZSet
                Set<Student> zSet = redisTemplate.boundZSetOps("ZSet").range(0, -1);
                for (Student stuZSet : zSet) {
                    if ((int) stuZSet.getId() == student.getId()) {
                        redisTemplate.boundZSetOps("ZSet").remove(stuZSet);
                        redisTemplate.boundZSetOps("ZSet").add(student, 100);
                    }
                }

//操作redis的Set
                Set<Student> set = redisTemplate.boundSetOps("set").members();
                for (Student stuSet : set) {
                    if ((int) stuSet.getId() == student.getId()) {
                        redisTemplate.boundSetOps("set").remove(stuSet);
                        redisTemplate.boundSetOps("set").add(student);
                    }
                }
                //操作hash
                redisTemplate.boundHashOps("hash").put("" + student.getId(), student);
                //操作list
                List<Student> list = redisTemplate.boundListOps("list").range(0, -1);
                for (int i = 0; i < list.size(); i++) {
                    Student stu = list.get(i);
                    if ((int) stu.getId() == student.getId()) {
                        redisTemplate.boundListOps("list").set(i, student);
                    }
                }
//                //使用MyBatisPlus的修改
                studentMapper.updateById(student);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除
     *
     * @param id 条件 id 主键
     * @return 返回true 表示操作成功 false 表示操作失败
     */
    @Override
    public Boolean deleted(Integer id) {
        try {
            //操作redis的ZSet
            Set<Student> zSet = redisTemplate.boundZSetOps("ZSet").range(0, -1);
            for (Student stuZSet : zSet) {
                if ((int) stuZSet.getId() == id) {
                    redisTemplate.boundZSetOps("ZSet").remove(stuZSet);
                }
            }
//redis删除Set
            Set<Student> set = redisTemplate.boundSetOps("set").members();
            for (Student stuSet : set) {
                if ((int) stuSet.getId() == id) {
                    redisTemplate.boundSetOps("set").remove(stuSet);
                }
            }
            //操作hash
            redisTemplate.boundHashOps("hash").delete("" + id);
            //操作list
            List<Student> list = redisTemplate.boundListOps("list").range(0, -1);
            for (int i = 0; i < list.size(); i++) {
                if ((int) list.get(i).getId() == id) {
                    redisTemplate.boundListOps("list").remove(i, list.get(i));
                }
            }
            //使用MyBatisPlus的逻辑删除 自动识别逻辑删除字段
            studentMapper.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * * 批量删除
     *
     * @param ids 条件
     * @return 返回true 表示操作成功 false 表示操作失败
     */
    @Override
    public boolean deletedList(List<Integer> ids) {
        try {
            studentMapper.deleteBatchIds(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, Object> getCount() {
        Map<String, Object> map = new HashMap<>();
//        map.put("success", true);
        List<Map<String, Object>> list = studentMapper.getCount();

        map.put("list", list);


        return map;
    }
}
