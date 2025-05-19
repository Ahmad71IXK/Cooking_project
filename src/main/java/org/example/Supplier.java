package org.example;

import java.util.HashMap;
import java.util.Map;

public class Supplier {

    private Map<String, Double> prices;
    private Map<String, Double> usualPrices;

    public Supplier() {
        prices = new HashMap<>();
        usualPrices = new HashMap<>();

        // Sample data
        prices.put("Pasta", 5.0);
        prices.put("Tomato", 2.0);
        prices.put("Chicken", 10.0);

        usualPrices.put("Pasta", 4.5);
        usualPrices.put("Tomato", 1.8);
        usualPrices.put("Chicken", 9.5);
    }

    public double getPrice(String ingredient) {
        return prices.getOrDefault(ingredient, 0.0);
    }

    public double getUsualPrice(String ingredient) {
        return usualPrices.getOrDefault(ingredient, 0.0);
    }

    public void fetchPrices() {
        // Simulating price changes
        prices.put("Pasta", 5.2);
        prices.put("Tomato", 2.1);
        prices.put("Chicken", 10.5);
    }

    public String getSupplierName() {
        return "Supplier A";
    }
}
