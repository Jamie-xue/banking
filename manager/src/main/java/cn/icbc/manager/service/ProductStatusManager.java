package cn.icbc.manager.service;

import cn.icbc.api.events.ProductStatusEvent;
import cn.icbc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: asus
 * @Date: 2018/8/27 11:06
 */
@Component
public class ProductStatusManager {

    private static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

    private static Logger LOG = LoggerFactory.getLogger(ProductStatusManager.class);


    @Autowired
    private JmsTemplate jmsTemplate;

    public void  changeStatus(String id, ProductStatus status){
        ProductStatusEvent event = new ProductStatusEvent(id, status);
        LOG.info("MQ发送消息:{}",event);
        jmsTemplate.convertAndSend(MQ_DESTINATION,event);
    }

    //@PostConstruct
    public void init(){
        changeStatus("8",ProductStatus.IN_SELL);
    }
}
