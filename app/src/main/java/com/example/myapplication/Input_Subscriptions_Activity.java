package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Input_Subscriptions_Activity extends AppCompatActivity {

    private EditText sub_name, sub_amount, sub_name_2, sub_amount_2, sub_name_3, sub_amount_3;
    private Button submit, back;

    private SharedPreferences subscriptionPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this activity
        setContentView(R.layout.activity_input_subscriptions);

        // Initialize SharedPreferences
        subscriptionPreferences = getSharedPreferences("subscription_data", MODE_PRIVATE);
        //erase everything inside the shared preferences
        subscriptionPreferences.edit().clear().apply();

        // Bind UI elements
        sub_name = findViewById(R.id.sub_name);
        sub_amount = findViewById(R.id.sub_amount);
        sub_name_2 = findViewById(R.id.sub_name_2);
        sub_amount_2 = findViewById(R.id.sub_amount_2);
        sub_name_3 = findViewById(R.id.sub_name_3);
        sub_amount_3 = findViewById(R.id.sub_amount_3);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);

        // Set up the submit button
        submit.setOnClickListener(view -> processSubscriptions());

        // Set up the back button
        back.setOnClickListener(view -> finish()); // Navigate back to the previous activity
    }

    private void processSubscriptions() {
        boolean hasValidEntry = false;

        // Validate each subscription
        int isValid1 = validateAndSave(sub_name, sub_amount);
        int isValid2 = validateAndSave(sub_name_2, sub_amount_2);
        int isValid3 = validateAndSave(sub_name_3, sub_amount_3);

        // Check if all fields are empty
        if (allFieldsEmpty()) {
            // Clear SharedPreferences
            subscriptionPreferences.edit().clear().apply();
            Toast.makeText(this, "No subscriptions added. Proceeding without subscriptions.", Toast.LENGTH_SHORT).show();
            goToNextActivity();
        }
        //check if the fist is filled then gotoNextActivity
        else if(isValid1 == 1  && (isValid2 == 2 || isValid2 != 0) && (isValid3 == 2 || isValid3 != 0)){
            goToNextActivity();
        }
        else if(isValid1 == 1  && isValid2 == 1 && (isValid3 == 2 || isValid3 != 0)){
            goToNextActivity();
        }
        else if(isValid1 == 1  && isValid2 == 1 && isValid3 == 1){
            goToNextActivity();
        }


    }

    private int validateAndSave(EditText nameField, EditText amountField) {
        String name = nameField.getText().toString().trim();
        String amountStr = amountField.getText().toString().trim();

        // Check if one field is filled without the other
        if (!name.isEmpty() && amountStr.isEmpty()) {
            Toast.makeText(this, "Please provide an amount for subscription: " + name, Toast.LENGTH_SHORT).show();
            return 0; // Incomplete pair
        }
        if (name.isEmpty() && !amountStr.isEmpty()) {
            Toast.makeText(this, "Please provide a name for the subscription amount: " + amountStr, Toast.LENGTH_SHORT).show();
            return 0; // Incomplete pair
        }

        // If both fields are filled, save the subscription
        if (!name.isEmpty() && !amountStr.isEmpty()) {

                long amount = Long.parseLong(amountStr);
                subscriptionPreferences.edit().putLong(name, amount).apply();
                return 1; // Valid entry saved

        }

        // Both fields are empty
        return 2;
    }

    private boolean allFieldsEmpty() {
        return sub_name.getText().toString().trim().isEmpty()
                && sub_amount.getText().toString().trim().isEmpty()
                && sub_name_2.getText().toString().trim().isEmpty()
                && sub_amount_2.getText().toString().trim().isEmpty()
                && sub_name_3.getText().toString().trim().isEmpty()
                && sub_amount_3.getText().toString().trim().isEmpty();
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this, User_intro_Display.class);
        startActivity(intent);
    }
}
