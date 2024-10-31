/*
  Input_Subscriptions_Activity.java

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

public class Input_Subscriptions_Activity extends AppCompatActivity {

    EditText sub_name, sub_amount, sub_name_2, sub_amount_2, sub_name_3, sub_amount_3;
    Button submit, addMore;

    LinearLayout subscriptionContainer;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ActivityInputSubscriptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Set the layout using setContentView
        setContentView(R.layout.activity_input_subscriptions);

        // Initialize EditText and Button views using findViewById()
        sub_name = findViewById(R.id.sub_name);
        sub_amount = findViewById(R.id.sub_amount);
        sub_name_2 = findViewById(R.id.sub_name_2);
        sub_amount_2 = findViewById(R.id.sub_amount_2);
        sub_name_3 = findViewById(R.id.sub_name_3);
        sub_amount_3 = findViewById(R.id.sub_amount_3);
        submit = findViewById(R.id.submit);
        addMore = findViewById(R.id.addMore);
        subscriptionContainer = findViewById(R.id.subscriptionContainer);

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("subscriptions", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();

        // Set submit button click listener
        submit.setOnClickListener(view -> handleSubmit());

        // Initialize View Binding for dynamic view addition
        binding = ActivityInputSubscriptionsBinding.inflate(getLayoutInflater());

        // Set addMore button click listener using View Binding for dynamic subscription
        addMore.setOnClickListener(view -> addMoreSub());
    }

    private void handleSubmit() {
        // Get the name and amount
        String name;
        Long amount;
        boolean completed = false;
        int allCompleted = 0;

        // Check if the first subscription is filled
        if (!sub_name.getText().toString().isEmpty() && !sub_amount.getText().toString().isEmpty()) {
            name = sub_name.getText().toString();
            amount = Long.parseLong(sub_amount.getText().toString());
            editor.putLong(name, amount);
            completed = true;
            allCompleted++;
        }
        // Similarly check for other subscriptions
        if (!sub_name_2.getText().toString().isEmpty() && !sub_amount_2.getText().toString().isEmpty()) {
            name = sub_name_2.getText().toString();
            amount = Long.parseLong(sub_amount_2.getText().toString());
            editor.putLong(name, amount);
            completed = true;
            allCompleted++;
        }
        if (!sub_name_3.getText().toString().isEmpty() && !sub_amount_3.getText().toString().isEmpty()) {
            name = sub_name_3.getText().toString();
            amount = Long.parseLong(sub_amount_3.getText().toString());
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
            Intent intent = new Intent(Input_Subscriptions_Activity.this, User_intro_Display.class);
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
        subscriptionContainer.addView(view);
    }
}
