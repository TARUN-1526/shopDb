package com.db.practice.repo;

import com.db.practice.model.CartItems;
import com.db.practice.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemsRepo extends JpaRepository<CartItems, Integer> {
    @Query("SELECT ci FROM CartItems ci WHERE ci.cart.cart_id = :cartId")
    List<CartItems> findByCartId(@Param("cartId") int cartId);
}
