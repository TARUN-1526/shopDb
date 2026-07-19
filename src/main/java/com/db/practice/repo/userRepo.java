package com.db.practice.repo;

import com.db.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,Integer> {

}
