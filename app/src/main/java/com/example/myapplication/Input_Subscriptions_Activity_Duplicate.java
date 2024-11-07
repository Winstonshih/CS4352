/*
  Input_Subscriptions_Activity_Duplicate.java

  This activity allows users to enter information for multiple subscriptions, such as subscription names and monthly amounts,
  and stores this data using SharedPreferences for persistence. The activity includes three default subscription fields and
  allows users to dynamically add more fields if needed. A "Submit" button saves all entered subscriptions and navigates
  the user to the next activity, while an "Add More" button lets users add extra subscription entries. Validation ensures
  that at least one subscription is completed before proceeding, enhancing user data input flexibility and persistence across sessions.

 */


package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityInputSubscriptionsBinding;

import java.util.Map;

public class Input_Subscriptions_Activity_Duplicate extends AppCompatActivity {

    EditText sub_nameDuplicate, sub_amountDuplicate, sub_name_2Duplicate, sub_amount_2Duplicate, sub_name_3Duplicate, sub_amount_3Duplicate;
    Button submitDuplicate, addMoreDuplicate;

    LinearLayout subscriptionContainerDuplicate;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ActivityInputSubscriptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Set the layout using setContentView
        setContentView(R.layout.activity_input_subscriptions_duplicate);

        // Initialize EditText and Button views using findViewById()
        sub_nameDuplicate = findViewById(R.id.sub_nameDuplicate);
        sub_amountDuplicate = findViewById(R.id.sub_amountDuplicate);
        sub_name_2Duplicate = findViewById(R.id.sub_name_2Duplicate);
        sub_amount_2Duplicate = findViewById(R.id.sub_amount_2Duplicate);
        sub_name_3Duplicate = findViewById(R.id.sub_name_3Duplicate);
        sub_amount_3Duplicate = findViewById(R.id.sub_amount_3Duplicate);
        submitDuplicate = findViewById(R.id.submitDuplicate);
        addMoreDuplicate = findViewById(R.id.addMoreDuplicate);
        subscriptionContainerDuplicate = findViewById(R.id.subscriptionContainerDuplicate);

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("subscriptionsDuplicate", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();

        // Set submit button click listener
        submitDuplicate.setOnClickListener(view -> handleSubmit());

        // Initialize View Binding for dynamic view addition
        binding = ActivityInputSubscriptionsBinding.inflate(getLayoutInflater());

        // Set addMore button click listener using View Binding for dynamic subscription
        addMoreDuplicate.setOnClickListener(view -> addMoreSub());
    }

    private void handleSubmit() {
        // Get the name and amount
        String name;
        Long amount;
        boolean completed = false;
        int allCompleted = 0;

        // Check if the first subscription is filled
        if (!sub_nameDuplicate.getText().toString().isEmpty() && !sub_amountDuplicate.getText().toString().isEmpty()) {
            name = sub_nameDuplicate.getText().toString();
            amount = Long.parseLong(sub_amountDuplicate.getText().toString());
            editor.putLong(name, amount);
            completed = true;
            allCompleted++;
        }
        // Similarly check for other subscriptions
        if (!sub_name_2Duplicate.getText().toString().isEmpty() && !sub_amount_2Duplicate.getText().toString().isEmpty()) {
            name = sub_name_2Duplicate.getText().toString();
            amount = Long.parseLong(sub_amount_2Duplicate.getText().toString());
            editor.putLong(name, amount);
            completed = true;
            allCompleted++;
        }
        if (!sub_name_3Duplicate.getText().toString().isEmpty() && !sub_amount_3Duplicate.getText().toString().isEmpty()) {
            name = sub_name_3Duplicate.getText().toString();
            amount = Long.parseLong(sub_amount_3Duplicate.getText().toString());
            editor.putLong(name, amount);
            completed = true;
            allCompleted++;
        }

        // Apply the changes if at least one subscription is completed
        if (completed) {
            editor.apply();
            // Log the saved subscriptions for debugging purposes
            Map<String, ?> allEntries = sharedPreferences.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Proceed to the next activity
            Intent intent = new Intent(Input_Subscriptions_Activity_Duplicate.this, User_intro_Display_Duplicate.class);
            startActivity(intent);
        } else {
            // Show a toast message if no subscriptions are filled
            Toast.makeText(this, "Please fill in at least one subscription", Toast.LENGTH_LONG).show();
        }
    }

    private void addMoreSub() {
        // Inflate the additional subscription layout and add it to the container
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.add_subscription, null);
        subscriptionContainerDuplicate.addView(view);
    }
}
