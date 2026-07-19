package com.db.practice.repo;

import com.db.practice.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reviewsRepo extends JpaRepository<Reviews,Integer> {
}
