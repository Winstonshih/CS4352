package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Input_Info_Activity_2 extends AppCompatActivity {
    EditText Income;
    EditText food;
    EditText mortage;
    EditText waterBill;
    EditText electricBill;
    EditText gasBill;
    EditText internetBill;
    Button nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_info2);

        Income = findViewById(R.id.Income);
        food = findViewById(R.id.food);
        mortage = findViewById(R.id.mortgage);
        waterBill = findViewById(R.id.waterBill);
        electricBill = findViewById(R.id.electricBill);
        gasBill = findViewById(R.id.gasBill);
        internetBill = findViewById(R.id.internetBill);

        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        SharedPreferences sharedPreferences = getSharedPreferences("money_tracker", MODE_PRIVATE);

        // Populate fields from SharedPreferences
        populateFieldsFromSharedPreferences(sharedPreferences);

        // Back button logic
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Input_Info_Activity_2.this, "Did not save", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Next button logic
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnyFieldEmpty()) {
                    Toast.makeText(Input_Info_Activity_2.this, "Missing Information", Toast.LENGTH_SHORT).show();
                } else {
                    saveDataToSharedPreferences(sharedPreferences);
                    Intent intent = new Intent(Input_Info_Activity_2.this, Input_Subscriptions_Activity_Duplicate.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isAnyFieldEmpty() {
        return Income.getText().toString().isEmpty() ||
                food.getText().toString().isEmpty() ||
                mortage.getText().toString().isEmpty() ||
                waterBill.getText().toString().isEmpty() ||
                electricBill.getText().toString().isEmpty() ||
                gasBill.getText().toString().isEmpty() ||
                internetBill.getText().toString().isEmpty();
    }

    private void saveDataToSharedPreferences(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor moneyTrackerEditor = sharedPreferences.edit();
        moneyTrackerEditor.putLong("income", Long.parseLong(Income.getText().toString()));
        moneyTrackerEditor.putLong("food", Long.parseLong(food.getText().toString()));
        moneyTrackerEditor.putLong("mortgage", Long.parseLong(mortage.getText().toString()));
        moneyTrackerEditor.putLong("waterBill", Long.parseLong(waterBill.getText().toString()));
        moneyTrackerEditor.putLong("electricBill", Long.parseLong(electricBill.getText().toString()));
        moneyTrackerEditor.putLong("gasBill", Long.parseLong(gasBill.getText().toString()));
        moneyTrackerEditor.putLong("internetBill", Long.parseLong(internetBill.getText().toString()));
        moneyTrackerEditor.apply();

    }

    private void populateFieldsFromSharedPreferences(SharedPreferences sharedPreferences) {
        Income.setText(String.valueOf(sharedPreferences.getLong("income", 0)));
        food.setText(String.valueOf(sharedPreferences.getLong("food", 0)));
        mortage.setText(String.valueOf(sharedPreferences.getLong("mortgage", 0)));
        waterBill.setText(String.valueOf(sharedPreferences.getLong("waterBill", 0)));
        electricBill.setText(String.valueOf(sharedPreferences.getLong("electricBill", 0)));
        gasBill.setText(String.valueOf(sharedPreferences.getLong("gasBill", 0)));
        internetBill.setText(String.valueOf(sharedPreferences.getLong("internetBill", 0)));
    }
}
