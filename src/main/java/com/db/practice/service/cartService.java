package com.db.practice.service;

import com.db.practice.model.Cart;
import com.db.practice.model.CartItems;
import com.db.practice.model.User;
import com.db.practice.model.products;
import com.db.practice.repo.CartItemsRepo;
import com.db.practice.repo.cartRepo;
import com.db.practice.repo.productRepo;
import com.db.practice.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class cartService {
    @Autowired
    public cartRepo cartRepo;
    @Autowired
    public productRepo productRepo;
    @Autowired
    public CartItemsRepo cartItemsRepo;
    @Autowired
    public userRepo userRepo;


    public List<Cart> addCarts(List<Cart> c) {
        return cartRepo.saveAll(c);
    }

    public Map<String, Object> addCart(Cart c) {
        Map<String, Object> response = new HashMap<>();
        List<String> notAdded = new ArrayList<>();
        List<CartItems> added = new ArrayList<>();
        User u = userRepo.findById(c.getUser().getUser_id()).orElse(null);
        if (u == null) {
            response.put("Message", "the user with the user id" + c.getUser().getUser_id() + "Not found");
            return response;
        }
        for (CartItems item : c.getCartItems()) {
            products p = productRepo.findById(item.getProduct().getProd_id()).orElse(null);
            if (p == null) {
                notAdded.add("Product id " + item.getProduct().getProd_id() + " not found");
                continue;
            }
            c.setUser(u);
            if (p.getStockAvailable() < item.getQuantity()) {
                notAdded.add("product " + p.getProd_name() + " has less stock i.e " + p.getStockAvailable() +
                        "but wanted" + item.getQuantity());
                continue;
            }
            item.setProduct(p);
            item.setCart(c);
            added.add(item);

        }
        if (added.isEmpty()) {
            response.put("MEssage", "Cart not created because of invalid quantity items ");
            response.put("Not added", notAdded);
            return response;
        }
        c.setCartItems(added);

        cartRepo.save(c);
        response.put("added", added);
        response.put("Not added", notAdded);
        return response;


    }

    public List<Cart> getCarts() {
        return cartRepo.findAll();
    }



    public Cart getCartById(int id) {
        return cartRepo.findById(id).orElse(null);
    }

    public List<String> deleteById(int id) {
        Cart c = cartRepo.findById(id).orElse(null);

        List<String> deleted = new ArrayList<>();

        if (c == null) {
            deleted.add("There is no cart with id " + id);
            return deleted;
        }

        deleted.add("The cart items of "
                + c.getUser().getUser_name()
                + " are being deleted");

        for (CartItems item : c.getCartItems()) {
            deleted.add("The product "
                    + item.getProduct().getProd_name()
                    + " is deleted from cart");
        }

        // Cascade deletes associated CartItems
        cartRepo.deleteById(id);

        deleted.add("Cart with id " + id + " is deleted");

        return deleted;
    }
}
