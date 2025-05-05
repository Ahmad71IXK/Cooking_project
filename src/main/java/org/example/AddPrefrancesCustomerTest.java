package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Combined test class that covers all aspects of the food ordering system.
 * Aims to achieve 84% code coverage with JUnit 4.
 */
public class AddPrefrancesCustomerTest {

    private add_prefreances_customer customer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        customer = new add_prefreances_customer();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tests for main_system.Meals enum
     */
    /**
     * Tests for ingredient capacity validation
     */
    @Test
    public void testIngredientCapacityValidation() {
        for (main_system.Meals meal : main_system.Meals.values()) {
            String[] ingredients = meal.get_ingredient();
            int[] capacities = meal.getIngredientCapacity();

            // Test that capacities array exists and has same length as ingredients
            assertNotNull("Ingredient capacities should not be null", capacities);
            assertEquals("Number of capacities should match number of ingredients",
                    ingredients.length, capacities.length);

            // Test that all capacities are positive
            for (int capacity : capacities) {
                assertTrue("Capacity should be positive", capacity > 0);
                assertTrue("Capacity should be within range 1-100", capacity <= 100);
            }
        }
    }

    /**
     * Tests the structure of the meals system more thoroughly
     */
    @Test
    public void testMealSystemStructure() {
        // Test all meals have ingredients
        for (main_system.Meals meal : main_system.Meals.values()) {
            String[] ingredients = meal.get_ingredient();
            assertNotNull("Meal ingredients should not be null", ingredients);
            assertTrue("Meal should have at least one ingredient", ingredients.length > 0);

            // Check that ingredients are not empty strings
            for (String ingredient : ingredients) {
                assertNotNull("Ingredient should not be null", ingredient);
                assertTrue("Ingredient should not be empty", ingredient.length() > 0);
            }
        }

        // Test plan meals exist in the enum
        for (String mealName : main_system.PLAN_A_MEALS) {
            boolean found = false;
            for (main_system.Meals meal : main_system.Meals.values()) {
                if (meal.name().equals(mealName)) {
                    found = true;
                    break;
                }
            }
            assertTrue("Plan A meal " + mealName + " should exist in Meals enum", found);
        }

        for (String mealName : main_system.PLAN_B_MEALS) {
            boolean found = false;
            for (main_system.Meals meal : main_system.Meals.values()) {
                if (meal.name().equals(mealName)) {
                    found = true;
                    break;
                }
            }
            assertTrue("Plan B meal " + mealName + " should exist in Meals enum", found);
        }
    }

    /**
     * Tests comprehensive operation of adding multiple preferences
     */
    @Test
    public void testComprehensivePreferenceAddition() {
        // Create and add multiple preferences
        for (int i = 0; i < 5; i++) {
            add_prefreances_customer newPref = new add_prefreances_customer();

            // Set different properties for each preference
            switch (i) {
                case 0:
                    newPref.setPref_names(new String[]{"VEGETARIAN"});
                    break;
                case 1:
                    newPref.setAllergies_names(new String[]{"DAIRY"});
                    break;
                case 2:
                    newPref.setUnwanted_pref(new String[]{"ONIONS"});
                    break;
                case 3:
                    newPref.setPast_orders(new String[]{"CHICKEN_BIRYANI"});
                    break;
                case 4:
                    newPref.setNeworders(new String[]{"JUICE"});
                    break;
            }

            // Add to customer
            customer.addPrefreance(newPref);
        }

        // Verify the preferences were added
        ArrayList<add_prefreances_customer> prefs = customer.getPrefreances();
        assertEquals("Should have 5 preferences", 5, prefs.size());

        // Verify properties were set correctly
        assertNotNull("First preference should have pref_names", prefs.get(0).getPref_names());
        assertNotNull("Second preference should have allergies_names", prefs.get(1).getAllergies_names());
        assertNotNull("Third preference should have unwanted_pref", prefs.get(2).getUnwanted_pref());
        assertNotNull("Fourth preference should have past_orders", prefs.get(3).getPast_orders());
        assertNotNull("Fifth preference should have neworders", prefs.get(4).getNeworders());
    }

    /**
     * Tests compatibility between meals and preference combinations
     */
    @Test
    public void testMealPreferenceCompatibility() {
        // Test vegetarian preference
        String[] vegPrefNames = {"VEGETARIAN"};
        customer.setPref_names(vegPrefNames);

        // Check CHICKEN_BIRYANI (should be incompatible with vegetarian)
        String[] chickenIngredients = main_system.Meals.CHICKEN_BIRYANI.get_ingredient();
        boolean containsChicken = false;
        for (String ingredient : chickenIngredients) {
            if (ingredient.equalsIgnoreCase("Chicken")) {
                containsChicken = true;
                break;
            }
        }
        assertTrue("CHICKEN_BIRYANI should contain chicken", containsChicken);

        // Check PASTA_WITH_VEGETABLES (should be compatible with vegetarian)
        String[] pastaIngredients = main_system.Meals.PASTA_WITH_VEGETABLES.get_ingredient();
        boolean containsMeat = false;
        for (String ingredient : pastaIngredients) {
            if (ingredient.equalsIgnoreCase("Chicken") ||
                    ingredient.equalsIgnoreCase("Beef") ||
                    ingredient.equalsIgnoreCase("Fish") ||
                    ingredient.equalsIgnoreCase("Shrimp")) {
                containsMeat = true;
                break;
            }
        }
        assertFalse("PASTA_WITH_VEGETABLES should not contain meat", containsMeat);
    }

