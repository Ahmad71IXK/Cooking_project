Feature: Assign tasks to chefs and kitchen staff

  Scenario: Kitchen manager assigns dessert tasks when chefs are available
    Given the system has a list of chefs
    And the system has a known list of past orders
    And the kitchen manager is assigning tasks
    And a dessert chef is marked as available
    When the manager indicates the order is for "Dessert"
    Then the dessert order should be assigned to an available dessert chef
    And the system logs the assignment decision for dessert

  Scenario: Kitchen manager assigns main dish tasks when chefs are available
    Given the system has a list of chefs
    And the system has a known list of past orders
    And the kitchen manager is assigning tasks
    And a main dish chef is marked as available
    When the manager indicates the order is for "Main"
    Then the main dish order should be assigned to an available main dish chef
    And the system logs the assignment decision for main dish

  Scenario: No task assignment if relevant chefs are not available
    Given the system has a list of chefs
    And the system has a known list of past orders
    And the kitchen manager is assigning tasks
    And a dessert chef is marked as unavailable
    When the manager indicates the order is for "Dessert"
    Then the dessert order should not be assigned
    And the system logs that no dessert chef was available