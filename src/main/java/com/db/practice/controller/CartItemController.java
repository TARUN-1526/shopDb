package com.db.practice.controller;

import com.db.practice.model.CartItems;
import com.db.practice.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {
    @Autowired
    public CartItemsService service;

    @PostMapping("/addcartitems")
    public Map<String,List<CartItems>> addCartItems(@RequestBody List<CartItems> c)
    {
        return service.addCartItems(c);
    }

    @PostMapping("/addCartItem")
    public CartItems addCartItem(@RequestBody CartItems c)
    {
        return service.addCartItem(c);
    }

    @GetMapping
    public List<CartItems> getCartItems()
    {
        return service.getCartItems();
    }

    @GetMapping("/{id}")
    public CartItems getById(@PathVariable Integer id)
    {
        return service.getCartItemsBYId(id);
    }
    @DeleteMapping("{id}")
    public String deleteCartItemById(@PathVariable Integer id)
    {
        return service.deleteCartItem(id);
    }
}
