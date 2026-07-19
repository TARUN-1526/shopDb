package com.db.practice.controller;

import com.db.practice.model.category;
import com.db.practice.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class categoryConntroller {
    @Autowired
    categoryService service;

    @PostMapping("/addCategories")
    public List<category> addCategories(@RequestBody List<category> c)
    {
        return service.addCategories(c);
    }
    @PostMapping("addCategory")
    public category save(@RequestBody category cat)
    {
        return service.saveCategory(cat);
    }
    @GetMapping
    public List<category> getAll()
    {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public category getById(@PathVariable Integer id)
    {
        return service.getByCategoryId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByid(@PathVariable Integer id)
    {
        service.deleteByCategory(id);
        return "Deleted";
    }



}
