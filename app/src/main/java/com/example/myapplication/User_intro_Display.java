package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class User_intro_Display extends AppCompatActivity {
    //what we are going to update in the display
    TextView income;
    TextView expenses;
    TextView amount;
    Button startButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_intro_display);
        income = findViewById(R.id.income);
        expenses = findViewById(R.id.expenses);
        amount = findViewById(R.id.amount);
        startButton = findViewById(R.id.startButton);

        //update the page with the current information we have stored
        //first access shared prefference (alaways iside On Create to prevent issues)
        SharedPreferences sharedPreferences = getSharedPreferences("money_tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences preferences = getSharedPreferences("subscriptions", MODE_PRIVATE);
        SharedPreferences.Editor editor_s = preferences.edit();
        //to get all expenses
        Map<String, ?> money_tracker = sharedPreferences.getAll();
        Map<String, ?> subscriptions = preferences.getAll();
        //update the income and expenses
        long income_have = sharedPreferences.getLong("income",0);
        //income
        income.setText("$ " + String.valueOf(income_have));
        //loop for expenses
        long total = 0; // Initialize total as long

        // Loop through the money tracker
        for (Map.Entry<String, ?> entry : money_tracker.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Check if the key does not contain "income"
            if (!key.toLowerCase().contains("income")) {
                System.out.println("Key: " + key + ", Value: " + value);
                // Check if the value is an Integer
                if (value instanceof Integer) {
                    total += (Integer) value; // Add the integer value to total
                }
                // Check if the value is a Long
                else if (value instanceof Long) {
                    total += (Long) value; // Add the long value to total
                }
            }
        }
        //loop thorugh subscriptions
        for (Map.Entry<String, ?> entry : subscriptions.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Check if the key does not contain "income"
            if (!key.toLowerCase().contains("income")) {
                System.out.println("Key: " + key + ", Value: " + value);
                // Check if the value is an Integer
                if (value instanceof Integer) {
                    total += (Integer) value; // Add the integer value to total
                }
                // Check if the value is a Long
                else if (value instanceof Long) {
                    total += (Long) value; // Add the long value to total
                }
            }
        }
        //expensense
        expenses.setText("$ " + String.valueOf(total));
        //have an equation
        long amount_saved = income_have - total;
        if(amount_saved > 0){
            amount.setTextColor(getResources().getColor(R.color.dark_green));
            amount.setText("$ " + String.valueOf(amount_saved));
        }
        else if (amount_saved < 0){
            amount.setTextColor(getResources().getColor(R.color.dark_red));
            amount.setText("$ " + String.valueOf(amount_saved));

        }
        else{
            amount.setText("$ " + String.valueOf(amount_saved));
        }

        //now that all the text and such has been updated we just wait for them to start
        startButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //when they click the button we move to the next activity
                                               Intent intent = new Intent(User_intro_Display.this, main_game_page.class);
                                               startActivity(intent);
                                           }
                                       }
        );
    }





}