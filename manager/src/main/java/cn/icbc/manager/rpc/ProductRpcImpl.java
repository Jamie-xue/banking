package cn.icbc.manager.rpc;

import cn.icbc.api.ProductRpc;
import cn.icbc.api.domain.ProductRpcReq;
import cn.icbc.entity.Product;
import cn.icbc.manager.service.ProductService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/25 22:55
 */
@Service
@AutoJsonRpcServiceImpl
public class ProductRpcImpl implements ProductRpc {

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        LOG.info("查询请求，参数:{}",req);
        List<String> idList = req.getIdList();
        List<String> statusList = req.getStatusList();
        BigDecimal minRewardRate = req.getMinRewardRate();
        BigDecimal maxRewardRate = req.getMaxRewardRate();

        Pageable pageable = new PageRequest(req.getPageNum(),req.getPageSize());
        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);
        LOG.info("查询请求page，结果:{}",page);
        LOG.info("查询请求page.getContent，结果:{}",page.getContent());
        return page.getContent();
    }

    @Override
    public Product findOne(String id) {
        LOG.info("查询findOne请求，id:{}",id);
        Product result = productService.findOnePro(id);
        LOG.info("查询findOne结果:{}",result);
        return result;
    }
}
