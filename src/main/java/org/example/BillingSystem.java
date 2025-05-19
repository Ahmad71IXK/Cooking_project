package org.example;

import java.util.HashMap;
import java.util.Map;

public class BillingSystem {

    private Map<String, Double> salesTransactions;

    public BillingSystem() {
        salesTransactions = new HashMap<>();
    }


    public String generateCustomerInvoice(String item, double price) {
        salesTransactions.put(item, price);

        StringBuilder invoice = new StringBuilder();
        invoice.append("Invoice:\n");
        invoice.append("Item: ").append(item).append("\n");
        invoice.append("Price: ").append(price).append(" ₪\n");

        return invoice.toString();
    }

    public String generateFinancialReport() {
        double totalRevenue = 0.0;
        StringBuilder report = new StringBuilder();
        report.append("Financial Report:\n");
        report.append("Transaction Details:\n");

        for (Map.Entry<String, Double> entry : salesTransactions.entrySet()) {
            String item = entry.getKey();
            double price = entry.getValue();
            report.append("- ").append(item).append(": ").append(price).append(" ₪\n");
            totalRevenue += price;
        }

        report.append("\nTotal Revenue: ").append(totalRevenue).append(" ₪\n");
        return report.toString();
    }
}
