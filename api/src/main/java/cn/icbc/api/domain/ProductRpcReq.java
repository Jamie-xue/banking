package cn.icbc.api.domain;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/25 22:35
 */
public class ProductRpcReq {

    private List<String> idList;
    private List<String> statusList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private Integer pageNum;
    private  Integer pageSize;


    public ProductRpcReq() {
    }

    public ProductRpcReq(List<String> idList, List<String> statusList, BigDecimal minRewardRate, BigDecimal maxRewardRate, Integer pageNum, Integer pageSize) {
        this.idList = idList;
        this.statusList = statusList;
        this.minRewardRate = minRewardRate;
        this.maxRewardRate = maxRewardRate;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public BigDecimal getMinRewardRate() {
        return minRewardRate;
    }

    public void setMinRewardRate(BigDecimal minRewardRate) {
        this.minRewardRate = minRewardRate;
    }

    public BigDecimal getMaxRewardRate() {
        return maxRewardRate;
    }

    public void setMaxRewardRate(BigDecimal maxRewardRate) {
        this.maxRewardRate = maxRewardRate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