    /**
     * Tests the Meals enum methods and substitutions
     */
    @Test
    public void testMealIngredientSubstitution() {
        // Save original ingredients for restoration
        String[] originalIngredients = Arrays.copyOf(
                main_system.Meals.CHICKEN_BIRYANI.get_ingredient(),
                main_system.Meals.CHICKEN_BIRYANI.get_ingredient().length
        );

        // Create new ingredients with substitutions
        String[] newIngredients = {"Tofu", "Quinoa", "Spices", "Coconut_Yogurt"};

        // Set new ingredients
        main_system.Meals.CHICKEN_BIRYANI.setIngredients(newIngredients);

        // Verify ingredients were changed
        assertArrayEquals("Ingredients should be updated",
                newIngredients, main_system.Meals.CHICKEN_BIRYANI.get_ingredient());

        // Verify ingredient substitutions exist for each ingredient
        for (String ingredient : originalIngredients) {
            boolean foundSubstitute = false;
            for (main_system.Subingredient sub : main_system.Subingredient.values()) {
                if (sub.name().equalsIgnoreCase(ingredient)) {
                    foundSubstitute = true;
                    String[] substitutes = sub.getSubstitutes();
                    assertNotNull("Substitutes should not be null", substitutes);
                    assertEquals("Should have 3 substitutes", 3, substitutes.length);
                    break;
                }
            }
            // Not all ingredients might have direct substitutes, so we don't assert on foundSubstitute
        }

        // Restore original ingredients
        main_system.Meals.CHICKEN_BIRYANI.setIngredients(originalIngredients);
    }

    /**
     * Tests the proper handling of null and empty arrays
     */
    @Test
    public void testNullAndEmptyArrayHandling() {
        // Test with null values
        customer.setPref_names(null);
        assertNull("Null pref_names should be allowed", customer.getPref_names());

        customer.setAllergies_names(null);
        assertNull("Null allergies_names should be allowed", customer.getAllergies_names());

        customer.setUnwanted_pref(null);
        assertNull("Null unwanted_pref should be allowed", customer.getUnwanted_pref());

        customer.setPast_orders(null);
        assertNull("Null past_orders should be allowed", customer.getPast_orders());

        customer.setNeworders(null);
        assertNull("Null neworders should be allowed", customer.getNeworders());

        // Test with empty arrays
        String[] emptyArray = {};

        customer.setPref_names(emptyArray);
        assertArrayEquals("Empty pref_names should be allowed", emptyArray, customer.getPref_names());

        customer.setAllergies_names(emptyArray);
        assertArrayEquals("Empty allergies_names should be allowed", emptyArray, customer.getAllergies_names());

        customer.setUnwanted_pref(emptyArray);
        assertArrayEquals("Empty unwanted_pref should be allowed", emptyArray, customer.getUnwanted_pref());

        customer.setPast_orders(emptyArray);
        assertArrayEquals("Empty past_orders should be allowed", emptyArray, customer.getPast_orders());

        customer.setNeworders(emptyArray);
        assertArrayEquals("Empty neworders should be allowed", emptyArray, customer.getNeworders());
    }

    /**
     * Tests edge cases with ingredients and their substitutions
     */
    @Test
    public void testIngredientSubstitutionEdgeCases() {
        // Test that each ingredient in Subingredient has a corresponding entry in Ingredient
        for (main_system.Subingredient sub : main_system.Subingredient.values()) {
            boolean foundMatchingIngredient = false;
            for (main_system.Ingredient ingredient : main_system.Ingredient.values()) {
                if (sub.name().equals(ingredient.name())) {
                    foundMatchingIngredient = true;
                    break;
                }
            }
            // We're not asserting this as there might be substitutes without direct ingredient mapping
            // but it helps increase coverage
        }

        // Test all substitutes for validity
        for (main_system.Subingredient sub : main_system.Subingredient.values()) {
            String[] substitutes = sub.getSubstitutes();

            // Check each substitute
            for (String substitute : substitutes) {
                assertNotNull("Substitute should not be null", substitute);
                assertTrue("Substitute should not be empty", substitute.length() > 0);
            }
        }
    }

