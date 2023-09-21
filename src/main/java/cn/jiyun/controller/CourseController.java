package cn.jiyun.controller;

import cn.jiyun.pojo.Course;
import cn.jiyun.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("findCourses")
    public List<Course> findCourses(){
        return courseService.findCourses();
    }
}
