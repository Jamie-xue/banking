package cn.icbc.seller.service;

import cn.icbc.entity.Order;
import cn.icbc.entity.Product;
import cn.icbc.entity.enums.OrderStatus;
import cn.icbc.entity.enums.OrderType;
import cn.icbc.seller.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: asus
 * @Date: 2018/8/27 13:46
 */
@Service
public class OrderService {


    private static Logger LOG = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRpcService productRpcService;


    public Order apply(Order order){

        checkOrder(order);


        addOther(order);


        LOG.debug("订单信息:{}",order);
        return orderRepository.save(order);
    }

    private void addOther(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replace("-",""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
    }

    private void checkOrder(Order order) {
        Assert.notNull(order.getOuterOrderId(),"需要外部订单编号");
        Assert.notNull(order.getChanId(),"需要渠道编号");
        Assert.notNull(order.getChanUserId(),"需要用户编号");
        Assert.notNull(order.getProductId(),"需要产品编号");
        Assert.notNull(order.getAmount(),"需要购买金额");
        Assert.notNull(order.getCreateAt(),"需要订单时间");

        Product onePro = productRpcService.findOnePro(order.getProductId());

        Assert.notNull(onePro,"产品不存在");

        Assert.isTrue(onePro.getThresholdAmount().compareTo(order.getAmount())<=0,"小于最低购买金额");

        Assert.isTrue(isRight(order.getAmount(),onePro.getStepAmount(),onePro.getThresholdAmount()),"投资金额没有按步长的整数倍增加");
    }


    public boolean isRight(BigDecimal amount, BigDecimal stepAmount, BigDecimal thresholdAmount){

        double amountDou = amount.doubleValue();
        double stepAmountDou = stepAmount.doubleValue();
        double thresholdAmountDou = thresholdAmount.doubleValue();
        double result =(amountDou-thresholdAmountDou)/stepAmountDou;

        String resultStr = String.valueOf(result);
        char[] chars = resultStr.toCharArray();

        return 0==(Integer.parseInt(chars[chars.length-1]+""));
    }

    public List<Order> findAllOrder() {
       return orderRepository.findAll();
    }
}
