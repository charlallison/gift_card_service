package com.calvin.model;

public class GiftItem {

    private int id;
    private String name;
    private double price;
    private String photo;
    private String description;

    public GiftItem(int id, String name, String description, double price, String photo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }
}
