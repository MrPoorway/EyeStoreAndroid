package com.example.eyewearshopandroid.Entities;

public class Glasses {
    private int id;
    private String name;
    private String type;
    private double price;
    private int quantity;
    private String imageUri;

    public Glasses(String name, String type, double price, int quantity, String imageUri) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.imageUri = imageUri;
    }

    public Glasses(int id, String name, String type, double price, int quantity, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.imageUri = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return "Glasses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", imageUri='" + imageUri + '\'' +
                '}';
    }
}
