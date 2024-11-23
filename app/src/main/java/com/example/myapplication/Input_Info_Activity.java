/*Ruben Rodriguez
rar180001
incomplete
* */

/*
Input_Info_Activity.java

  This activity collects essential financial data from the user, such as income, food expenses, and various
  bills (e.g., mortgage, utilities). It includes validation to ensure that all fields are filled out before allowing
  the user to proceed. If the data is complete, each entry is stored using SharedPreferences for persistence,
  allowing values to be accessed across sessions. A "Next" button saves the inputted data and navigates
  the user to the following activity, where they can further customize their financial tracking experience.
  This activity is a foundational step, providing necessary data for the app’s core functionality.

 */


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

public class Input_Info_Activity extends AppCompatActivity {
    EditText Income;
    EditText food;
    EditText mortage;
    EditText waterBill;
    EditText electricBill;
    EditText gasBill;
    EditText internetBill;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_info);
        Income = findViewById(R.id.Income);
        food = findViewById(R.id.food);
        mortage = findViewById(R.id.mortgage);
        waterBill = findViewById(R.id.waterBill);
        electricBill= findViewById(R.id.electricBill);
        gasBill = findViewById(R.id.gasBill);
        internetBill = findViewById(R.id.internetBill);
        //button for the next page
        nextButton = findViewById(R.id.nextButton);
        //for the shared preferences (Also have it inside on create to prevent app from shutting down
        SharedPreferences sharedPreferences = getSharedPreferences("money_tracker", MODE_PRIVATE);
        SharedPreferences.Editor moneyTrackerEditor = sharedPreferences.edit();

        //when they click the next button
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //actions that are going to happen when they click the next button
               reset();
                //test
               /*int test = parseInt(Income.getText().toString());
               System.out.println(test);*/
                //if one of the text are empty
                if (
                        Income.getText().toString().isEmpty() ||
                                food.getText().toString().isEmpty() ||
                                mortage.getText().toString().isEmpty() ||
                                waterBill.getText().toString().isEmpty() ||
                                electricBill.getText().toString().isEmpty() ||
                                gasBill.getText().toString().isEmpty() ||
                                internetBill.getText().toString().isEmpty()
                ){
                    Toast.makeText(Input_Info_Activity.this, "Missing Information", Toast.LENGTH_SHORT).show();
                }
                //if all the information is complete
                else{
                    Toast.makeText(Input_Info_Activity.this, "Completed", Toast.LENGTH_SHORT).show();
                    //save them in our preference
                    moneyTrackerEditor.putLong("income", Long.parseLong(Income.getText().toString()));
                    moneyTrackerEditor.putLong("food", Long.parseLong(food.getText().toString()));
                    moneyTrackerEditor.putLong("mortgage", Long.parseLong(mortage.getText().toString()));
                    moneyTrackerEditor.putLong("waterBill", Long.parseLong(waterBill.getText().toString()));
                    moneyTrackerEditor.putLong("electricBill", Long.parseLong(electricBill.getText().toString()));
                    moneyTrackerEditor.putLong("gasBill", Long.parseLong(gasBill.getText().toString()));
                    moneyTrackerEditor.putLong("internetBill", Long.parseLong(internetBill.getText().toString()));
                    moneyTrackerEditor.apply();
                    //check if they are saved
                   System.out.println("income :" +sharedPreferences.getLong("income", 0));
                   System.out.println("food :" + sharedPreferences.getLong("food", 0));
                   System.out.println("mortgage :" +sharedPreferences.getLong("mortgage", 0));
                   System.out.println("waterbill :" + sharedPreferences.getLong("waterBill", 0));
                   System.out.println("electricbill :" +sharedPreferences.getLong("electricBill", 0));
                   System.out.println("gasbill :" +sharedPreferences.getLong("gasBill", 0));
                   System.out.println("internetbill :" +sharedPreferences.getLong("internetBill", 0));
                    //then we move to the next part of the page where they are going to ad their subscriptions or such
                    Intent intent=new Intent(Input_Info_Activity.this,Input_Subscriptions_Activity.class);
                    startActivity(intent);
                }

            }
        });

    }
    private void reset(){
        SharedPreferences sharedPreferences = getSharedPreferences("money_tracker", MODE_PRIVATE);
        SharedPreferences.Editor moneyTrackerEditor = sharedPreferences.edit();
        moneyTrackerEditor.clear();
        moneyTrackerEditor.apply();
    }
}
