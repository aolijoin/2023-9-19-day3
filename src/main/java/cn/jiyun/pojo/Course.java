package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_course")
public class Course implements Serializable {
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    private String name;
}
