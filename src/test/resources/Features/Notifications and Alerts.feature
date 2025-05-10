Feature: Notifications and Alerts
  Scenario: Send reminders for upcoming orders and deliveries
    Given a customer with an upcoming meal delivery
    When the delivery is scheduled within the next 24 hours
    Then the customer should receive a reminder notification

  Scenario: Notify chef of scheduled cooking tasks
    Given a chef with scheduled cooking tasks
    When a task is scheduled within the next 24 hours
    Then the chef should receive a task notification

  Scenario: Notify users of low-stock ingredients
    Given a kitchen manager
    And the stock level of an ingredient is below the minimum threshold
    When the stock is checked
    Then the kitchen manager should receive a low-stock alert
