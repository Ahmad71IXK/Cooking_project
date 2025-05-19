package org.example;

import java.util.Random;

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

        public static void resetAllMealsState() {
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
            BEEF,
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
}
