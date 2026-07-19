package com.db.practice.controller;

import com.db.practice.model.Wishlist;
import com.db.practice.service.wishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class wishlistController {
    @Autowired
    public wishlistService service;
    @PostMapping("/addWishlists")
    public List<Wishlist> addWishlists(@RequestBody List<Wishlist> w)
    {
        return service.addWishlists(w);
    }
    @PostMapping("/addWishlist")
    public Wishlist addWishList(@RequestBody Wishlist w)
    {

        return service.addWishlist(w);
    }

    @GetMapping
    public List<Wishlist> getWishlist()
    {
        return service.getAllWishlist();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id)
    {
        return service.deleteById(id);
    }



}
