package com.db.practice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishlistId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    @ManyToOne
    @JoinColumn(name = "prod_id")
    private products prod_id;
    private LocalDateTime added_at;
    @PrePersist
    public void prePersist() {
        added_at = LocalDateTime.now();
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public products getProd_id() {
        return prod_id;
    }

    public void setProd_id(products prod_id) {
        this.prod_id = prod_id;
    }

    public LocalDateTime getAdded_at() {
        return added_at;
    }

    public void setAdded_at(LocalDateTime added_at) {
        this.added_at = added_at;
    }
}
