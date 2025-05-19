package org.example;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Integer> stock;
    private Map<String, Double> prices;
    private Supplier supplier;
    private Map<String, Double> ingredientPrices = new HashMap<>();

    public InventoryManager() {
        stock = new HashMap<>();
        prices = new HashMap<>();
        supplier = new Supplier();
        retrieveRealTimePrices();
    }

    public void updateStock(String ingredient, int quantity) {
        stock.put(ingredient, quantity);
    }

    public int checkStockLevel(String ingredient) {
        return stock.getOrDefault(ingredient, 0);
    }

//    public boolean generatePurchaseOrder(String ingredient) {
//        int currentStock = stock.getOrDefault(ingredient, 0);
//        System.out.println("Checking generatePurchaseOrder for " + ingredient + ". Current stock: " + currentStock);
//        return currentStock <= 2;
//
//    }
    public boolean shouldSuggestRestocking(String ingredient) {
        int currentStock = stock.getOrDefault(ingredient, 0);
        return currentStock <= 2;
    }
    public boolean shouldCreateActualPurchaseOrder(String ingredient) {
        int currentStock = stock.getOrDefault(ingredient, 0);
        return currentStock <= 1;
    }

    public void retrieveRealTimePrices() {
        supplier.fetchPrices();
        for (String ingredient : prices.keySet()) {
            prices.put(ingredient, supplier.getPrice(ingredient));
        }
    }

    public double fetchLatestPrice(String ingredient) {
        return supplier.getPrice(ingredient);
    }

    public double getUsualPrice(String ingredient) {
        return supplier.getUsualPrice(ingredient);
    }

    public boolean detectPriceIncrease(String ingredient, int percentage) {
        double latestPrice = fetchLatestPrice(ingredient);
        double usualPrice = getUsualPrice(ingredient);

        if (usualPrice == 0) {
            return false;
        }

        double increasePercentage = ((latestPrice - usualPrice) / usualPrice) * 100;
        return increasePercentage > percentage;
    }

    public String selectPreferredSupplier(String ingredient) {
        return supplier.getSupplierName();
    }

    public String getUsageHistory(String ingredient) {
        return "Usage history for " + ingredient;
    }

    public double calculateTotalCost(String[] ingredients) {
        double totalCost = 0.0;
        for (String ingredient : ingredients) {
            totalCost += fetchLatestPrice(ingredient);
        }
        return totalCost;
    }
//    public Map<String, Double> getIngredientPrices() {
//        return ingredientPrices;
//    }

}
