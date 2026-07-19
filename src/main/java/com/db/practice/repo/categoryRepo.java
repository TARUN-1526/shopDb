package com.db.practice.repo;

import com.db.practice.model.category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepo extends JpaRepository<category,Integer> {

}
