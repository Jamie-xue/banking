package cn.icbc.manager.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: asus
 * @Date: 2018/8/27 23:42
 */
@Component
public class Scheduling {
    @Scheduled(cron = "0/3 * * * * ? ")
    public void sayHello(){

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        System.out.println("Hello Scheduling..................."+format);
    }

}
