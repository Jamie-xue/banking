package cn.icbc.api.events;

import cn.icbc.entity.enums.ProductStatus;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @Auther: asus
 * @Date: 2018/8/27 10:59
 */
public class ProductStatusEvent implements Serializable {

    private String id;
    private ProductStatus status;


    public ProductStatusEvent(String id, ProductStatus status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
