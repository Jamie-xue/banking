package cn.icbc.saller.service;

import cn.icbc.api.ProductRpc;
import cn.icbc.api.domain.ProductRpcReq;
import cn.icbc.entity.Product;
import cn.icbc.entity.enums.ProductStatus;
import cn.icbc.saller.configuration.RpcConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/26 10:06
 */
@Service
public class ProductRpcService {

    @Resource
    private ProductRpc productRpc;


    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    public void findAll(){
        //List<String> idList, List<String> statusList, BigDecimal minRewardRate, BigDecimal maxRewardRate, Integer pageNum, Integer pageSize
        List<String> idList=new ArrayList<>();
        List<String> statusList= new ArrayList<>();
        statusList.add(ProductStatus.IN_SELL.getDesc());

        ProductRpcReq req = new ProductRpcReq();
        req.setStatusList(statusList);
        req.setPageNum(0);
        req.setPageSize(100);
        LOG.info("调用rpc服务productList请求：{}",req);
        List<Product> productList = productRpc.query(req);
        LOG.info("调用rpc服务productList结果：{}",productList);
        //return productList;
    }

    @PostConstruct
    public void testProductRpcService(){
        findAll();
    }

}
