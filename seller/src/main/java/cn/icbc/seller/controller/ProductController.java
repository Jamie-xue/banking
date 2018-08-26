package cn.icbc.seller.controller;

import cn.icbc.entity.Product;
import cn.icbc.seller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: asus
 * @Date: 2018/8/26 20:08
 */
@RestController
public class ProductController {


    @Autowired
    private ProductRpcService productRpcService;

    @GetMapping("/product/{id}")
    public Product findOne(@PathVariable("id") String id){
        return productRpcService.findOnePro(id);
    }
}
