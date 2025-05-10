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
}
