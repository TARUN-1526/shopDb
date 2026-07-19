package com.db.practice.service;



import com.db.practice.model.CartItems;
import com.db.practice.model.products;
import com.db.practice.repo.CartItemsRepo;
import com.db.practice.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartItemsService {
    @Autowired
    public CartItemsRepo repo;

    @Autowired
    public productRepo productRepo;

    public Map<String, List<CartItems>> addCartItems(List<CartItems> c) {

        List<CartItems> added = new ArrayList<>();
        List<CartItems> notAdded = new ArrayList<>();

        for (CartItems c1 : c) {

            products product = productRepo.findById(
                    c1.getProduct().getProd_id()
            ).orElse(null);

            if (product != null &&
                    product.getStockAvailable() >= c1.getQuantity()) {

                CartItems saved = repo.save(c1);
                added.add(saved);

            } else {
                notAdded.add(c1);
            }
        }

        Map<String, List<CartItems>> result = new HashMap<>();

        result.put("added", added);
        result.put("not_added", notAdded);

        return result;
    }

    public CartItems addCartItem(CartItems c)
    {
        return repo.save(c);
    }

    public List<CartItems> getCartItems()
    {
        return repo.findAll();
    }

    public CartItems getCartItemsBYId(int id)
    {
        return repo.getReferenceById(id);
    }
    public String deleteCartItem(int id)
    {
        CartItems c = repo.findById(id).orElse(null);
        if(c==null)
        {
            return "Cart Item with the id "+id+" not found";
        }
        String username = c.getCart().getUser().getUser_name();
        int id1 = c.getCart().getCart_id();
        String prodName = c.getProduct().getProd_name();
        repo.deleteById(id);
        return "The user "+username+
                " have deleted the product "+prodName+
                " for the cart id"+id1;

    }


}
