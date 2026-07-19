package com.db.practice.repo;

import com.db.practice.model.Wishlist;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wishlistRepo extends JpaRepository<Wishlist,Integer> {
}
