package cn.icbc.api;

import cn.icbc.api.domain.ProductRpcReq;
import cn.icbc.entity.Product;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/25 22:35
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {

    List<Product> query(ProductRpcReq req);


    Product findOne(String id);
}
