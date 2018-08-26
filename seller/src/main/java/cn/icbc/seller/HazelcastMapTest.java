package cn.icbc.seller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Auther: asus
 * @Date: 2018/8/26 19:20
 */
@Component
public class HazelcastMapTest {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void put(){
        Map map = hazelcastInstance.getMap("itcast");
        map.put("name","Li");

    }
}
