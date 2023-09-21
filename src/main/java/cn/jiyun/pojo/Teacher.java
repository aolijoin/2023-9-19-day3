package cn.jiyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_teacher")
public class Teacher implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;
    public String name;
    public Integer cid;
}
