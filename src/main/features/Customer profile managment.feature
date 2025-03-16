Feature: Customer Profile Management
  Scenario: customer Store dietary preferences and allergies
    Given as a customer logged in
    When customer input his dietary preferences and allergies
    Then the system save customer preferences and allegries
    And prevent unwanted ingredients
    And the system can recommend appropriate meals
  Scenario: cheif view the customer prefrences and allergies
    Given as a chife logged in
    When chief can view customer dietary preferences
    Then the system shows the customers prefrences
    And the system shows the customers allgries
    And the chief can can customize meals accordingly
  Scenario: Customer view his past orders
    Given as a customer logged in
    When customers view his past orders
    Then system shows the past orders for specific customer
    And customer record the meals he liked
    And system saved the meals that customer liked
  Scenario: Cheif see the customer old orders
    Given as a chife logged in
    When chife acsess customers old orders
    Then The system shows the customers old orders
    And chife can suggest personalized meal plans
  Scenario: System administrator retrieves customer order history for analysis
    Given As a system admin
    When admin store and retrieve customer order history
    Then system shows order history for all customers
    And admin system can analyze trends and improve service offerings