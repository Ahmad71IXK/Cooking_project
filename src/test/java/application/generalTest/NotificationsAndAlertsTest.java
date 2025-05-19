package application.generalTest;

import org.example.NotificationsAndAlerts;
import org.example.NotificationsAndAlerts.User;
import org.example.NotificationsAndAlerts.Notification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class NotificationsAndAlertsTest {

    private User customer;
    private User chef;
    private User manager;
    private List<Notification> notifications;

    @Given("a customer with an upcoming meal delivery")
    public void a_customer_with_an_upcoming_meal_delivery() {
        customer = new User("John Doe", "Customer");
    }

    @When("the delivery is scheduled within the next {int} hours")
    public void the_delivery_is_scheduled_within_the_next_hours(Integer hours) {
        notifications = NotificationsAndAlerts.sendReminderNotification(customer, hours);
    }

    @Then("the customer should receive a reminder notification")
    public void the_customer_should_receive_a_reminder_notification() {
        Assert.assertFalse("No notifications received.", notifications.isEmpty());
        Assert.assertTrue("Reminder notification not found.", notifications.get(0).getMessage().contains("Reminder"));
    }

    @Given("a chef with scheduled cooking tasks")
    public void a_chef_with_scheduled_cooking_tasks() {
        chef = new User("Chef Mike", "Chef");
    }

    @When("a task is scheduled within the next {int} hours")
    public void a_task_is_scheduled_within_the_next_hours(Integer hours) {
        notifications = NotificationsAndAlerts.sendTaskNotification(chef, hours);
    }

    @Then("the chef should receive a task notification")
    public void the_chef_should_receive_a_task_notification() {
        Assert.assertFalse("No notifications received.", notifications.isEmpty());
        Assert.assertTrue("Task notification not found.", notifications.get(0).getMessage().contains("Task Alert"));
    }

    @Given("a kitchen manager")
    public void a_kitchen_manager() {
        manager = new User("Manager Tom", "Manager");
    }

    @Given("the stock level of an ingredient is below the minimum threshold")
    public void the_stock_level_of_an_ingredient_is_below_the_minimum_threshold() {
        // This step is handled in the next step
    }

    @When("the stock is checked")
    public void the_stock_is_checked() {
        notifications = NotificationsAndAlerts.sendLowStockAlert(manager, "Tomatoes", 5, 10);
    }

    @Then("the kitchen manager should receive a low-stock alert")
    public void the_kitchen_manager_should_receive_a_low_stock_alert() {
        Assert.assertFalse("No notifications received.", notifications.isEmpty());
        Assert.assertTrue("Low Stock Alert notification not found.", notifications.get(0).getMessage().contains("Low Stock Alert"));
    }
    @Given("a customer who has placed a new order")
    public void a_customer_who_has_placed_a_new_order() {
        customer = new User("John Doe", "Customer");
    }

    @When("a new order is placed with details {string}")
    public void a_new_order_is_placed_with_details(String orderDetails) {
        notifications = NotificationsAndAlerts.sendOrderNotification(customer, orderDetails);
    }

    @Then("the customer should receive a new order notification")
    public void the_customer_should_receive_a_new_order_notification() {
        Assert.assertFalse("No notifications received.", notifications.isEmpty());
        Assert.assertTrue("New Order notification not found.", notifications.get(0).getMessage().contains("New Order Placed"));
    }

    @Given("a customer who has modified an order")
    public void a_customer_who_has_modified_an_order() {
        customer = new User("John Doe", "Customer");
    }

    @When("the order is modified with details {string}")
    public void the_order_is_modified_with_details(String modifiedOrderDetails) {
        notifications = NotificationsAndAlerts.sendOrderModificationNotification(customer, modifiedOrderDetails);
    }

    @Then("the customer should receive an order modification notification")
    public void the_customer_should_receive_an_order_modification_notification() {
        Assert.assertFalse("No notifications received.", notifications.isEmpty());
        Assert.assertTrue("Order Modification notification not found.", notifications.get(0).getMessage().contains("Order Modified"));
    }

    @Given("a customer who has changed their preferences")
    public void a_customer_who_has_changed_their_preferences() {
        customer = new User("John Doe", "Customer");
    }

    @When("the preferences are updated with details {string}")
    public void the_preferences_are_updated_with_details(String preferenceDetails) {
        notifications = NotificationsAndAlerts.sendPreferenceChangeNotification(customer, preferenceDetails);
    }

    @Then("the customer should receive a preference change notification")
    public void the_customer_should_receive_a_preference_change_notification() {
        Assert.assertFalse("No notifications received.", notifications.isEmpty());
        Assert.assertTrue("Preference Change notification not found.", notifications.get(0).getMessage().contains("Preference Updated"));
    }
}
