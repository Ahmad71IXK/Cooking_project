Feature: Inventory and Supplier Management

  Scenario: Track available ingredients and suggest restocking
    Given the kitchen manager is logged in
    And the ingredient "Tomato" has 2 units remaining
    When the stock level is checked
    Then the system should suggest restocking "Tomato"

  Scenario: Automatic restocking suggestion when stock is low
    Given the system is monitoring ingredient stock levels
    And the stock level of "Basil" is 2 units
    When the stock level is checked
    Then the system should suggest restocking "Basil"

  Scenario: Integrate with suppliers for real-time pricing
    Given the kitchen manager is preparing a purchase order for "Olive Oil"
    When the system fetches the latest price from the supplier
    Then the system should display the latest price for "Olive Oil"

  Scenario: Automatic purchase order generation when stock is critically low
    Given the system is monitoring ingredient stock levels
    And the stock level of "Pasta" is 1 unit
    When the stock level is checked
    Then the system should generate a purchase order for "Pasta"

  Scenario: Notification to kitchen manager for critical stock levels
    Given the system is monitoring ingredient stock levels
    And the stock level of "Garlic" is 0 units
    When the stock level is checked
    Then the system should notify the kitchen manager to restock "Garlic"

  Scenario: Viewing ingredient usage history
    Given the kitchen manager is logged in
    When the manager selects "Tomato" from the ingredient list
    Then the system should display the usage history of "Tomato"

  Scenario: Supplier selection for purchase orders
    Given the system has detected a low stock level for "Olive Oil"
    When a purchase order is generated
    Then the system should allow the kitchen manager to select a preferred supplier

  Scenario: Automated cost calculation for restocking
    Given a purchase order is being generated for "Pasta" and "Tomato"
    When the system retrieves real-time prices from suppliers
    Then the system should calculate and display the total cost for the order

  Scenario: Alert for price increase from suppliers
    Given the kitchen manager is preparing a purchase order for "Basil"
    When the system detects a price increase of more than 10% from the usual price
    Then the system should alert the kitchen manager of the price increase
