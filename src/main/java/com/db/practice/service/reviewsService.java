package com.db.practice.service;

import com.db.practice.model.Reviews;
import com.db.practice.repo.reviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class reviewsService {
    @Autowired
    private reviewsRepo repo;

    public List<Reviews> addReviews(List<Reviews> r)
    {
        return repo.saveAll(r);
    }

    public Reviews addReview(Reviews r)
    {
        return repo.save(r);
    }

    public List<Reviews> getReviews()
    {
        return repo.findAll();
    }

    public Reviews getReviewById(int id)
    {
        return repo.getReferenceById(id);
    }
    public String deleteById(int id)
    {
        repo.deleteById(id);
        return "Review "+id+" deleted";
    }

}
