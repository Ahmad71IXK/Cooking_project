package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Seccond_feature extends add_prefreances_customer {

    public void Addingrediantmeal(ArrayList<String> ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            return;
        }
        for (main_system.Meals meal : main_system.Meals.values()) {
            boolean mealContainsSelectedIngredient = false;
            for (String mealIngredient : meal.get_ingredient()) {
                for (String inputIngredient : ingredients) {
                    if (mealIngredient.equalsIgnoreCase(inputIngredient)) {
                        System.out.println("✅ Found ingredient '" + inputIngredient + "' in meal: " + meal.name());
                        mealContainsSelectedIngredient = true;
                        break;
                    }
                }
                if (mealContainsSelectedIngredient) {
                    break;
                }
            }
            if (mealContainsSelectedIngredient) {
                setPast_orders(new String[]{meal.name()});
                return;
            }
        }
    }

    public String Ingrediantreplace() {
        String[] unwantedPrefs = getUnwanted_pref();
        if (unwantedPrefs == null || unwantedPrefs.length == 0) {
            System.out.println("No unwanted preferences set by customer.");
            return "No unwanted preferences set";
        }

        for (String unwantedIngredientName : unwantedPrefs) {
            try {
                main_system.Subingredient subEnum = main_system.Subingredient.valueOf(unwantedIngredientName.toUpperCase());

                System.out.println("Found substitution rule for unwanted ingredient: " + unwantedIngredientName);
                String[] substitutes = subEnum.getSubstitutes();
                System.out.println("Available substitutes for " + subEnum.name() + ": " + Arrays.toString(substitutes));

                if (substitutes != null && substitutes.length > 0) {
                    System.out.println("System suggests: " + substitutes[0]);
                    return substitutes[0];
                } else {
                    System.out.println("No substitutes defined for " + subEnum.name() + ".");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No specific substitution rule found for unwanted ingredient: " + unwantedIngredientName);
            }
        }
        return "No replacement found";
    }

    public void validate(String[] Ingrediantcap) {
        String x = Ingrediantreplace();

        for (main_system.Meals dumi : main_system.Meals.values()) {
            int[] cap = dumi.getIngredientCapacity();
            if (x.equals(dumi.name()) && cap.length > 0) {
                setPast_orders(new String[]{dumi.name()});
            }
        }
    }

    public void modifyMealIngredients(String[] unwantedPrefs) {
        String[] orderNames = getPast_orders();
        if (orderNames == null || orderNames.length == 0) {
            System.out.println("No past orders to modify.");
            return;
        }
        if (unwantedPrefs == null || unwantedPrefs.length == 0) {
            System.out.println("No unwanted preferences specified for modification.");
            return;
        }

        for (String orderName : orderNames) {
            try {
                main_system.Meals mealEnumInstance = main_system.Meals.valueOf(orderName.toUpperCase());

                String[] mealIngredients = mealEnumInstance.get_ingredient();
                List<String> modifiedIngredients = new ArrayList<>(Arrays.asList(mealIngredients));
                boolean ingredientsChanged = false;

                for (String unwanted : unwantedPrefs) {
                    if (modifiedIngredients.removeIf(ingredient -> ingredient.equalsIgnoreCase(unwanted))) {
                        ingredientsChanged = true;
                        System.out.println("Removed unwanted ingredient '" + unwanted + "' from " + mealEnumInstance.name());
                    }
                }

                if (ingredientsChanged) {
                    mealEnumInstance.setIngredients(modifiedIngredients.toArray(new String[0]));
                    System.out.println("Updated ingredients for " + mealEnumInstance.name() + ": " + Arrays.toString(mealEnumInstance.get_ingredient()));
                } else {
                    System.out.println("No specified unwanted ingredients found in " + mealEnumInstance.name() + ".");
                }

            } catch (IllegalArgumentException e) {
                System.err.println("Warning: Meal with name '" + orderName + "' not found in main_system.Meals enum. Cannot modify.");
            }
        }
    }
}