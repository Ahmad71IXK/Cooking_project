# Cooking System Project

A comprehensive Java-based restaurant management system designed to handle billing, inventory management, customer profiles, order customization, and notifications.

## 📋 Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Building the Project](#building-the-project)
- [Running Tests](#running-tests)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **Billing System**: Generate invoices and financial reports
- **Inventory Management**: Track and manage restaurant inventory and suppliers
- **Customer Profile Management**: Add and manage customer preferences
- **Order Customization**: Customize orders and menu items
- **Notifications & Alerts**: Alert system for important events
- **Task Management**: Schedule and assign tasks within the system

## 📁 Project Structure

```
cooking_2025/
├── src/
│   ├── main/java/org/example/
│   │   ├── Main.java
│   │   ├── BillingSystem.java
│   │   ├── InventoryManager.java
│   │   ├── Supplier.java
│   │   ├── add_prefreances_customer.java
│   │   ├── NotificationsAndAlerts.java
│   │   ├── First_feature.java
│   │   ├── Seccond_feature.java
│   │   ├── Third_feature.java
│   │   └── main_system.java
│   └── test/java/application/
│       ├── ConfigurationTest.java
│       ├── generalTest/
│       │   ├── BillingSystemTest.java
│       │   ├── InventoryAndSupplierManagementTest.java
│       │   ├── CustomerProfileStepDefs.java
│       │   ├── NotificationsAndAlertsTest.java
│       │   ├── OrderCustomizationStepDefs.java
│       │   └── AssignTasksStepDefinitions.java
│       └── resources/Features/
│           ├── BillingSystem.feature
│           ├── Customer profile managment.feature
│           ├── Inventory and Supplier Management.feature
│           ├── Notifications and Alerts.feature
│           ├── Order_and_Menu_Customization.feature
│           └── Scheduling_and_Task_Management.feature
├── pom.xml
└── target/
```

## 🛠️ Technologies

- **Language**: Java
- **Build Tool**: Maven
- **Testing Framework**: Cucumber (BDD), JUnit, TestNG
- **Logging**: Logback
- **Project Model**: Maven 4.0.0

## 📦 Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher
- Git (optional, for version control)

## 🚀 Installation

1. **Clone or download the project**:
   ```bash
   git clone <repository-url>
   cd cooking_2025
   ```

2. **Verify Java installation**:
   ```bash
   java -version
   ```

3. **Verify Maven installation**:
   ```bash
   mvn -version
   ```

## 🔨 Building the Project

Build the project using Maven:

```bash
mvn clean install
```

This will:
- Clean previous builds
- Compile source code
- Run tests
- Package the application

## ✅ Running Tests

### Run all tests:
```bash
mvn test
```

### Run only Cucumber tests:
```bash
mvn test -Dgroups=cucumber
```

### Run specific test class:
```bash
mvn test -Dtest=BillingSystemTest
```

### View test report:
After running tests, view the Cucumber HTML report:
```
target/cucumber/test-summary.html
```

## 💻 Usage

### Running the main application:
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### Example: Billing System
```java
BillingSystem billing = new BillingSystem();
String invoice = billing.generateCustomerInvoice("Pasta Carbonara", 45.99);
String report = billing.generateFinancialReport();
```

### Example: Inventory Management
```java
InventoryManager inventory = new InventoryManager();
// Add inventory items
// Track suppliers
```

### Example: Customer Preferences
```java
add_prefreances_customer preferences = new add_prefreances_customer();
// Add customer preferences
// Manage dietary restrictions
```

## 🤝 Contributing

1. Create a feature branch (`git checkout -b feature/AmazingFeature`)
2. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
3. Push to the branch (`git push origin feature/AmazingFeature`)
4. Open a Pull Request

### Code Style Guidelines
- Follow Java naming conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Keep methods focused and concise
- Write tests for new features

## 📝 Behavior-Driven Development (BDD)

This project uses Cucumber for BDD. Feature files are written in Gherkin language:

```gherkin
Feature: Billing System
  Scenario: Generate customer invoice
    Given the billing system is initialized
    When the customer purchases an item
    Then an invoice should be generated
```

## 📦 Dependencies

Main dependencies managed in `pom.xml`:
- **Cucumber**: 7.3.4 (Behavior-Driven Development)
- **JUnit**: 4.13.2 (Unit Testing)
- **TestNG**: 7.11.0 (Testing Framework)
- **Logback**: Latest (Logging)

## 🐛 Troubleshooting

- **Maven issues**: Run `mvn clean` before rebuilding
- **Test failures**: Check that all dependencies are installed with `mvn dependency:resolve`
- **Java version mismatch**: Ensure your JDK version matches the project requirements

## 📄 License

This project is provided as-is for educational and commercial use.

## 📧 Contact & Support

For questions or issues, please contact the development team or open an issue in the repository.

---

**Last Updated**: February 17, 2026
