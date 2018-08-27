package cn.icbc.seller.controller;

import cn.icbc.entity.Order;
import cn.icbc.seller.params.OrderParams;
import cn.icbc.seller.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParams params){

        LOGGER.info("申购参数：{}",params);
        Order order = new Order();
        BeanUtils.copyProperties(params,order);
       return orderService.apply(order);
    }

    @GetMapping("/orders")
    public List<Order> findAllOrder(){
      return   orderService.findAllOrder();
    }
}
