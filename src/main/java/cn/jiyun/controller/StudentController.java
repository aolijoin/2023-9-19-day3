package cn.jiyun.controller;

import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("stu")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("findAllStu")
    public PageInfo findAllStu( Integer pageNum
            ,  Integer pageSize
            , @RequestBody Student student
    ) {
        PageHelper.startPage(pageNum,pageSize);

        List<Student> list = studentService.findAllStu(student);
        return new PageInfo(list);
    }
    @RequestMapping("addStudent")
    public boolean addStudent(@RequestBody Student student){
     return studentService.addStu(student);
    }

    @RequestMapping("deleted")
    public Boolean deleted(Integer id){
        return studentService.deleted(id);
    }
}
