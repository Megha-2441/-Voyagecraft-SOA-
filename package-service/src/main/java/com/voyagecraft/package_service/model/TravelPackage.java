package com.voyagecraft.package_service.model;

public class TravelPackage {

    private int id;
    private String destination;
    private double price;
    private int capacity;

    public TravelPackage() {
    }

    public TravelPackage(int id, String destination,
                         double price, int capacity) {
        this.id = id;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}