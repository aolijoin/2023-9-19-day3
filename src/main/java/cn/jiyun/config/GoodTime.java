package cn.jiyun.config;

import cn.jiyun.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GoodTime {
    @Autowired
    StudentMapper studentMapper;

    @Scheduled(cron = "0/10 * * * * ?")
    public void aVoid() {
        System.out.println(new Date()+"\n------------------------------------------*");
    }

}
