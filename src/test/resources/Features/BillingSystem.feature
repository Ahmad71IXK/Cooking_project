Feature: Billing System

  Scenario: Customer receives an invoice after placing an order
    Given a customer has placed an order for "Chicken" costing 25.0
    When the order is confirmed
    Then the system should generate an invoice for the customer
    And the invoice should include the item "Chicken" with the price 25.0

  Scenario: System administrator generates financial reports
    Given the system has recorded sales transactions
    When the administrator requests a financial report
    Then the system should generate a financial report
    And the report should include total revenue and transaction details
