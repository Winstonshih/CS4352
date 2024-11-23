package com.example.myapplication.Services;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class userIntroServices {
    private SharedPreferences moneyTracker, subscriptions, user;
    private Long income, expenses;
    private String username, password;

    public userIntroServices(Context context) {
        moneyTracker = context.getSharedPreferences("money_tracker", Context.MODE_PRIVATE);
        subscriptions = context.getSharedPreferences("subscription_data", Context.MODE_PRIVATE);
        // Initialize user preferences for username and password
        user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        username = user.getString("username", "");
        password = user.getString("password", "");

        // Initialize income and expenses
        income = moneyTracker.getLong("income", 0);
        expenses = calculateExpenses();
    }

    // Calculate the total expenses
    private Long calculateExpenses() {
        long total = 0;

        // Loop through moneyTracker and sum up expenses (excluding income)
        for (Map.Entry<String, ?> entry : moneyTracker.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!key.toLowerCase().contains("income")) {
                if (value instanceof Integer) {
                    total += (Integer) value;
                } else if (value instanceof Long) {
                    total += (Long) value;
                }
            }
        }

        // Add subscription costs
        for (Map.Entry<String, ?> entry : subscriptions.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Long) {
                total += (Long) value;
            } else if (value instanceof Integer) {
                total += (Integer) value;
            }
        }
        return total;
    }

    // Getters for income, expenses, amount saved, username, and password
    public Long getIncome() {
        return income;
    }

    public Long getExpenses() {
        return expenses;
    }

    // Calculate the amount saved based on income and expenses
    public Long getAmountSaved() {
        return income - expenses;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    //print everything inside the shared preferences
    public void printSharedPreferences() {
        System.out.println("Money Tracker:");
        Map<String, ?> allEntries = moneyTracker.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Subscriptions:");
        Map<String, ?> allEntries2 = subscriptions.getAll();
        for (Map.Entry<String, ?> entry : allEntries2.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("User:");
        Map<String, ?> allEntries3 = user.getAll();
        for (Map.Entry<String, ?> entry : allEntries3.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
