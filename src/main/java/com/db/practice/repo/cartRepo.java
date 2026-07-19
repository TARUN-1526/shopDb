package com.db.practice.repo;

import com.db.practice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartRepo extends JpaRepository<Cart,Integer> {
}
