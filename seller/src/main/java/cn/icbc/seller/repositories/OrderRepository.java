package cn.icbc.seller.repositories;

import cn.icbc.entity.Order;
import cn.icbc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order,String> , JpaSpecificationExecutor<Order> {
}
