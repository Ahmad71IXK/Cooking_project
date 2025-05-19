package application.generalTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import org.example.First_feature;
import org.example.add_prefreances_customer;
// No @After or System.out capturing needed if all messages are returned

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerProfileStepDefs {

    private add_prefreances_customer currentCustomerPreferences;
    private First_feature featureLogic;
    private boolean unwantedStoreResult;
    private boolean mealContainsUnwantedResult;
    private List<String> suggestedPlan;
    private boolean offerApplicableResult;
    private String lastMessageFromFeature; // To store message from feature logic

    @Before
    public void setUp() {
        currentCustomerPreferences = new add_prefreances_customer();
        featureLogic = new First_feature(currentCustomerPreferences);
        unwantedStoreResult = false;
        mealContainsUnwantedResult = false;
        suggestedPlan = null;
        offerApplicableResult = false;
        lastMessageFromFeature = "";
    }

    @Given("a customer is managing their preferences")
    public void a_customer_is_managing_their_preferences() {
    }

    @Given("a customer has an account")
    public void a_customer_has_an_account() {
    }

    @Given("a customer profile exists")
    public void a_customer_profile_exists() {
    }

    @Given("a customer is browsing meals")
    public void a_customer_is_browsing_meals() {
    }

    @When("the customer sets {string} as an unwanted ingredient")
    public void the_customer_sets_as_an_unwanted_ingredient(String ingredient) {
        unwantedStoreResult = featureLogic.storeUnwantedIngredient(ingredient);
        lastMessageFromFeature = featureLogic.getLastOperationMessage();
    }

    @Then("{string} should be recorded as an unwanted preference for the customer")
    public void should_be_recorded_as_an_unwanted_preference(String expectedIngredient) {
        assertTrue("Ingredient '" + expectedIngredient + "' should have been stored successfully. Message: [" + lastMessageFromFeature.trim() + "]", unwantedStoreResult);
        assertNotNull("Unwanted preferences array should not be null.", currentCustomerPreferences.getUnwanted_pref());
        assertTrue("Unwanted preferences array should not be empty.", currentCustomerPreferences.getUnwanted_pref().length > 0);
        assertEquals("The recorded unwanted ingredient should be '" + expectedIngredient.toUpperCase() + "'.",
                expectedIngredient.toUpperCase(), currentCustomerPreferences.getUnwanted_pref()[0]);
        assertTrue("System message should confirm preference saved. Message: [" + lastMessageFromFeature.trim() + "]",
                lastMessageFromFeature.contains("Preference '" + expectedIngredient.toUpperCase() + "' saved as unwanted."));
    }

    @Then("{string} should not be recorded as an unwanted preference")
    public void should_not_be_recorded_as_an_unwanted_preference(String ingredient) {
        assertFalse("Ingredient '" + ingredient + "' should not have been stored.", unwantedStoreResult);
        assertNull("Unwanted preferences should be null if storage failed.",
                currentCustomerPreferences.getUnwanted_pref());
    }

    @Then("the system should inform that {string} is not a known ingredient")
    public void the_system_should_inform_that_is_not_a_known_ingredient(String ingredient) {
        assertTrue("System message should inform that '" + ingredient.toUpperCase() + "' is not a known ingredient. Message was: [" + lastMessageFromFeature.trim() + "]",
                lastMessageFromFeature.contains("The preference '" + ingredient.toUpperCase() + "' you entered is not a known ingredient."));
    }

    @Given("the customer has {string} as an unwanted ingredient")
    public void the_customer_has_as_an_unwanted_ingredient(String ingredient) {
        featureLogic.storeUnwantedIngredient(ingredient);
        lastMessageFromFeature = featureLogic.getLastOperationMessage(); // Capture message from this setup
    }

    @When("the system checks the meal {string} for unwanted ingredients")
    public void the_system_checks_the_meal_for_unwanted_ingredients(String mealName) {
        mealContainsUnwantedResult = featureLogic.doesMealContainUnwantedIngredients(mealName);
        lastMessageFromFeature = featureLogic.getLastOperationMessage(); // Capture message if any
    }

    @Then("the meal {string} should be flagged as containing unwanted ingredients")
    public void the_meal_should_be_flagged_as_containing_unwanted_ingredients(String mealName) {
        assertTrue("Meal '" + mealName + "' should have been flagged. Message: [" + lastMessageFromFeature.trim() + "]", mealContainsUnwantedResult);
        assertTrue("System message should indicate meal contains unwanted. Message: [" + lastMessageFromFeature.trim() + "]",
                lastMessageFromFeature.contains("Meal '" + mealName + "' contains unwanted ingredient:"));
    }

    @Then("the meal {string} should not be flagged as containing unwanted ingredients")
    public void the_meal_should_not_be_flagged_as_containing_unwanted_ingredients(String mealName) {
        assertFalse("Meal '" + mealName + "' should NOT have been flagged. Message: [" + lastMessageFromFeature.trim() + "]", mealContainsUnwantedResult);
        assertFalse("System message should NOT indicate unwanted. Message: [" + lastMessageFromFeature.trim() + "]",
                lastMessageFromFeature.contains("Meal '" + mealName + "' contains unwanted ingredient:"));
    }

    @When("the customer places an order with meals {string} and {string}")
    public void the_customer_places_an_order_with_meals_and(String meal1, String meal2) {
        featureLogic.recordOrder(new String[]{meal1, meal2});
        lastMessageFromFeature = featureLogic.getLastOperationMessage();
    }

    @Then("the order containing {string} and {string} should be recorded in their past orders")
    public void the_order_containing_and_should_be_recorded_in_their_past_orders(String meal1, String meal2) {
        String[] pastOrders = currentCustomerPreferences.getPast_orders();
        assertNotNull("Past orders should not be null", pastOrders);
        assertEquals("Past orders should contain 2 items", 2, pastOrders.length);
        assertEquals("First meal in past order should match", meal1.toUpperCase(), pastOrders[0]);
        assertEquals("Second meal in past order should match", meal2.toUpperCase(), pastOrders[1]);
    }

    @When("the system suggests a plan because the customer liked {string}")
    public void the_system_suggests_a_plan_because_the_customer_liked(String likedMeal) {
        suggestedPlan = featureLogic.suggestPlanBasedOnLikedMeal(likedMeal);
        lastMessageFromFeature = featureLogic.getLastOperationMessage();
    }

    @Then("Plan A containing {string}, {string}, {string} should be suggested")
    public void plan_A_containing_should_be_suggested(String m1, String m2, String m3) {
        assertNotNull("Suggested plan should not be null", suggestedPlan);
        List<String> expectedMeals = Arrays.asList(m1, m2, m3);
        assertEquals("Suggested plan size does not match Plan A.", expectedMeals.size(), suggestedPlan.size());
        assertTrue("Suggested plan does not contain all expected meals for Plan A. Expected: " + expectedMeals + ", Got: " + suggestedPlan,
                suggestedPlan.containsAll(expectedMeals) && expectedMeals.containsAll(suggestedPlan));

        assertTrue("System message should suggest Plan A. Message: [" + lastMessageFromFeature.trim() + "]", lastMessageFromFeature.contains("suggesting Plan A:"));
        for (String meal : expectedMeals) {
            assertTrue("Message for Plan A should contain meal: " + meal + ". Message: [" + lastMessageFromFeature.trim() + "]", lastMessageFromFeature.contains("- " + meal));
        }
    }

    @Then("Plan B containing {string}, {string}, {string}, {string} should be suggested")
    public void plan_B_containing_should_be_suggested(String m1, String m2, String m3, String m4) {
        assertNotNull("Suggested plan should not be null", suggestedPlan);
        List<String> expectedMeals = Arrays.asList(m1, m2, m3, m4);
        assertEquals("Suggested plan size does not match Plan B.", expectedMeals.size(), suggestedPlan.size());
        assertTrue("Suggested plan does not contain all expected meals for Plan B. Expected: " + expectedMeals + ", Got: " + suggestedPlan,
                suggestedPlan.containsAll(expectedMeals) && expectedMeals.containsAll(suggestedPlan));

        assertTrue("System message should suggest Plan B. Message: [" + lastMessageFromFeature.trim() + "]", lastMessageFromFeature.contains("suggesting Plan B:"));
        for (String meal : expectedMeals) {
            assertTrue("Message for Plan B should contain meal: " + meal + ". Message: [" + lastMessageFromFeature.trim() + "]", lastMessageFromFeature.contains("- " + meal));
        }
    }

    @Then("no specific meal plan should be suggested")
    public void no_specific_meal_plan_should_be_suggested() {
        assertNotNull("Suggested plan list should exist (even if empty)", suggestedPlan);
        assertTrue("Suggested plan should be empty when no specific plan is found.", suggestedPlan.isEmpty());
        assertTrue("System message should indicate no specific suggestion. Message: [" + lastMessageFromFeature.trim() + "]", lastMessageFromFeature.contains("No specific plan suggestion for liked meal: SOME_OTHER_MEAL"));
    }

    @When("the system checks if an offer is applicable for {string}")
    public void the_system_checks_if_an_offer_is_applicable_for(String mealName) {
        offerApplicableResult = featureLogic.isOfferApplicable(mealName);
        lastMessageFromFeature = featureLogic.getLastOperationMessage();
    }

    @Then("an offer should be applicable")
    public void an_offer_should_be_applicable() {
        assertTrue("Offer should be applicable.", offerApplicableResult);
    }

    @Then("the system should inform about the special offer")
    public void the_system_should_inform_about_the_special_offer() {
        assertTrue("System message should mention the special offer. Message: [" + lastMessageFromFeature.trim() + "]",
                lastMessageFromFeature.contains("Special offer applicable for ") && lastMessageFromFeature.contains(" as part of Plan A promotion!"));
    }

    @Then("an offer should not be applicable")
    public void an_offer_should_not_be_applicable() {
        assertFalse("Offer should not be applicable.", offerApplicableResult);
        assertTrue("System message should state no special offer is applicable. Message: [" + lastMessageFromFeature.trim() + "]",
                lastMessageFromFeature.contains("No special offer applicable for "));
    }
}