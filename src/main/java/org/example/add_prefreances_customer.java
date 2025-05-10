package org.example;

import java.util.*;

public class add_prefreances_customer {
    private String[] pref_names;
    private String[] allergies_names;
    private String[] unwanted_pref;
    private String[] past_orders;
    private String[] neworders;

    ArrayList<add_prefreances_customer> prefreances = new ArrayList<>();

    public String[] getNeworders() {
        return neworders;
    }

    public void setNeworders(String[] x1) {
        neworders = x1;
    }

    public String[] getPast_orders() {
        return past_orders;
    }

    public void setPast_orders(String[] past_orders) {
        this.past_orders = past_orders;
    }

    public String[] getPref_names() {
        return pref_names;
    }

    public void setPref_names(String[] pref_names) {
        this.pref_names = pref_names;
    }

    public String[] getAllergies_names() {
        return allergies_names;
    }

    public void setAllergies_names(String[] allergies_names) {
        this.allergies_names = allergies_names;
    }

    public String[] getUnwanted_pref() {
        return unwanted_pref;
    }

    public void setUnwanted_pref(String[] unwanted_pref) {
        this.unwanted_pref = unwanted_pref;
    }

    public ArrayList<add_prefreances_customer> getPrefreances() {
        return prefreances;
    }

    public void addPrefreance(add_prefreances_customer pref) {
        this.prefreances.add(pref);
    }

    public void in_order(String[] strings) {
        if (strings == null || strings.length == 0) {
            System.out.println("Error: No orders provided.");
            return;
        }

        if (past_orders == null) {
            past_orders = new String[strings.length];
            System.arraycopy(strings, 0, past_orders, 0, strings.length);
        } else {
            String[] updatedOrders = new String[past_orders.length + strings.length];
            System.arraycopy(past_orders, 0, updatedOrders, 0, past_orders.length);
            System.arraycopy(strings, 0, updatedOrders, past_orders.length, strings.length);
            past_orders = updatedOrders;
        }

        System.out.println("Order(s) recorded: " + Arrays.toString(past_orders));
    }

    class prefnames extends main_system {
        add_prefreances_customer x = new add_prefreances_customer();
        Scanner input1 = new Scanner(System.in);
        String input = input1.nextLine().toUpperCase();
        String[] meal = {"CHICKEN_BIRYANI"};
        String[] meal2 = {"PASTA_WITH_VEGETABLES"};

        prefnames(String[] name, String[] name2) {

            x.setPref_names(name);
            x.setAllergies_names(name2);

        }

        void store_unwanted_pref(String[] unwanted) {

            for (Ingredient meals : Ingredient.values()) {

                if (meals.name().equals(input)) {
                    x.setUnwanted_pref(new String[]{meals.name()});

                } else {
                    System.out.println("the prefrence you entared is not on the menu");
                }
            }
        }

        void prevent_unwanted_meals(String[] x1) {
            for (Meals meals : Meals.values()) {
                if (!meals.name().equals(input)) {
                    x1 = new String[]{meals.name()};
                } else {
                    System.out.println("your dietary is done");
                }
            }

        }

        void recordPreferenceOrder(String[] order) {
            setPast_orders(order);
            x.addPrefreance(x);
        }

        void suggest_meals(String[] suggest) {

            if (suggest.equals(meal)) {
                System.out.println("🍽️ Plan A Meals:");
                for (String meal1 : PLAN_A_MEALS) {
                    System.out.println("- " + meal1);
                }
            }

            if (suggest.equals(meal2)) {
                System.out.println("🍽️ Plan B Meals:");
                for (String meal1 : PLAN_B_MEALS) {
                    System.out.println("- " + meal1);
                }
            }
        }

        void offer(String[] plan) {
            boolean flag = false;
            if (plan.equals(meal)) {

                flag = true;
            }

        }

        void add_meal(String[] meal) {
            String[] meal_order = meal;
            meal = getPast_orders();
            System.out.println("did you want to remove an ingrediant? y // n");
            String in = input1.nextLine().toUpperCase();
            Meals selectedMeal;
            try {
                selectedMeal = Meals.valueOf(in); // Convert input string to enum
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid meal name.");
                return;
            }
            String[] ingredients = Arrays.copyOf(selectedMeal.get_ingredient(), selectedMeal.get_ingredient().length);
            if (in.equals("Y")) {
                System.out.println("Enter the ingredient that you want to delete:");
                String ingredientToDelete = input1.nextLine();

                List<String> modifiedList = new ArrayList<>(Arrays.asList(ingredients));
                if (modifiedList.removeIf(i -> i.equalsIgnoreCase(ingredientToDelete))) {
                    System.out.println("✅ Ingredient removed.");
                } else {
                    System.out.println("❌ Ingredient not found.");
                }

                ingredients = modifiedList.toArray(new String[0]); // Updated array
            }
        }

