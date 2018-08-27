package cn.icbc.seller.repositories;

import cn.icbc.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Auther: asus
 * @Date: 2018/8/27 21:47
 */
public interface VerificationOrderRepository extends JpaRepository<VerificationOrder,String>, JpaSpecificationExecutor<VerificationOrder> {


    @Query(value = "SELECT CONCAT_WS('|', order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount,DATE_FORMAT( create_at,'%Y-%m-%d %H:%i:%s')) FROM order_t WHERE order_status = 'success' AND chan_id = ?1 AND create_at >= ?2 AND create_at < ?3",nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date end);
}
