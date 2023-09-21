package cn.jiyun.service.impl;

import cn.jiyun.mapper.CourseMapper;
import cn.jiyun.pojo.Course;
import cn.jiyun.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper mapper;

    @Override
    public List<Course> findCourses() {
        return mapper.selectList(null);
    }
}
