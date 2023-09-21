package cn.jiyun.service;

import cn.jiyun.pojo.Student;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有
     * @param student 查询到的对象
     * @return 返回查询到的集合
     */
    List<Student> findAllStu(Student student);

    /**
     * 添加或修改
     *
     * @param student 添加或修改的对象
     * @return 返回true 表示操作成功 false 表示操作失败
     */
    boolean addStu(Student student);

    /**
     * 删除
     *
     * @param id 条件 id 主键
     * @return 返回true 表示操作成功 false 表示操作失败
     */

    Boolean deleted(Integer id);

    /**
     * 批量删除
     *
     * @param ids 条件
     * @return 返回true 表示操作成功 false 表示操作失败
     */
    boolean deletedList(List<Integer> ids);
}
