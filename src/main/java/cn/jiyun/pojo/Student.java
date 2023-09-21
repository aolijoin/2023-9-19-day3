package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("tb_student")
public class Student implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer sex;
    private Integer age;
    private Date birthday;
    private String hobby;
    @TableField(value = "img")
    private String imgUrl;
    private Integer tid;
    private Integer del;

    @TableField(exist = false)
    public String teacher;
    @TableField(exist = false)
    public String course;

    @TableField(exist = false)
    public Integer cid;

    @TableField(exist = false)
    public String ageStr;

    @TableField(exist = false)
    public Date startTime;
    @TableField(exist = false)
    public Date endTime;
    @TableField(exist = false)
    public Integer startAge;
    @TableField(exist = false)
    public Integer endAge;
}
