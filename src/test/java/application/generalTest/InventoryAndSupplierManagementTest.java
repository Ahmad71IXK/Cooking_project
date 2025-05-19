package application.generalTest;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.main_system;
import org.example.InventoryManager;
import  org.example.Supplier;

import static org.junit.Assert.*;

/**
 * Cucumber step definitions for Inventory and Supplier Management feature.
 * Handles scenarios for tracking ingredient stock, generating purchase orders,
 * fetching supplier prices, and notifying managers.
 */
public class InventoryAndSupplierManagementTest {
//    private main_system.InventoryManager inventoryManager = new main_system.InventoryManager();
    private InventoryManager inventoryManager = new InventoryManager();
    private double latestPrice; // Stores the latest fetched price
    private double totalCost; // Stores the total cost of a purchase order
    private boolean priceIncreaseDetected; // Tracks if a price increase is detected

    @Given("the kitchen manager is logged in")
    public void the_kitchen_manager_is_logged_in() {
        // No logic needed for now; simulates a logged-in kitchen manager
    }

    @Given("the ingredient {string} has {int} units remaining")
    public void the_ingredient_has_units_remaining(String ingredient, Integer quantity) {
        // Update the stock level for the specified ingredient
        inventoryManager.updateStock(ingredient, quantity);
    }

    @And("the stock level of {string} is {int} units")
    public void theStockLevelOfIsUnits(String ingredient, Integer quantity) {
        // Update the stock level for the specified ingredient
        inventoryManager.updateStock(ingredient, quantity);
    }

    @And("the stock level of {string} is {int} unit")
    public void theStockLevelOfIsUnit(String ingredient, Integer quantity) {
        // Update the stock level for the specified ingredient (singular unit case)
        inventoryManager.updateStock(ingredient, quantity);
    }

    @When("the stock level is checked")
    public void the_stock_level_is_checked() {
        // No specific action needed; stock check is implicit in subsequent steps
    }

    @Then("the system should suggest restocking {string}")
    public void the_system_should_suggest_restocking(String ingredient) {
        // Check if a purchase order is generated, indicating restocking is suggested
        boolean suggestionNeeded = inventoryManager.shouldSuggestRestocking(ingredient);
        assertTrue("System should suggest restocking for " + ingredient, suggestionNeeded);
        System.out.println("Suggested restocking for " + ingredient);
    }

    @Then("the system should generate a purchase order for {string}")
    public void theSystemShouldGenerateAPurchaseOrderFor(String ingredient) {
        // Verify that a purchase order is generated for the ingredient
        boolean orderShouldBeGenerated = inventoryManager.shouldCreateActualPurchaseOrder(ingredient);
        assertTrue("Purchase order should be generated for " + ingredient, orderShouldBeGenerated);
        System.out.println("Generated purchase order for " + ingredient);
    }

    @Then("the system should notify the kitchen manager to restock {string}")
    public void theSystemShouldNotifyTheKitchenManagerToRestock(String ingredient) {
        // Verify stock level is 0 and simulate a notification
        int stockLevel = inventoryManager.checkStockLevel(ingredient);
        assertEquals("Stock level for " + ingredient + " should be 0", 0, stockLevel);
        System.out.println("Notification: Please restock " + ingredient);
    }

    @Given("the system is monitoring ingredient stock levels")
    public void the_system_is_monitoring_ingredient_stock_levels() {
        // No setup needed; monitoring is implicit in InventoryManager
    }

    @Given("the kitchen manager is preparing a purchase order for {string}")
    public void theKitchenManagerIsPreparingAPurchaseOrderFor(String ingredient) {
        // Simulate a low stock level to trigger a purchase order
        inventoryManager.updateStock(ingredient, 10);
        inventoryManager.retrieveRealTimePrices();
    }

