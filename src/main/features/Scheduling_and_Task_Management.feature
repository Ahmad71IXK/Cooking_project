Feature: Assign tasks to chefs and kitchen staff

  Scenario: Kitchen manager assigns tasks based on workload and expertise
    Given the kitchen manager is viewing the list of pending meal orders
    And the system has access to chefs' workload and expertise data
    When the manager assigns tasks to available chefs
    Then tasks are distributed based on balanced workloads and relevant expertise

  Scenario: Chef receives notification for assigned tasks
    Given a task has been assigned to a chef
    When the assignment is saved in the system
    Then the chef receives a notification with details of the cooking task
    And the chef is expected to start preparing the assigned meals