        void Addingrediantmeal(ArrayList<String> ingredients) {
            for (Meals meal : Meals.values()) { // Loop over each meal
                for (String mealIngredient : meal.get_ingredient()) { // Loop over meal's ingredients
                    for (String inputIngredient : ingredients) { // Loop over user's ingredients
                        if (mealIngredient.equalsIgnoreCase(inputIngredient)) {
                            System.out.println("✅ Found ingredient '" + inputIngredient + "' in meal: " + meal.name());
                            String[] result = meal.name().split(" ");
                            setPast_orders(result);
                        }
                    }
                }
            }
        }

        String Ingrediantreplace() {
            String[] x = getUnwanted_pref();  // Array of unwanted ingredient names
            Subingredient replacement = null;

            for (String ingredientName : x) {
                for (Subingredient meal : Subingredient.values()) {
                    if (ingredientName.equalsIgnoreCase(meal.name())) {
                        System.out.println("Found match: " + meal.name());
                        System.out.println("Choose one of the choices for replacement:");

                        // You can display choices here if needed

                        replacement = Subingredient.valueOf(meal.name()); // Set replacement
                        break;  // Optional: stop after the first match
                    }
                }
            }

            if (replacement != null) {
                return replacement.name();  // Return the replacement as a String
            } else {
                return "No replacement found";
            }
        }

        void validate(String[] Ingrediantcap) {
            String x = Ingrediantreplace();

            for (Meals dumi : Meals.values()) {
                int[] cap = dumi.getIngredientCapacity();

                if (x.equals(dumi.name()) && cap.length > 0) {
                    setPast_orders(new String[]{dumi.name()});
                }

            }
        }

        public void modifyMealIngredients(String[] unwantedPrefs) {
            // Assuming getPast_orders() returns an array of meal names (e.g., ["CHICKEN_CURRY", "PASTA_ARRABBIATA"])
            String[] orderNames = getPast_orders();
            if (orderNames == null) {
                System.out.println("No orders to modify.");
                return;
            }
            for (String orderName : orderNames) {
                // Iterate over the Meals enum
                for (Meals meal : Meals.values()) {
                    // Check if the meal name matches the order name
                    if (meal.name().equals(orderName)) {
                        String[] mealIngredients = meal.get_ingredient();

                        // Create a new list of ingredients by removing unwanted ingredients
                        List<String> modifiedIngredients = new ArrayList<>(Arrays.asList(mealIngredients));

                        // Remove unwanted ingredients from the list
                        for (String unwanted : unwantedPrefs) {
                            modifiedIngredients.removeIf(ingredient -> ingredient.equalsIgnoreCase(unwanted));
                        }

                        // Update the meal ingredients after removal
                        meal.setIngredients(modifiedIngredients.toArray(new String[0]));

                        // Optional: Print the updated meal ingredients
                        System.out.println("Updated ingredients for " + meal.name() + ": " + Arrays.toString(meal.get_ingredient()));
                    }
                }
            }
        }

        class cheif {
            private String[] chefsmain = {"ali", "abass", "ibrahim"};
            private String[] chefother = {"anwar", "joe", "jim"};
            private boolean Validality = false;
            private String[] orderlist;


            public String[] getOrderlist() {
                return orderlist;
            }

            public void setOrderlist(String[] order) {
                order = orderlist;
            }

            public String[] getChefsmain() {
                return chefsmain;
            }

            public String[] getChefother() {
                return chefother;
            }

            public void setValidality(boolean validality) {
                Validality = validality;
            }

            public boolean getValidality() {
                return Validality;
            }
        }

        public class cheif_imp extends cheif {
            cheif_imp xy = new cheif_imp();

            void spilt_work() {
                System.out.println("do you want deseret or one of the main dishis by D / M");
                String name = input1.nextLine();
                String[] x1 = x.getPast_orders();
                if (Objects.equals(name, "D")) {
                    String[] x;
                    x = getChefother();

                    for (String chef : x) {
                        if (xy.getValidality() == true) {
                            xy.setOrderlist(x1);
                        }
                    }
                } else {
                    String[] x;
                    x = getChefsmain();
                    for (String chef : x) {
                        if (xy.getValidality() == true) {
                            xy.setOrderlist(x1);
                        }
                    }
                }
            }


        }
    }
    public static class CustomerPreferences {
        private Map<String, String> customerPreferences = new HashMap<>();


        public void setPreference(String ingredient, String preference) {
            customerPreferences.put(ingredient, preference);
        }


        public String getPreference(String ingredient) {
            return customerPreferences.getOrDefault(ingredient, "None");
        }
    }
}








