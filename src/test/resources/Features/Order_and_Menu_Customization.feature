Feature: Order and Menu Customization

  Scenario: Customer customizes a meal with preferred ingredients
    Given the customer opens the custom meal creation page
    When the customer selects ingredients according to their taste and dietary needs
    Then the system validates the selected ingredient combination
    And the meal is added to the customer's custom order

  Scenario: System suggests alternative ingredients due to dietary restrictions
    Given the customer has specified dietary restrictions
    And the selected ingredient is not suitable
    When the system detects an incompatible or unavailable ingredient
    Then it suggests alternative compatible ingredients
    And shows the suggestions to the customer

  Scenario: Chef receives alert when substitution is applied
    Given the system substitutes an ingredient based on dietary restriction
    When the order is submitted
    Then the chef receives an alert about the substitution
    And the chef can approve or modify the final recipe