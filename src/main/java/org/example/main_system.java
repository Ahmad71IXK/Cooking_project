package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class main_system {


    public enum Meals {
        CHICKEN_BIRYANI(new String[]{"Chicken", "Rice", "Spices", "Yogurt"}),
        PASTA_WITH_VEGETABLES(new String[]{"Pasta", "Tomatoes", "Bell Peppers", "Onions"}),
        SUSTAINABLE_CAKE(new String[]{"Flour", "Sugar", "Eggs", "Eco-friendly Butter"}),
        ICE_CREAM(new String[]{"Milk", "Sugar", "Vanilla"}),
        JUICE(new String[]{"Oranges", "Lemons"}),
        AVOCADO_SALAD(new String[]{"Avocado", "Lettuce", "Olive Oil"}),
        CAESAR_SALAD(new String[]{"Lettuce", "Croutons", "Parmesan", "Caesar Dressing"});
        private final Random random = new Random();
        private String[] ingredient;

        private int[] ingredientCapacity; // Array to hold the random capacities for ingredients


        Meals(String[] ingredient) {
            this.ingredient = ingredient;
            this.ingredientCapacity = new int[ingredient.length];

            // Assign random capacity to each ingredient
            for (int i = 0; i < ingredient.length; i++) {
                // Random capacity between 1 and 100
                this.ingredientCapacity[i] = random.nextInt(100) + 1;
            }

        }
        public int[] getIngredientCapacity() {
            return ingredientCapacity;
        }
        public void setIngredients(String[] ingredients) {
            this.ingredient = ingredients;
        }

        public String []get_ingredient()
        {
            return ingredient;
        }
    }
        public enum Ingredient {
            CHICKEN,
            RICE,
            SPICES,
            YOGURT,
            PASTA,
            TOMATOES,
            BELL_PEPPERS,
            ONIONS,
            FLOUR,
            SUGAR,
            EGGS,
            ECO_FRIENDLY_BUTTER,
            MILK,
            VANILLA,
            ORANGES,
            LEMONS,
            AVOCADO,
            LETTUCE,
            OLIVE_OIL,
            CROUTONS,
            PARMESAN,
            CAESAR_DRESSING;
            public String getIngredientName() {
                return name(); // Built-in enum method that returns the constant's name as String
            }
        }

    public static final String[] PLAN_A_MEALS = {
            "CHICKEN_BIRYANI",
            "JUICE",
            "AVOCADO_SALAD"
    };

    public static final String[] PLAN_B_MEALS = {
            "ICE_CREAM",
            "SUSTAINABLE_CAKE",
            "CAESAR_SALAD",
            "PASTA_WITH_VEGETABLES"
    };
    public enum Subingredient {
        CHICKEN(new String[]{"TOFU", "MUSHROOM", "SEITAN"}),
        RICE(new String[]{"QUINOA", "BARLEY", "COUSCOUS"}),
        SPICES(new String[]{"HERBS", "GARLIC", "GINGER"}),
        YOGURT(new String[]{"COCONUT_YOGURT", "SOY_YOGURT", "ALMOND_YOGURT"}),
        PASTA(new String[]{"ZUCCHINI_NOODLES", "GLUTEN_FREE_PASTA", "SPAGHETTI_SQUASH"}),
        TOMATOES(new String[]{"BELL_PEPPER", "CARROT", "PUMPKIN"}),
        BELL_PEPPERS(new String[]{"ZUCCHINI", "CARROT", "CABBAGE"}),
        ONIONS(new String[]{"SHALLOTS", "GARLIC", "LEEK"}),
        FLOUR(new String[]{"CORNSTARCH", "ALMOND_FLOUR", "COCONUT_FLOUR"}),
        SUGAR(new String[]{"HONEY", "STEVIA", "AGAVE_SYRUP"}),
        EGGS(new String[]{"FLAXSEED_EGGS", "CHIA_EGGS", "TOFU"}),
        ECO_FRIENDLY_BUTTER(new String[]{"AVOCADO_OIL", "COCONUT_OIL", "OLIVE_OIL"}),
        MILK(new String[]{"COCONUT_MILK", "ALMOND_MILK", "SOY_MILK"}),
        VANILLA(new String[]{"ALMOND_EXTRACT", "MAPLE_SYRUP", "COCONUT_EXTRACT"}),
        ORANGES(new String[]{"GRAPEFRUIT", "LEMONS", "KIWI"}),
        LEMONS(new String[]{"LIME", "VINEGAR", "TAMARIND"}),
        AVOCADO(new String[]{"MASHED_POTATOES", "TOFU", "GUACAMOLE"}),
        LETTUCE(new String[]{"SPINACH", "KALE", "ARUGULA"}),
        OLIVE_OIL(new String[]{"COCONUT_OIL", "AVOCADO_OIL", "GRAPESEED_OIL"}),
        CROUTONS(new String[]{"TOASTED_SEEDS", "ROASTED_CHICKPEAS", "NUTS"}),
        PARMESAN(new String[]{"NUTRITIONAL_YEAST", "VEGAN_CHEESE", "CASHEW_CHEESE"}),
        CAESAR_DRESSING(new String[]{"AVOCADO_DRESSING", "YOGURT_DRESSING", "LEMON_TAHINI_DRESSING"});

        private final String[] substitutes;

        Subingredient(String[] substitutes) {
            this.substitutes = substitutes;
        }

        public String[] getSubstitutes() {
            return substitutes;
        }
    }
    public static class InventoryManager {
        private Map<String, Integer> ingredientStock = new HashMap<>();
        private Map<String, Double> ingredientPrices = new HashMap<>();
        private Map<String, Double> usualPrices = new HashMap<>();

       
        public void updateStock(String ingredient, int quantity) {
            ingredientStock.put(ingredient, quantity);
        }

        
        public int checkStockLevel(String ingredient) {
            return ingredientStock.getOrDefault(ingredient, 0);
        }

        
        public boolean generatePurchaseOrder(String ingredient) {
            int currentStock = checkStockLevel(ingredient);
            if (currentStock <= 10) {
                System.out.println("Generating purchase order for " + ingredient);
                return true;
            }
            return false;
        }
        
        public String selectPreferredSupplier(String ingredient) {
            return "Supplier XYZ";
        }
        
        public String getUsageHistory(String ingredient) {
            return "Usage history for " + ingredient +": Used 10 units last month";
        }

        public double fetchLatestPrice(String ingredient) {
            return ingredientPrices.getOrDefault(ingredient, 5.0);
        }

        public void retrieveRealTimePrices() {
            ingredientPrices.clear();
            usualPrices.clear();

            // تحديث الأسعار الحالية
            ingredientPrices.put("Pasta", 5.0);
            ingredientPrices.put("Tomato", 3.0);
            ingredientPrices.put("Olive Oil", 7.0);
            ingredientPrices.put("Basil", 2.0);
            ingredientPrices.put("Garlic", 1.5);

            // تحديث الأسعار المعتادة
            usualPrices.put("Pasta", 4.0);
            usualPrices.put("Tomato", 2.5);
            usualPrices.put("Olive Oil", 6.0);
            usualPrices.put("Basil", 1.8);
            usualPrices.put("Garlic", 1.2);

            // التحقق من زيادة الأسعار
            for (String ingredient : ingredientPrices.keySet()) {
                double currentPrice = ingredientPrices.get(ingredient);
                double usualPrice = usualPrices.get(ingredient);
                if (currentPrice > usualPrice) {
                    // التنبيه بزيادة السعر
                    System.out.println("Price increase detected for " + ingredient + ": " + usualPrice + " -> " + currentPrice);
                }
            }

            System.out.println("Retrieved real-time prices from suppliers");
        }

        public Map<String, Double> getIngredientPrices() {
            return ingredientPrices;
        }

        public void setIngredientPrices(Map<String, Double> ingredientPrices) {
            this.ingredientPrices = ingredientPrices;
        }
        public double calculateTotalCost(String[] ingredients) {
            double total = 0.0;
            for (String ingredient : ingredients) {
                total += fetchLatestPrice(ingredient);
            }
            return total;
        }
        public boolean detectPriceIncrease(String ingredient, int percentageThreshold) {
            double usualPrice = usualPrices.getOrDefault(ingredient, 5.0);
            double latestPrice = fetchLatestPrice(ingredient);
            double increasePercentage = ((latestPrice - usualPrice) / usualPrice) * 100;
            System.out.println("Usual price for " + ingredient + ": " + usualPrice);
            System.out.println("Latest price for " + ingredient + ": " + latestPrice);
            System.out.println("Increase percentage: " + increasePercentage + "%");
            return increasePercentage > percentageThreshold;
        }
        public Double getUsualPrice(String ingredient) {
            return usualPrices.get(ingredient);
        }




    }

    public String generateCustomerInvoice(String item, double price) {
        InventoryManager inventory = new InventoryManager();
        int stockLevel = inventory.checkStockLevel(item);
        if (stockLevel <= 0) {
            return "Error: Item " + item + " is out of stock.";
        }
        StringBuilder ingredientsList = new StringBuilder();
        try {
            Meals meal = Meals.valueOf(item.toUpperCase().replace(" ", "_"));
            String[] ingredients = meal.get_ingredient();
            ingredientsList.append("Ingredients: ").append(Arrays.toString(ingredients));
        } catch (IllegalArgumentException e) {
            ingredientsList.append("Ingredients: Not specified");
        }
        return "Invoice: " + item + " - " + price + " (" + ingredientsList.toString() + ")";
    }

    public String generateFinancialReport(add_prefreances_customer customer) {
        InventoryManager inventory = new InventoryManager();
        inventory.retrieveRealTimePrices();
        Map<String, Double> prices = inventory.getIngredientPrices();
        double totalRevenue = 0.0;
        String[] orders = customer.getPast_orders();
        if (orders == null) {
            return "Total Revenue: 0.0";
        }
        for (String order : orders) {

            int stockLevel = inventory.checkStockLevel(order);
            if (stockLevel <= 0) {
                continue;
            }
            try {
                Meals meal = Meals.valueOf(order.toUpperCase().replace(" ", "_"));
                for (String ingredient : meal.get_ingredient()) {
                    totalRevenue += prices.getOrDefault(ingredient, 5.0);
                }
            } catch (IllegalArgumentException e) {
                totalRevenue += prices.getOrDefault(order, 5.0);
            }
        }
        return "Total Revenue: " + totalRevenue;
    }



}
