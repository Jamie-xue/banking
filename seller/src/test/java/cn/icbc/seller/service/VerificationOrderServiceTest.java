package cn.icbc.seller.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @Auther: asus
 * @Date: 2018/8/27 22:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerificationOrderServiceTest {


    @Autowired
    private  VerificationOrderService verificationOrderService;
    //@Test
    public void  fun(){
        Date day = new GregorianCalendar(2018, 7, 26).getTime();
        File verificationFile = verificationOrderService.createVerificationFile("3", day);
        System.out.println(verificationFile.getAbsolutePath());

    }
}