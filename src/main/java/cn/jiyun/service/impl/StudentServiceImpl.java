package cn.jiyun.service.impl;

import cn.jiyun.mapper.StudentMapper;
import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

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
        try {
            if (student.getId() == null) {
                studentMapper.insert(student);
            } else {
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
}
