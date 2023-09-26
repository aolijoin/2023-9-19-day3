
package cn.jiyun;

import cn.jiyun.mapper.CourseMapper;
import cn.jiyun.pojo.Course;
import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StudentTest {
    @Autowired
    private CourseMapper mapper;
    @Autowired
    private StudentService studentService;

    @Test
    public void course() {
        List<Course> courses = mapper.selectList(null);
        courses.forEach(System.out::println);
    }

    @Test
    public void student() {
//        Student student = new Student();
//        studentService.findAllStu(student).forEach(System.out::println);
    }

}
