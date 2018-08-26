package cn.icbc.manager.controller;

import cn.icbc.entity.Product;
import cn.icbc.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public Product addPro(@RequestBody Product product) {

        LOGGER.info("创建产品,参数:{}", product);
        Product pro = productService.saveProduct(product);
        LOGGER.info("创建产品,结果:{}", pro);


        return pro;
    }

    @GetMapping("/products")
    public List<Product> getAllPro() {
        return productService.findAllPro();
    }

    @GetMapping("/product/{id}")
    public Product getAllPro(@PathVariable String id) {
        return productService.findOnePro(id);
    }


    @GetMapping("/products/query")
    public Page<Product> query(String ids,String status,BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               @RequestParam(defaultValue = "0") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize){

        List<String> idList=null;
        List<String> statusList=null;
        if(!StringUtils.isEmpty(ids)){
            idList= Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)){
            statusList=Arrays.asList(status.split(","));
        }

        Pageable pageable=new PageRequest(pageNum, pageSize);
        Page<Product> page = productService.query(
                idList, minRewardRate, maxRewardRate, statusList,pageable);
        LOGGER.info("查询产品,结果={}",page);
        return page;
    }

    @PutMapping("/product")
    public Product editOne(@RequestBody Product product) {
        return productService.editOne(product);
    }

    @DeleteMapping("/products")
    public void delAll() {
        productService.delAll();
    }

    @DeleteMapping("/product/{id}")
    public void delOne(@PathVariable String id) {
        productService.delById(id);
    }
}
