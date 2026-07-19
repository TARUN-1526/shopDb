package com.db.practice.service;

import com.db.practice.model.Order;
import com.db.practice.model.OrderItems;
import com.db.practice.model.User;
import com.db.practice.model.products;
import com.db.practice.repo.OrderItemsRepo;
import com.db.practice.repo.OrderRepo;
import com.db.practice.repo.productRepo;
import com.db.practice.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private productRepo productRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private OrderItemsRepo orderItemRepo;

    public Map<String,Object> addOrder(Order order)
    {
        Map<String,Object> response = new HashMap<>();

        List<String> notAdded = new ArrayList<>();
        List<OrderItems> added = new ArrayList<>();

        User u = userRepo.findById(order.getUser().getUser_id()).orElse(null);

        if(u==null)
        {
            response.put("Message","User not found");
            return response;
        }
        order.setUser(u);
        int totalAmount=0;

        for(OrderItems item:order.getOrderItems())
        {
            products p = productRepo.findById(item.getProduct()
                    .getProd_id()).orElse(null);
            if(p==null)
            {
                notAdded.add("Product id"+item.getProduct().getProd_id()+" Not found");
                continue;
            }
            if(p.getStockAvailable() < item.getQuantity())
            {
                notAdded.add(p.getProd_name()+" is not added due to stck available is"+p.getStockAvailable()
                        +" but required qunatity is "+item.getQuantity());
                continue;
            }
            item.setProduct(p);
            item.setOrder(order);
            item.setPrice((int) p.getPrice());
            totalAmount+= (int) p.getPrice() * item.getQuantity();

            p.setStockAvailable(
                    p.getStockAvailable() - item.getQuantity()
            );

            productRepo.save(p);
            added.add(item);

        }
        if(added.isEmpty())
        {
            response.put("message","Order not created because the products does have sufficient quantity");
            response.put("not added",notAdded);
            return response;
        }
        order.setOrderItems(added);
        order.setTotalAmount(totalAmount);

        Order saveOrder = orderRepo.save(order);

        response.put("message", "Order created successfully");
        response.put("order", added);
        response.put("not_added", notAdded);

        return response;





    }
    public List<Order> getOrders()
    {
        return orderRepo.findAll();
    }

    public Order getById(int id)
    {
        return orderRepo.getReferenceById(id);
    }

    public List<String> deleteById(int id)
    {
        List<String> msgs = new ArrayList<>();
        Order o = orderRepo.findById(id).orElse(null);
        if(o == null)
        {
            msgs.add("No order with order id "+id);
            return msgs;
        }
        List<OrderItems> or = orderItemRepo.findByOrderId(o.getOrder_id());
        for(OrderItems item:or)
        {
            products p = productRepo.findById(item.getProduct().getProd_id())
                    .orElse(null);
            if(p!=null)
            {
                p.setStockAvailable(p.getStockAvailable()+item.getQuantity());
                productRepo.save(p);
                msgs.add("Product " + p.getProd_name()
                        + " with order item id " + item.getOrder_item_id()
                        + " associated with order id " + id
                        + " is deleted");
                orderItemRepo.deleteById(item.getOrder_item_id());
            }
            msgs.add("Order created by " + o.getUser().getUser_name()
                    + " with order id " + id
                    + " is deleted");
            orderRepo.deleteById(id);





        }
        return msgs;



    }

}
