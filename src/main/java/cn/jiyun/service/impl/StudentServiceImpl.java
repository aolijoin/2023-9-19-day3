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

    @Override
    public List<Student> findAllStu(Student student) {
        if (StringUtils.isNoneBlank(student.getAgeStr())) {
            String[] split = student.getAgeStr().split("-");
            student.setStartAge(Integer.parseInt(split[0]));
            student.setEndAge(Integer.parseInt(split[1]));
        }
        return studentMapper.selectAllStu(student);
    }

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

    @Override
    public Boolean deleted(Integer id) {
        try {
            Student student = studentMapper.selectById(id);
            student.setDel(1);
            studentMapper.updateById(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
