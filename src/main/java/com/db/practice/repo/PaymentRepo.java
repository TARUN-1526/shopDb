package com.db.practice.repo;

import com.db.practice.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payments,Integer> {
}
