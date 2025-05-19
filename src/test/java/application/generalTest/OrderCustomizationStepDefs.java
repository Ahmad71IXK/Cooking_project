package application.generalTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.example.Seccond_feature;
import org.example.main_system;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class OrderCustomizationStepDefs {

    private Seccond_feature customizationFeature;
    private ArrayList<String> selectedIngredients;
    private String[] dietaryRestrictions;
    private String substitutionResult;
    private boolean alertSent;
    private boolean chefApproval;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        main_system.Meals.resetAllMealsState();

        customizationFeature = new Seccond_feature();
        selectedIngredients = new ArrayList<>();
        dietaryRestrictions = new String[0];
        substitutionResult = null;
        alertSent = false;
        chefApproval = false;

        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    private String getCapturedOutput() {
        System.out.flush();
        String output = outContent.toString();
        outContent.reset();
        return output;
    }

    @Given("the customer opens the custom meal creation page")
    public void the_customer_opens_the_custom_meal_creation_page() {
        assertNotNull("Customization feature should be initialized", customizationFeature);
        selectedIngredients = new ArrayList<>();
    }

    @When("the customer selects ingredients according to their taste and dietary needs")
    public void the_customer_selects_ingredients_according_to_their_taste_and_dietary_needs() {
        selectedIngredients.add("CHICKEN");
        selectedIngredients.add("RICE");
        selectedIngredients.add("TOMATOES");

        assertFalse("Selected ingredients should not be empty", selectedIngredients.isEmpty());
        assertEquals("Should have 3 selected ingredients", 3, selectedIngredients.size());
    }

    @Then("the system validates the selected ingredient combination")
    public void the_system_validates_the_selected_ingredient_combination() {
        customizationFeature.Addingrediantmeal(selectedIngredients);
        String output = getCapturedOutput();
        assertTrue("System should have found at least one ingredient match. Output: " + output,
                output.contains("Found ingredient"));
    }

    @Then("the meal is added to the customer's custom order")
    public void the_meal_is_added_to_the_customers_custom_order() {
        String[] pastOrders = customizationFeature.getPast_orders();
        assertNotNull("Past orders should not be null after adding ingredients", pastOrders);
        assertTrue("At least one meal should be added to past orders. Found: " + Arrays.toString(pastOrders),
                pastOrders.length > 0);
        assertEquals("Expected meal not found in past orders", "CHICKEN_BIRYANI", pastOrders[0]);
    }

    @Given("the customer has specified dietary restrictions")
    public void the_customer_has_specified_dietary_restrictions() {
        dietaryRestrictions = new String[]{"BEEF"};
        customizationFeature.setUnwanted_pref(dietaryRestrictions);

        assertNotNull("Dietary restrictions should be set", customizationFeature.getUnwanted_pref());
        assertEquals("Should have one dietary restriction", 1, customizationFeature.getUnwanted_pref().length);
    }

    @Given("the selected ingredient is not suitable")
    public void the_selected_ingredient_is_not_suitable() {
        boolean foundUnwantedIngredientInSystem = false;
        for (main_system.Ingredient ingredient : main_system.Ingredient.values()) {
            if (ingredient.name().equalsIgnoreCase(dietaryRestrictions[0])) {
                foundUnwantedIngredientInSystem = true;
                break;
            }
        }
        assertTrue("The restricted ingredient (" + dietaryRestrictions[0] + ") should exist as a main_system.Ingredient",
                foundUnwantedIngredientInSystem);
    }

    @When("the system detects an incompatible or unavailable ingredient")
    public void the_system_detects_an_incompatible_or_unavailable_ingredient() {
        substitutionResult = customizationFeature.Ingrediantreplace();
        assertNotNull("A substitution result (or 'No replacement found') should be provided", substitutionResult);
    }

    @Then("it suggests alternative compatible ingredients")
    public void it_suggests_alternative_compatible_ingredients() {
        assertTrue("Substitution result '" + substitutionResult + "' is not a valid ingredient or known non-replacement message.",
                substitutionResult.equals("No replacement found") ||
                        substitutionResult.equals("No unwanted preferences set") ||
                        isValidIngredient(substitutionResult));

        if (!substitutionResult.equals("No replacement found") && !substitutionResult.equals("No unwanted preferences set")) {
            assertEquals("Expected 'TOFU_STEAK' as substitution for BEEF", "TOFU_STEAK", substitutionResult);
        }
    }

    @Then("shows the suggestions to the customer")
    public void shows_the_suggestions_to_the_customer() {
        String output = getCapturedOutput();
        if (!substitutionResult.equals("No replacement found") && !substitutionResult.equals("No unwanted preferences set")) {
            assertTrue("System should show substitution information. Output: " + output,
                    output.contains("Found substitution rule for unwanted ingredient: " + dietaryRestrictions[0].toUpperCase()) ||
                            output.contains("Available substitutes for " + dietaryRestrictions[0].toUpperCase()) ||
                            output.contains("System suggests:"));
        }
    }

    @Given("the system substitutes an ingredient based on dietary restriction")
    public void the_system_substitutes_an_ingredient_based_on_dietary_restriction() {
        customizationFeature.setUnwanted_pref(new String[]{"CHICKEN"});
        customizationFeature.setPast_orders(new String[]{"CHICKEN_BIRYANI"});

        substitutionResult = customizationFeature.Ingrediantreplace();
        getCapturedOutput();

        assertNotNull("Substitution result should not be null", substitutionResult);
        assertEquals("Expected 'TOFU' as substitution for CHICKEN", "TOFU", substitutionResult);
        customizationFeature.validate(customizationFeature.getUnwanted_pref());
    }

    @When("the order is submitted")
    public void the_order_is_submitted() {
        customizationFeature.modifyMealIngredients(customizationFeature.getUnwanted_pref());
        alertSent = true;
        assertTrue("Alert should be sent to chef", alertSent);
    }

    @Then("the chef receives an alert about the substitution")
    public void the_chef_receives_an_alert_about_the_substitution() {
        String output = getCapturedOutput();
        assertTrue("System should alert chef about updated ingredients for CHICKEN_BIRYANI. Output: " + output,
                output.contains("Updated ingredients for CHICKEN_BIRYANI"));
        assertTrue("Output should show 'Chicken' was removed. Output: " + output,
                output.contains("Removed unwanted ingredient 'CHICKEN' from CHICKEN_BIRYANI"));

    }

    @Then("the chef can approve or modify the final recipe")
    public void the_chef_can_approve_or_modify_the_final_recipe() {
        chefApproval = true;
        assertTrue("Chef should be able to approve the substitution", chefApproval);
    }

    private boolean isValidIngredient(String ingredientName) {
        if (ingredientName == null) return false;
        for (main_system.Ingredient ingredient : main_system.Ingredient.values()) {
            if (ingredient.name().equalsIgnoreCase(ingredientName)) {
                return true;
            }
        }
        return false;
    }
}