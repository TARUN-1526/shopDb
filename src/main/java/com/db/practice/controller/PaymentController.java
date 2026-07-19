package com.db.practice.controller;

import com.db.practice.model.Payments;
import com.db.practice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    public PaymentService service;
    @PostMapping
    public Map<String,Object> addPayment(@RequestBody Payments p)
    {
        return service.addPayment(p);
    }
    @GetMapping()
    public List<Payments> getPayments()
    {
        return service.getPayments();
    }
    @GetMapping("/{id}")
    public Map<String,Object> getById(@PathVariable Integer id)
    {
        return service.getById(id);
    }
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable Integer id)
    {
        return service.deleteById(id);
    }

}
