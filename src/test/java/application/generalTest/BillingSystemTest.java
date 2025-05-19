package application.generalTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.example.BillingSystem;


public class BillingSystemTest {

    private BillingSystem billingSystem;
    private String item;
    private double price;
    private String invoice;
    private String financialReport;

    public BillingSystemTest() {
        billingSystem = new BillingSystem();
    }

    @Given("a customer has placed an order for {string} costing {double}")
    public void customerPlacedOrder(String itemName, double itemPrice) {
        item = itemName;
        price = itemPrice;
        invoice = billingSystem.generateCustomerInvoice(item, price);
        System.out.println("Order placed for " + item + " with price " + price);
    }

    @When("the order is confirmed")
    public void orderConfirmed() {
        System.out.println("Order confirmed. Invoice generated: \n" + invoice);
    }

    @Then("the system should generate an invoice for the customer")
    public void systemGeneratesInvoice() {
        assertNotNull("Invoice should not be null", invoice);
    }

    @Then("the invoice should include the item {string} with the price {double}")
    public void invoiceContainsItem(String expectedItem, double expectedPrice) {
        String expectedInvoice = "Invoice:\nItem: " + expectedItem + "\nPrice: " + expectedPrice + " ₪\n";
        assertEquals("Invoice content should match expected format", expectedInvoice, invoice);
    }

    @Given("the system has recorded sales transactions")
    public void systemRecordedTransactions() {
        // Simulate previous orders
        billingSystem.generateCustomerInvoice("Chicken", 25.0);
        billingSystem.generateCustomerInvoice("Pasta", 15.0);
        billingSystem.generateCustomerInvoice("Salad", 10.0);
        System.out.println("Sales transactions recorded.");
    }

    @When("the administrator requests a financial report")
    public void adminRequestsFinancialReport() {
        financialReport = billingSystem.generateFinancialReport();
        System.out.println("Financial report generated: \n" + financialReport);
    }

    @Then("the system should generate a financial report")
    public void systemGeneratesFinancialReport() {
        assertNotNull("Financial report should not be null", financialReport);
    }

    @Then("the report should include total revenue and transaction details")
    public void reportContainsRevenueAndDetails() {
        String expectedReport =
                "Financial Report:\n" +
                        "Transaction Details:\n" +
                        "- Chicken: 25.0 ₪\n" +
                        "- Pasta: 15.0 ₪\n" +
                        "- Salad: 10.0 ₪\n\n" +
                        "Total Revenue: 50.0 ₪\n";

        assertEquals("Financial report should match expected format", expectedReport, financialReport);
    }
}
