package com.db.practice.service;

import com.db.practice.model.Wishlist;
import com.db.practice.repo.wishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class wishlistService {
    @Autowired
    public wishlistRepo repo;
    public List<Wishlist> addWishlists(List<Wishlist> w)
    {
        return repo.saveAll(w);
    }
    public Wishlist addWishlist(Wishlist w)
    {
        return repo.save(w);
    }

    public List<Wishlist> getAllWishlist()
    {

        return repo.findAll();
    }

    public Wishlist getById(int id)
    {
        return repo.getReferenceById(id);
    }

    public String deleteById(int id) {

        Wishlist w = repo.findById(id).orElse(null);

        if (w == null) {
            return "Wishlist with id " + id + " not found";
        }

        String username = w.getUser_id().getUser_name();

        repo.deleteById(id);

        return "The wishlist of user " + username
                + " with wishlist id " + w.getWishlistId()
                + " is deleted";
    }




}
