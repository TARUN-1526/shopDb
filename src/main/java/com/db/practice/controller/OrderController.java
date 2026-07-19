package com.db.practice.controller;

import com.db.practice.model.Order;
import com.db.practice.repo.OrderRepo;
import com.db.practice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public OrderService service;
    @PostMapping
    public Map<String,Object> addOrder(@RequestBody Order o)
    {
        return service.addOrder(o);
    }
    @GetMapping
    public List<Order> getOrders()
    {
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Integer id)
    {
        return service.getById(id);
    }
    @DeleteMapping("{id}")
    public List<String> deleteById(@PathVariable Integer id)
    {
        return service.deleteById(id);
    }
}
