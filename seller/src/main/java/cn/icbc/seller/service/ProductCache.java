package cn.icbc.seller.service;

import cn.icbc.api.ProductRpc;
import cn.icbc.api.domain.ProductRpcReq;
import cn.icbc.entity.Product;
import cn.icbc.entity.enums.ProductStatus;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: asus
 * @Date: 2018/8/26 21:29
 */
@Component
public class ProductCache {


    static final String CACHE_NAME = "itcast_product";

    private static Logger LOG = LoggerFactory.getLogger(ProductRpc.class);


    @Resource
    private ProductRpc productRpc;

    @Resource
    private HazelcastInstance hazelcastInstance;



    public List<Product> findAllCache(){
        Map map =hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (map.size()>0){
            products= new ArrayList<>();
            products.addAll(map.values());
        }else{
            products=findAll();
        }
        return products;
    }





    public List<Product> findAll() {
        //List<String> idList, List<String> statusList, BigDecimal minRewardRate, BigDecimal maxRewardRate, Integer pageNum, Integer pageSize
        List<String> idList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        statusList.add(ProductStatus.IN_SELL.name());

        ProductRpcReq req = new ProductRpcReq();
        req.setStatusList(statusList);
        req.setPageNum(0);
        req.setPageSize(100);
        LOG.info("调用rpc服务productList请求：{}", req);
        List<Product> productList = productRpc.query(req);
        LOG.info("调用rpc服务productList结果：{}", productList);
        return productList;
    }


    @Cacheable(cacheNames = CACHE_NAME)
    public Product findOnePro(String id) {
        //id = "1";
        LOG.info("调用rpc服务findOnePro请求：{}", id);
        Product one = productRpc.findOne(id);
        LOG.info("调用rpc服务findOnePro结果：{}", one);
        return one;
    }

    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {

    }

    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public Product putCache(Product product) {

        return product;
    }
}