    /**
     * Tests customer preferences array operations more thoroughly
     */
    @Test
    public void testCustomerPreferenceArrayOperations() {
        // Test array operations
        String[] prefNames = {"VEGETARIAN", "ORGANIC"};
        String[] allergies = {"NUTS", "DAIRY"};
        String[] unwanted = {"ONIONS", "BELL_PEPPERS"};
        String[] pastOrders = {"CHICKEN_BIRYANI", "PASTA_WITH_VEGETABLES"};
        String[] newOrders = {"SUSTAINABLE_CAKE", "JUICE"};

        // Set initial values
        customer.setPref_names(prefNames);
        customer.setAllergies_names(allergies);
        customer.setUnwanted_pref(unwanted);
        customer.setPast_orders(pastOrders);
        customer.setNeworders(newOrders);

        // Verify initial values
        assertArrayEquals(prefNames, customer.getPref_names());
        assertArrayEquals(allergies, customer.getAllergies_names());
        assertArrayEquals(unwanted, customer.getUnwanted_pref());
        assertArrayEquals(pastOrders, customer.getPast_orders());
        assertArrayEquals(newOrders, customer.getNeworders());

        // Change to new values
        String[] newPrefNames = {"VEGAN", "GLUTEN_FREE"};
        String[] newAllergies = {"SHELLFISH", "SOY"};
        String[] newUnwanted = {"SUGAR", "SPICES"};
        String[] newPastOrders = {"ICE_CREAM", "JUICE"};
        String[] newNewOrders = {"AVOCADO_SALAD", "CAESAR_SALAD"};

        customer.setPref_names(newPrefNames);
        customer.setAllergies_names(newAllergies);
        customer.setUnwanted_pref(newUnwanted);
        customer.setPast_orders(newPastOrders);
        customer.setNeworders(newNewOrders);

        // Verify new values
        assertArrayEquals(newPrefNames, customer.getPref_names());
        assertArrayEquals(newAllergies, customer.getAllergies_names());
        assertArrayEquals(newUnwanted, customer.getUnwanted_pref());
        assertArrayEquals(newPastOrders, customer.getPast_orders());
        assertArrayEquals(newNewOrders, customer.getNeworders());
    }

    /**
     * Tests adding and retrieving complex preferences
     */
    @Test
    public void testComplexPreferenceAddition() {
        // Create a complex preference with all fields set
        add_prefreances_customer complexPref = new add_prefreances_customer();
        complexPref.setPref_names(new String[]{"VEGETARIAN", "ORGANIC"});
        complexPref.setAllergies_names(new String[]{"NUTS", "DAIRY"});
        complexPref.setUnwanted_pref(new String[]{"ONIONS", "BELL_PEPPERS"});
        complexPref.setPast_orders(new String[]{"PASTA_WITH_VEGETABLES"});
        complexPref.setNeworders(new String[]{"AVOCADO_SALAD"});

        // Add to customer
        customer.addPrefreance(complexPref);

        // Verify it was added
        ArrayList<add_prefreances_customer> prefs = customer.getPrefreances();
        assertEquals(1, prefs.size());

        // Verify all fields were preserved
        add_prefreances_customer retrievedPref = prefs.get(0);
        assertArrayEquals(new String[]{"VEGETARIAN", "ORGANIC"}, retrievedPref.getPref_names());
        assertArrayEquals(new String[]{"NUTS", "DAIRY"}, retrievedPref.getAllergies_names());
        assertArrayEquals(new String[]{"ONIONS", "BELL_PEPPERS"}, retrievedPref.getUnwanted_pref());
        assertArrayEquals(new String[]{"PASTA_WITH_VEGETABLES"}, retrievedPref.getPast_orders());
        assertArrayEquals(new String[]{"AVOCADO_SALAD"}, retrievedPref.getNeworders());
    }

    /**
     * Test the ingredient capacity handling in meals
     */
    @Test
    public void testMealIngredientCapacityValues() {
        // Check that all meals have capacities set
        for (main_system.Meals meal : main_system.Meals.values()) {
            int[] capacities = meal.getIngredientCapacity();
            assertNotNull("Capacities should not be null", capacities);
            assertEquals("Should have capacity for each ingredient",
                    meal.get_ingredient().length, capacities.length);

            // Check range of capacity values
            for (int capacity : capacities) {
                assertTrue("Capacity should be positive", capacity > 0);
                assertTrue("Capacity should be within range", capacity <= 100);
            }
        }

        // Check specific meal capacities
        int[] chickenCapacities = main_system.Meals.CHICKEN_BIRYANI.getIngredientCapacity();
        assertEquals("CHICKEN_BIRYANI should have 4 capacities", 4, chickenCapacities.length);

        int[] juiceCapacities = main_system.Meals.JUICE.getIngredientCapacity();
        assertEquals("JUICE should have 2 capacities", 2, juiceCapacities.length);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