    @When("the system fetches the latest price from the supplier")
    public void theSystemFetchesTheLatestPriceFromTheSupplier() {
        inventoryManager.retrieveRealTimePrices();
        latestPrice = inventoryManager.fetchLatestPrice("Pasta");
        System.out.println("Fetched latest price: $" + latestPrice);

        assertTrue("The latest price for Pasta should be valid", latestPrice > 0);


        double usualPrice = inventoryManager.getUsualPrice("Pasta");
        if (latestPrice > usualPrice) {
            System.out.println("Price increase detected for Pasta: " + usualPrice + " -> " + latestPrice);
        } else {
            System.out.println("No price increase detected for Pasta.");
        }
    }
    @Then("the system should display the latest price for {string}")
    public void theSystemShouldDisplayTheLatestPriceFor(String ingredient) {
        // Verify the fetched price is valid and display it
        assertTrue("Latest price for " + ingredient + " should be greater than 0", latestPrice > 0);
        System.out.println("Latest price for " + ingredient + ": $" + latestPrice);
    }

    @Given("the system has detected a low stock level for {string}")
    public void the_system_has_detected_a_low_stock_level_for(String ingredient) {
        // Simulate a low stock level and verify it
        inventoryManager.updateStock(ingredient, 5);
        int stockLevel = inventoryManager.checkStockLevel(ingredient);
        assertTrue("Stock level for " + ingredient + " should be low (<= 10)", stockLevel <= 10);
        System.out.println("Detected low stock for " + ingredient + ": " + stockLevel + " units");
    }

    @When("a purchase order is generated")
    public void a_purchase_order_is_generated() {
        // Handled in the "Then" step for generating purchase orders
    }

    @Then("the system should allow the kitchen manager to select a preferred supplier")
    public void the_system_should_allow_the_kitchen_manager_to_select_a_preferred_supplier() {
        // Verify that a supplier can be selected
        String supplier = inventoryManager.selectPreferredSupplier("Placeholder");
        assertNotNull("Preferred supplier should not be null", supplier);
        System.out.println("Selected supplier: " + supplier);
    }

    @When("the manager selects {string} from the ingredient list")
    public void the_manager_selects_from_the_ingredient_list(String ingredient) {
        // No action needed; prepares for viewing usage history
    }

    @Then("the system should display the usage history of {string}")
    public void the_system_should_display_the_usage_history_of(String ingredient) {
        // Retrieve and verify the usage history
        String usageHistory = inventoryManager.getUsageHistory(ingredient);
        assertNotNull("Usage history for " + ingredient + " should not be null", usageHistory);
        System.out.println("Usage history: " + usageHistory);
    }

    @Given("a purchase order is being generated for {string} and {string}")
    public void aPurchaseOrderIsBeingGeneratedForAnd(String ingredient1, String ingredient2) {
        // Simulate low stock for two ingredients
        inventoryManager.updateStock(ingredient1, 5);
        inventoryManager.updateStock(ingredient2, 5);
    }

    @When("the system retrieves real-time prices from suppliers")
    public void theSystemRetrievesRealTimePricesFromSuppliers() {
        inventoryManager.retrieveRealTimePrices();
        String[] ingredients = {"Pasta", "Tomato"};
        latestPrice = inventoryManager.calculateTotalCost(ingredients);
        System.out.println("Retrieved real-time prices from suppliers");
    }

    @Then("the system should calculate and display the total cost for the order")
    public void theSystemShouldCalculateAndDisplayTheTotalCostForTheOrder() {
        totalCost = latestPrice;
        assertTrue("Total cost should be greater than 0", totalCost > 0);
        System.out.println("Total cost for the order: $" + totalCost);
    }

    @When("the system detects a price increase of more than {int}% from the usual price")
    public void theSystemDetectsAPriceIncreaseOfMoreThanFromTheUsualPrice(int percentage) {
        priceIncreaseDetected = inventoryManager.detectPriceIncrease("Pasta", percentage);
        System.out.println("Checked for price increase: " + (priceIncreaseDetected ? "Detected" : "Not detected"));
    }

    @Then("the system should alert the kitchen manager of the price increase")
    public void theSystemShouldAlertTheKitchenManagerOfThePriceIncrease() {
        assertTrue("Price increase should be detected", priceIncreaseDetected);
        System.out.println("Alerted kitchen manager of price increase");
    }

}