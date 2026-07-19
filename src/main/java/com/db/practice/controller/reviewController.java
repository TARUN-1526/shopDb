package com.db.practice.controller;

import com.db.practice.model.Reviews;
import com.db.practice.service.reviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class reviewController {
    @Autowired
    public reviewsService service;

    @PostMapping("/addReviews")
    public List<Reviews> addReviews(@RequestBody List<Reviews> r)
    {
        return service.addReviews(r);
    }
    @PostMapping("addReview")
    public Reviews addReview(@RequestBody Reviews r)
    {
        return service.addReview(r);
    }

    @GetMapping
    public List<Reviews> getAll()
    {
        return service.getReviews();
    }

    @GetMapping("/{id}")
    public Reviews getRevbyId(@PathVariable Integer id)
    {
        return service.getReviewById(id);
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable Integer id)
    {
        return service.deleteById(id);
    }



}
