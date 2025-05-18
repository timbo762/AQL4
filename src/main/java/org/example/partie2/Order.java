package org.example.partie2;

public class Order {
    private long id;
    private String productName;
    private int quantity;
    private double price;

    public Order(long id, String productName, int quantity, double price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

}