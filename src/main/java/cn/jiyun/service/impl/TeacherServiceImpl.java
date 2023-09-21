package cn.jiyun.service.impl;

import cn.jiyun.mapper.TeacherMapper;
import cn.jiyun.pojo.Teacher;
import cn.jiyun.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public List<Teacher> findTeachers() {
        return teacherMapper.selectList(null);
    }
    @Autowired
    private TeacherMapper teacherMapper;
}
