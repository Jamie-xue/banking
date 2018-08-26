package cn.icbc.seller.service;

import cn.icbc.api.ProductRpc;
import cn.icbc.api.domain.ProductRpcReq;
import cn.icbc.entity.Product;
import cn.icbc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }


/*    @PostConstruct
    public void testProductRpcService(){
       findAll();
       findOnePro();
    }*/

}
