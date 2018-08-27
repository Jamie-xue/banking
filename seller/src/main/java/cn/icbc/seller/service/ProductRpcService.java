package cn.icbc.seller.service;

import cn.icbc.api.ProductRpc;
import cn.icbc.api.domain.ProductRpcReq;
import cn.icbc.api.events.ProductStatusEvent;
import cn.icbc.entity.Product;
import cn.icbc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/26 10:06
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private ProductCache productCache;


    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    private static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";



    public List<Product> findAll() {
        return productCache.findAllCache();
    }

    public Product findOnePro(String id) {

        Product onePro = productCache.findOnePro(id);
        if (onePro == null) {
            productCache.removeCache(id);
        }

        return onePro;
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<Product> products = findAll();
        LOG.info("Product全部进入缓存,{}",products);
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }


    @JmsListener(destination = MQ_DESTINATION)
    void updateCache(ProductStatusEvent event){

        LOG.info("MQ接收消息:{}",event);
        productCache.removeCache(event.getId());

        LOG.info("-----{}",ProductStatus.IN_SELL.name());
        LOG.info("====={}",event.getStatus());

        if (ProductStatus.IN_SELL.equals(event.getStatus())){
            LOG.info("更新:{}",event.getId());
            productCache.findOnePro(event.getId());
        }

    }


/*    @PostConstruct
    public void testProductRpcService(){
       findAll();
       findOnePro();
    }*/

}
