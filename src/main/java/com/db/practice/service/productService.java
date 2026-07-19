package com.db.practice.service;


import com.db.practice.model.products;
import com.db.practice.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class productService{
    @Autowired
    public productRepo repo;

    public List<products> addProducts(List<products> p)
    {
        return repo.saveAll(p);
    }
    public products addProduct(products p)
    {
        return repo.save(p);
    }

    public List<products> getAllProducts()
    {
        return repo.findAll();
    }

    public products getByProductid(int id)
    {
        return repo.getReferenceById(id);
    }

    public String deleteById(int id)
    {
        repo.deleteById(id);
        return "Product Deleted";
    }

}
