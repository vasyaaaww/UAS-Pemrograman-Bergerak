
package com.example.modul6;

public class ShoppingItem {
    public final String name;
    public final int qty;
    public final double price;

    public ShoppingItem(String name, int qty, double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public double subtotal() {
        return qty * price;
    }
}
