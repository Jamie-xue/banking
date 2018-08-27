package cn.icbc.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Auther: asus
 * @Date: 2018/8/26 09:53
 */

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableCaching
@EntityScan(basePackages = "cn.icbc.entity")
@SpringBootApplication
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class,args);
    }
}
