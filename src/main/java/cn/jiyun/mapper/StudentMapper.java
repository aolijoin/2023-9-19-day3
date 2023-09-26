package cn.jiyun.mapper;

import cn.jiyun.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {
    List<Student> selectAllStu(Student student);

    List<Map<String, Object>> getCount();
}
