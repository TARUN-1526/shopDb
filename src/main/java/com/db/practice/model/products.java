package com.db.practice.model;

import jakarta.persistence.*;

@Entity
public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prod_id;
    private String prod_name;
    @ManyToOne
    @JoinColumn(name="cat_id")
    private category category;
    private double price;
    private int stockAvailable;


    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public category getCategory() {
        return category;
    }

    public void setCategory(category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }
}
