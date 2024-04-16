package com.example.eyewearshopandroid.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<Glasses> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(Glasses glasses) {
        items.add(glasses);
    }

    public void removeItem(Glasses glasses) {
        items.remove(glasses);
    }

    public List<Glasses> getItems() {
        return items;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Glasses glasses : items) {
            totalQuantity += glasses.getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Glasses glasses : items) {
            totalPrice += glasses.getPrice() * glasses.getQuantity();
        }
        return totalPrice;
    }
}
