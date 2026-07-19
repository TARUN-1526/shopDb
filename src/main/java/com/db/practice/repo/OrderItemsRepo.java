package com.db.practice.repo;

import com.db.practice.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemsRepo extends JpaRepository<OrderItems,Integer> {
    @Query("SELECT oi FROM OrderItems oi WHERE oi.order.order_id = :orderId")
    List<OrderItems> findByOrderId(@Param("orderId") int orderId);
}
