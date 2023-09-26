package cn.jiyun.controller;

import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("stu")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Value("${upload.path}")
    private String path;

    @RequestMapping("findAllStu")
    public PageInfo findAllStu(Integer pageNum
            , Integer pageSize
            , @RequestBody Student student
    ) {
        Page<Student> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentService.findAllStu(student);
        System.out.println("--------------------------------"+list);
        return new PageInfo(list);
    }

    @RequestMapping("addStudent")
    public boolean addStudent(@RequestBody Student student) {

        return studentService.addStu(student);
    }

    @RequestMapping("deleted")
    public Boolean deleted(Integer id) {
        return studentService.deleted(id);
    }

    @RequestMapping("deletedList")
    public boolean deletedList(Integer[] ids) {
        return studentService.deletedList(Arrays.asList(ids));
    }

    @RequestMapping("upload")
    public String uploadString(MultipartFile file) {
        try {
            if (file != null && file.getSize() > 0) {
                String filename = file.getOriginalFilename();
                String img = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));

                file.transferTo(new File(path + img));
                return "http://localhost:8080/images/" + img;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    @RequestMapping("getCount")
    public Map<String,Object> getCount(){
        return studentService.getCount();
    }
}
