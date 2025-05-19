package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner; // For building multi-line messages

import static org.example.main_system.PLAN_A_MEALS;
import static org.example.main_system.PLAN_B_MEALS;

public class First_feature {
    public add_prefreances_customer customerPreferences;

    // To store the last message produced by a method for testing
    private String lastOperationMessage = "";

    public First_feature(add_prefreances_customer customerPreferences) {
        this.customerPreferences = customerPreferences;
    }

    public String getLastOperationMessage() {
        String message = lastOperationMessage;
        lastOperationMessage = ""; // Clear after reading for one-time access
        return message;
    }

    public boolean storeUnwantedIngredient(String unwantedIngredientName) {
        lastOperationMessage = ""; // Reset message
        try {
            if (unwantedIngredientName == null || unwantedIngredientName.trim().isEmpty()) {
                lastOperationMessage = "Preference input cannot be empty.";
                System.out.println(lastOperationMessage);
                return false;
            }
        } catch (Exception e) {
            lastOperationMessage = "Preference input cannot be empty.";
            System.out.println(lastOperationMessage);
            return false;
        }

        String upperInput = unwantedIngredientName.toUpperCase();
        boolean isValidIngredient = false;
        for (main_system.Ingredient ingredientEnum : main_system.Ingredient.values()) {
            if (ingredientEnum.name().equals(upperInput)) {
                isValidIngredient = true;
                break;
            }
        }

        if (isValidIngredient) {
            customerPreferences.setUnwanted_pref(new String[]{upperInput});
            lastOperationMessage = "Preference '" + upperInput + "' saved as unwanted.";
            System.out.println(lastOperationMessage);
            return true;
        } else {
            lastOperationMessage = "The preference '" + upperInput + "' you entered is not a known ingredient.";
            System.out.println(lastOperationMessage);
            return false;
        }
    }

    // doesMealContainUnwantedIngredients still prints, but we also store a message if it returns true
    public boolean doesMealContainUnwantedIngredients(String mealName) throws IllegalArgumentException {
        lastOperationMessage = ""; // Reset message
        if (mealName == null || mealName.trim().isEmpty()) {
            throw new IllegalArgumentException("Meal name cannot be null or empty.");
        }

        if (customerPreferences.getUnwanted_pref() == null || customerPreferences.getUnwanted_pref().length == 0) {
            return false;
        }

        String upperMealName = mealName.toUpperCase();
        main_system.Meals mealEnum;

        try {
            mealEnum = main_system.Meals.valueOf(upperMealName);
        } catch (IllegalArgumentException e) {
            String errorMessage = "Meal '" + mealName + "' not found in the system's list of meals.";
            throw new IllegalArgumentException(errorMessage, e);
        }

        String[] mealIngredientsAsStrings = mealEnum.get_ingredient();
        String[] unwantedPrefs = customerPreferences.getUnwanted_pref();

        if (unwantedPrefs == null) return false;

        for (String mealIngredientStr : mealIngredientsAsStrings) {
            if (mealIngredientStr != null) {
                for (String unwanted : unwantedPrefs) {
                    if (unwanted != null && mealIngredientStr.equalsIgnoreCase(unwanted)) {
                        lastOperationMessage = "Meal '" + mealName + "' contains unwanted ingredient: " + mealIngredientStr;
                        System.out.println(lastOperationMessage);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void recordOrder(String[] order) {
        lastOperationMessage = ""; // Reset message
        if (order != null && order.length > 0) {
            String[] upperCaseOrder = new String[order.length];
            for (int i = 0; i < order.length; i++) {
                if (order[i] != null) {
                    upperCaseOrder[i] = order[i].toUpperCase();
                } else {
                    upperCaseOrder[i] = null;
                }
            }
            customerPreferences.setPast_orders(upperCaseOrder);
            // lastOperationMessage = "Order recorded."; // Optional: if you want a success message
        } else {
            lastOperationMessage = "Cannot record an empty order.";
            System.out.println(lastOperationMessage);
        }
    }

    public List<String> suggestPlanBasedOnLikedMeal(String likedMealName) {
        lastOperationMessage = ""; // Reset message
        StringJoiner sj = new StringJoiner(System.lineSeparator());

        List<String> suggestedPlan = new ArrayList<>();
        if (likedMealName == null || likedMealName.trim().isEmpty()) {
            sj.add("No specific plan suggestion for empty meal name.");
            System.out.println(sj.toString());
            lastOperationMessage = sj.toString();
            return suggestedPlan;
        }

        String upperLikedMeal = likedMealName.toUpperCase();
        List<String> planAMealsList = Arrays.asList(PLAN_A_MEALS);
        List<String> planBMealsList = Arrays.asList(PLAN_B_MEALS);

        if (upperLikedMeal.equals("CHICKEN_BIRYANI") || planAMealsList.contains(upperLikedMeal)) {
            sj.add("Based on liking " + likedMealName + ", suggesting Plan A:");
            for (String meal : PLAN_A_MEALS) {
                sj.add("- " + meal);
                suggestedPlan.add(meal);
            }
        } else if (upperLikedMeal.equals("PASTA_WITH_VEGETABLES") || planBMealsList.contains(upperLikedMeal)) {
            sj.add("Based on liking " + likedMealName + ", suggesting Plan B:");
            for (String meal : PLAN_B_MEALS) {
                sj.add("- " + meal);
                suggestedPlan.add(meal);
            }
        } else {
            sj.add("No specific plan suggestion for liked meal: " + likedMealName);
        }
        lastOperationMessage = sj.toString();
        System.out.println(lastOperationMessage); // Still print for manual verification if needed
        return suggestedPlan;
    }

    public boolean isOfferApplicable(String mealToCheck) {
        lastOperationMessage = ""; // Reset message
        if (mealToCheck == null || mealToCheck.trim().isEmpty()) {
            lastOperationMessage = "Cannot check offer for empty meal name.";
            System.out.println(lastOperationMessage);
            return false;
        }

        String upperMealName = mealToCheck.toUpperCase();
        boolean isFirstPlanAMeal = false;

        if (PLAN_A_MEALS.length > 0) {
            String firstPlanAMeal = PLAN_A_MEALS[0];
            isFirstPlanAMeal = upperMealName.equals(firstPlanAMeal);
        }

        if (isFirstPlanAMeal) {
            lastOperationMessage = "Special offer applicable for " + mealToCheck + " as part of Plan A promotion!";
            System.out.println(lastOperationMessage);
            return true;
        }

        lastOperationMessage = "No special offer applicable for " + mealToCheck;
        System.out.println(lastOperationMessage);
        return false;
    }
}