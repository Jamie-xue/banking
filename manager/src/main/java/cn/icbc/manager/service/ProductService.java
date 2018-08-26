package cn.icbc.manager.service;

import cn.icbc.entity.Product;
import cn.icbc.entity.enums.ProductStatus;
import cn.icbc.manager.error.ErrorEnum;
import cn.icbc.manager.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 */
@Service
public class ProductService {


    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product saveProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NULL.getCode());
        LOGGER.debug("Service层创建产品,参数:{}", product);


        setDefault(product);


        checkProduct(product);

        Product pro = productRepository.save(product);
        LOGGER.debug("Service层创建产品,结果:{}", pro);
        return pro;


    }

    /**
     * 产品数据校验
     * 1. 非空数据
     * 2. 收益率要0-30以内
     * 3. 投资步长需为整数
     *
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), "编号不可为空");
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0
                && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) > 0, "收益率范围错误");

        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue())
                .compareTo(product.getStepAmount()) == 0, "投资步长需为整数");
    }

    /**
     * 设置默认值
     * 创建时间、更新时间
     * 投资步长、锁定期、状态
     *
     * @param product
     */
    private void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }


    public List<Product> findAllPro() {
        return productRepository.findAll();
    }

    public Product findOnePro(String id) {
        Assert.notNull(id,"需要产品编号参数");
        LOGGER.debug("查询单个产品,id={}",id);
        Product pro = productRepository.findOne(id);
        LOGGER.debug("查询单个产品,结果={}",pro);
        return pro;
    }


    public Page<Product> query(List<String> idList, BigDecimal minRewardRate,
                               BigDecimal maxRewardRate, List<String> statusList,
                               Pageable pageable){

        Specification<Product> spec = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if(idList!=null&&idList.size()>0){
                    predicates.add(idCol.in(idList));
                }
                if(statusList!=null&&statusList.size()>0){
                    predicates.add(statusCol.in(statusList));
                }
                if(minRewardRate!=null&&BigDecimal.ZERO.compareTo(minRewardRate)<0){
                    predicates.add(cb.ge(rewardRateCol,minRewardRate));
                }
                if (maxRewardRate!=null&&BigDecimal.ZERO.compareTo(maxRewardRate)<0){
                    predicates.add(cb.le(rewardRateCol,maxRewardRate));
                }

                query.where(predicates.toArray(new Predicate[0]));

                return null;
            }
        };
        Page<Product> page = productRepository.findAll(spec, pageable);
        LOGGER.debug("查询产品,结果={}",page);
        return page;

    }

    

    public Product editOne(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void delPro(Product product) {
        productRepository.delete(product);
    }

    public void delById(String id) {
        Assert.notNull(id,"需要产品编号参数");
        productRepository.delete(id);
    }

    public void delAll() {
        productRepository.deleteAll();
    }

}
