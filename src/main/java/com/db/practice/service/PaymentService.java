package com.db.practice.service;

import com.db.practice.model.Order;
import com.db.practice.model.Payments;
import com.db.practice.repo.OrderRepo;
import com.db.practice.repo.PaymentRepo;
import org.hibernate.sql.results.graph.entity.internal.AbstractNonJoinedEntityFetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    public PaymentRepo paymentRepo;

    @Autowired
    public OrderRepo orderRepo;


    public Map<String, Object> addPayment(Payments p)
    {
        Map<String, Object> response = new HashMap<>();

        if (p.getOrder() == null)
        {
            response.put("message", "Order details are required");
            return response;
        }

        int orderId = p.getOrder().getOrder_id();

        Order o = orderRepo.findById(orderId).orElse(null);

        if (o == null)
        {
            response.put("message",
                    "The order with order id " + orderId + " not found");
            return response;
        }

        // Set actual Order entity in Payment
        p.setOrder(o);

        // Get amount automatically from Order
        p.setAmount(o.getTotalAmount());

        // Update payment status in Order
        o.setPayment_status(p.getPayment_status());

        // Save Order
        orderRepo.save(o);

        // Save Payment
        Payments savedPayment = paymentRepo.save(p);

        response.put("message", "Payment added successfully");
        response.put("payment", savedPayment);

        return response;
    }

    public List<Payments> getPayments()
    {
        return paymentRepo.findAll();
    }

    public String deleteById(int id)
    {
        Payments p = paymentRepo.findById(id).orElse(null);

        if(p == null)
        {
            return "Payment id with the given id " + id + " not found";
        }

        Order o = p.getOrder();

        String str = "Payment for the order id "
                + o.getOrder_id()
                + " is deleted";

        // Remove payment reference from Order
        o.setPayments(null);
        o.setPayment_status(null);
        orderRepo.save(o);

        // Delete Payment
        paymentRepo.deleteById(id);

        return str;
    }
    public Map<String,Object> getById(int id)
    {
        Payments p = paymentRepo.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if(p == null)
        {
            response.put("Message","the payment with the id "+id+" is not present");
            return response;

        }

        response.put("Ok",p);
        return response;
    }
}
