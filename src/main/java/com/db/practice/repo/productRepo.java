package com.db.practice.repo;

import com.db.practice.model.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepo extends JpaRepository<products,Integer> {
}
