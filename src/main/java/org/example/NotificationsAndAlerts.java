package org.example;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAndAlerts {

    public static class User {
        private String name;
        private String role;

        public User(String name, String role) {
            this.name = name;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }

    public static class Notification {
        private String message;

        public Notification(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static List<Notification> sendReminderNotification(User user, int hoursUntilDelivery) {
        List<Notification> notifications = new ArrayList<>();
        if (user.getRole().equals("Customer") && hoursUntilDelivery <= 24) {
            notifications.add(new Notification("Reminder: Your meal delivery is scheduled in " + hoursUntilDelivery + " hours."));
        }
        return notifications;
    }

    public static List<Notification> sendTaskNotification(User user, int hoursUntilTask) {
        List<Notification> notifications = new ArrayList<>();
        if (user.getRole().equals("Chef") && hoursUntilTask <= 24) {
            notifications.add(new Notification("Task Alert: You have a scheduled task in " + hoursUntilTask + " hours."));
        }
        return notifications;
    }

    public static List<Notification> sendLowStockAlert(User user, String ingredient, int currentStock, int threshold) {
        List<Notification> notifications = new ArrayList<>();
        if (user.getRole().equals("Manager") && currentStock < threshold) {
            notifications.add(new Notification("Low Stock Alert: " + ingredient + " is below the minimum threshold."));
        }
        return notifications;
    }

    public static List<Notification> sendOrderNotification(User user, String orderDetails) {
        List<Notification> notifications = new ArrayList<>();
        if (user.getRole().equals("Customer")) {
            notifications.add(new Notification("New Order Placed: " + orderDetails));
        }
        return notifications;
    }

    public static List<Notification> sendOrderModificationNotification(User user, String modifiedOrderDetails) {
        List<Notification> notifications = new ArrayList<>();
        if (user.getRole().equals("Customer")) {
            notifications.add(new Notification("Order Modified: " + modifiedOrderDetails));
        }
        return notifications;
    }

    public static List<Notification> sendPreferenceChangeNotification(User user, String preferenceDetails) {
        List<Notification> notifications = new ArrayList<>();
        if (user.getRole().equals("Customer")) {
            notifications.add(new Notification("Preference Updated: " + preferenceDetails));
        }
        return notifications;
    }
}
