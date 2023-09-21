package cn.jiyun.service;

import cn.jiyun.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllStu(Student student);

    boolean addStu(Student student);

    Boolean deleted(Integer id);
}
