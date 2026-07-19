package com.db.practice.controller;

import com.db.practice.model.Cart;
import com.db.practice.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class cartController {

    @Autowired
    public cartService service;

    @PostMapping("/addCarts")
    public List<Cart> addCarts(@RequestBody List<Cart> c)
    {
        return service.addCarts(c);
    }
    @PostMapping("/addCart")
    public Map<String,Object> addCart(@RequestBody Cart c)
    {

        return service.addCart(c);
    }
    @GetMapping
    public List<Cart> getCarts()
    {
        return service.getCarts();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Integer id)
    {
        return service.getCartById(id);
    }

    @DeleteMapping("/{id}")
    public List<String> deleteById(@PathVariable Integer id)
    {
        return service.deleteById(id);
    }
}
