Feature: Customer Profile Management

  Scenario: Customer stores a valid dietary preference as unwanted
    Given a customer is managing their preferences
    When the customer sets "CHICKEN" as an unwanted ingredient
    Then "CHICKEN" should be recorded as an unwanted preference for the customer

  Scenario: Customer attempts to store an invalid item as an unwanted preference
    Given a customer is managing their preferences
    When the customer sets "CHOCOLATE_CAKE_FLAVOR" as an unwanted ingredient
    Then "CHOCOLATE_CAKE_FLAVOR" should not be recorded as an unwanted preference
    And the system should inform that "CHOCOLATE_CAKE_FLAVOR" is not a known ingredient

  Scenario: System checks a meal that contains an unwanted ingredient
    Given a customer is managing their preferences
    And the customer has "YOGURT" as an unwanted ingredient
    When the system checks the meal "CHICKEN_BIRYANI" for unwanted ingredients
    Then the meal "CHICKEN_BIRYANI" should be flagged as containing unwanted ingredients

  Scenario: System checks a meal that does not contain any unwanted ingredient
    Given a customer is managing their preferences
    And the customer has "EGGS" as an unwanted ingredient
    When the system checks the meal "CHICKEN_BIRYANI" for unwanted ingredients
    Then the meal "CHICKEN_BIRYANI" should not be flagged as containing unwanted ingredients

  Scenario: Customer records a past order
    Given a customer has an account
    When the customer places an order with meals "CHICKEN_BIRYANI" and "JUICE"
    Then the order containing "CHICKEN_BIRYANI" and "JUICE" should be recorded in their past orders

  Scenario: System suggests Plan A based on a liked meal from Plan A
    Given a customer profile exists
    When the system suggests a plan because the customer liked "CHICKEN_BIRYANI"
    Then Plan A containing "CHICKEN_BIRYANI", "JUICE", "AVOCADO_SALAD" should be suggested

  Scenario: System suggests Plan B based on a liked meal from Plan B
    Given a customer profile exists
    When the system suggests a plan because the customer liked "PASTA_WITH_VEGETABLES"
    Then Plan B containing "ICE_CREAM", "SUSTAINABLE_CAKE", "CAESAR_SALAD", "PASTA_WITH_VEGETABLES" should be suggested

  Scenario: System does not suggest a plan for an unassociated liked meal
    Given a customer profile exists
    When the system suggests a plan because the customer liked "SOME_OTHER_MEAL"
    Then no specific meal plan should be suggested

  Scenario: System checks for special offer on a qualifying meal
    Given a customer is browsing meals
    When the system checks if an offer is applicable for "CHICKEN_BIRYANI"
    Then an offer should be applicable
    And the system should inform about the special offer

  Scenario: System checks for special offer on a non-qualifying meal
    Given a customer is browsing meals
    When the system checks if an offer is applicable for "PASTA_WITH_VEGETABLES"
    Then an offer should not be applicable