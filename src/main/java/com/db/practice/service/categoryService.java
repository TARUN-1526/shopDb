package com.db.practice.service;

import com.db.practice.model.category;
import com.db.practice.repo.categoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryService {
    @Autowired
    public categoryRepo repo;
    public List<category> addCategories(List<category> c)
    {
        return repo.saveAll(c);
    }
    public category saveCategory(category c)
    {
        return repo.save(c);
    }
    public List<category> getAllCategories()
    {
        return repo.findAll();
    }
    public category getByCategoryId(int id)
    {
        return repo.findById(id).orElse(null);
    }
    public void deleteByCategory(int id)
    {
        repo.deleteById(id);
    }



}
