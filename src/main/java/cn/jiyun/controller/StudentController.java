package cn.jiyun.controller;

import cn.jiyun.pojo.Student;
import cn.jiyun.service.StudentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("stu")
public class StudentController {
    @Autowired
    private StudentService studentService;

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
        return new PageInfo(list);
    }

    @RequestMapping("addStudent")
    public boolean addStudent(@RequestBody Student student, MultipartFile img, HttpSession session) throws IOException {
        if (img != null && img.getSize() > 0) {
            String path = session.getServletContext().getRealPath("/images");
            File dir = new File(path);
            if (dir.exists()) {
                dir.mkdirs();
            }
            String filename = img.getOriginalFilename();
            img.transferTo(new File(dir + filename));
            student.setImgUrl("../images/" + filename);
        }
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
}
