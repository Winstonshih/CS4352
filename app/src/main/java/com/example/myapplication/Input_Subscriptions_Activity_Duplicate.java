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

    private EditText sub_name, sub_amount, sub_name_2, sub_amount_2, sub_name_3, sub_amount_3;
    private Button submit, back;
    private SharedPreferences subscriptionPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Set the layout using setContentView
        setContentView(R.layout.activity_input_subscriptions_duplicate);

        subscriptionPreferences = getSharedPreferences("subscription_data", MODE_PRIVATE);

        sub_name = findViewById(R.id.sub_name);
        sub_amount = findViewById(R.id.sub_amount);
        sub_name_2 = findViewById(R.id.sub_name_2);
        sub_amount_2 = findViewById(R.id.sub_amount_2);
        sub_name_3 = findViewById(R.id.sub_name_3);
        sub_amount_3 = findViewById(R.id.sub_amount_3);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);

        loadExistingData();

        submit.setOnClickListener(view -> processSubscriptions());
        back.setOnClickListener(view -> finish());
    }

    private void loadExistingData() {
        String name1 = subscriptionPreferences.getString("sub_name_1", "");
        long amount1 = subscriptionPreferences.getLong("sub_amount_1", 0);
        String name2 = subscriptionPreferences.getString("sub_name_2", "");
        long amount2 = subscriptionPreferences.getLong("sub_amount_2", 0);
        String name3 = subscriptionPreferences.getString("sub_name_3", "");
        long amount3 = subscriptionPreferences.getLong("sub_amount_3", 0);

        sub_name.setText(name1);
        sub_amount.setText(amount1 != 0 ? String.valueOf(amount1) : "");
        sub_name_2.setText(name2);
        sub_amount_2.setText(amount2 != 0 ? String.valueOf(amount2) : "");
        sub_name_3.setText(name3);
        sub_amount_3.setText(amount3 != 0 ? String.valueOf(amount3) : "");
    }

    private void processSubscriptions() {
        int isValid1 = validateAndSave(sub_name, sub_amount, 1);
        int isValid2 = validateAndSave(sub_name_2, sub_amount_2, 2);
        int isValid3 = validateAndSave(sub_name_3, sub_amount_3, 3);

        if (allFieldsEmpty()) {
            subscriptionPreferences.edit().clear().apply();
            Toast.makeText(this, "No subscriptions added. Proceeding without subscriptions.", Toast.LENGTH_SHORT).show();
            goToNextActivity();
        } else if (isValid1 == 1 && (isValid2 == 2 || isValid2 != 0) && (isValid3 == 2 || isValid3 != 0)) {
            goToNextActivity();
        } else if (isValid1 == 1 && isValid2 == 1 && (isValid3 == 2 || isValid3 != 0)) {
            goToNextActivity();
        } else if (isValid1 == 1 && isValid2 == 1 && isValid3 == 1) {
            goToNextActivity();
        }
    }

    private int validateAndSave(EditText nameField, EditText amountField, int subscriptionNumber) {
        String name = nameField.getText().toString().trim();
        String amountStr = amountField.getText().toString().trim();

        if (!name.isEmpty() && amountStr.isEmpty()) {
            Toast.makeText(this, "Please provide an amount for subscription: " + name, Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (name.isEmpty() && !amountStr.isEmpty()) {
            Toast.makeText(this, "Please provide a name for the subscription amount: " + amountStr, Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (!name.isEmpty() && !amountStr.isEmpty()) {
            long amount = Long.parseLong(amountStr);
            subscriptionPreferences.edit()
                    .putString("sub_name_" + subscriptionNumber, name)
                    .putLong("sub_amount_" + subscriptionNumber, amount)
                    .apply();
            return 1;
        }
        //delete the specific subscription
        subscriptionPreferences.edit()
                .remove("sub_name_" + subscriptionNumber)
                .remove("sub_amount_" + subscriptionNumber)
                .apply();
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
        Intent intent = new Intent(this, User_intro_Display_Duplicate.class);
        startActivity(intent);
    }


}