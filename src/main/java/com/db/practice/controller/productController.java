package com.db.practice.controller;

import com.db.practice.model.products;
import com.db.practice.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {
    @Autowired
    public productService service;

    @PostMapping("/addProducts")
    public List<products> addProducts(@RequestBody List<products> p)
    {
        return service.addProducts(p);
    }
    @PostMapping("/addProduct")
    public products addProduct(@RequestBody products p)
    {
        return service.addProduct(p);

    }

    @GetMapping
    public List<products> getAllProducts()
    {
        return service.getAllProducts();
    }
    @GetMapping("/{id}")
    public products getProdById(@PathVariable Integer id)
    {
        return service.getByProductid(id);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id)
    {
        return service.deleteById(id);
    }


}
