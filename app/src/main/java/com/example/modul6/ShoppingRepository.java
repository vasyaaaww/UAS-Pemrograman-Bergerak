
package com.example.modul6;

import java.util.ArrayList;
import java.util.List;

public class ShoppingRepository {
    private static final ArrayList<ShoppingItem> ITEMS = new ArrayList<>();

    public static List<ShoppingItem> getItems() {
        return ITEMS;
    }

    public static void add(ShoppingItem item) {
        ITEMS.add(item);
    }

    public static void clearAll() {
        ITEMS.clear();
    }

    public static int totalQty() {
        int t = 0;
        for (ShoppingItem it : ITEMS) t += it.qty;
        return t;
    }

    public static double totalPrice() {
        double t = 0;
        for (ShoppingItem it : ITEMS) t += it.subtotal();
        return t;
    }
}
