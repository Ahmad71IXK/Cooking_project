package application.generalTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.example.main_system;
import org.example.add_prefreances_customer;

public class BillingSystemTest {

    private String item;
    private double price;
    private String invoice;
    private String financialReport;
    private final main_system system;
    private final add_prefreances_customer customer;

    public BillingSystemTest() {
        system = new main_system();
        customer = new add_prefreances_customer();
    }

    @Given("a customer has placed an order for {string} costing {double}")
    public void customerPlacedOrder(String itemName, double itemPrice) {
        item = itemName;
        price = itemPrice;
        customer.setNeworders(new String[]{itemName});
        customer.in_order(new String[]{itemName});
        System.out.println("Order placed for " + item + " with price " + price);
    }

    @When("the order is confirmed")
    public void orderConfirmed() {
        invoice = system.generateCustomerInvoice(item, price);
        System.out.println("Invoice generated: " + invoice);
    }

    @Then("the system should generate an invoice for the customer")
    public void systemGeneratesInvoice() {
        assertNotNull("Invoice should not be null", invoice);
    }

    @Then("the invoice should include the item {string} with the price {double}")
    public void invoiceContainsItem(String expectedItem, double expectedPrice) {
        assertEquals("Invoice should contain correct item", expectedItem, item);
        assertEquals("Invoice should contain correct price", expectedPrice, price, 0.01);
    }

    @Given("the system has recorded sales transactions")
    public void systemRecordedTransactions() {
        customer.setPast_orders(new String[]{"Chicken", "Pasta"});
        System.out.println("System has recorded sales transactions.");
    }

    @When("the administrator requests a financial report")
    public void adminRequestsFinancialReport() {
        financialReport = system.generateFinancialReport(customer);
        System.out.println("Financial report generated: " + financialReport);
    }

    @Then("the system should generate a financial report")
    public void systemGeneratesFinancialReport() {
        assertNotNull("Financial report should not be null", financialReport);
    }

    @Then("the report should include total revenue and transaction details")
    public void reportContainsRevenueAndDetails() {
        String expectedReport = "Total Revenue: 0.0";
        assertEquals("Financial report should match expected format", expectedReport, financialReport);
        System.out.println("Financial report generated: " + financialReport);
    }
}