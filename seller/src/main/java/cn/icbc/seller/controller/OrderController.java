package cn.icbc.seller.controller;

import cn.icbc.entity.Order;
import cn.icbc.seller.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/27 13:44
 */
@RestController
public class OrderController {

    private static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private OrderService orderService;
    @PostMapping("/order/apply")
    public Order apply(@RequestBody Order order){
        LOGGER.info("申购参数：{}",order);
       return orderService.apply(order);
    }

    @GetMapping("/orders")
    public List<Order> findAllOrder(){
      return   orderService.findAllOrder();
    }
}
