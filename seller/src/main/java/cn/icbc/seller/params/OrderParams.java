package cn.icbc.seller.params;

import cn.icbc.seller.sign.SignText;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: asus
 * @Date: 2018/8/27 17:48
 */
public class OrderParams implements SignText {


    //渠道id
    private String chanId;
    //产品编号
    private String productId;
    //用户编号
    private String chanUserId;
    //外部订单编号
    private String outerOrderId;
    //订单金额
    private BigDecimal amount;
    //备注
    private String memo;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    public String getOuterOrderId() {
        return outerOrderId;
    }

    public void setOuterOrderId(String outerOrderId) {
        this.outerOrderId = outerOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
