/* Ruben Rodriguez
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

public class Input_Info_Activity_2 extends AppCompatActivity {
    EditText Income_2;
    EditText food_2;
    EditText mortage_2;
    EditText waterBill_2;
    EditText electricBill_2;
    EditText gasBill_2;
    EditText internetBill_2;
    Button nextButton_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_info2);
        Income_2 = findViewById(R.id.Income_2);
        food_2 = findViewById(R.id.food_2);
        mortage_2 = findViewById(R.id.mortage_2);
        waterBill_2 = findViewById(R.id.waterBill_2);
        electricBill_2 = findViewById(R.id.electricBill_2);
        gasBill_2 = findViewById(R.id.gasBill_2);
        internetBill_2 = findViewById(R.id.internetBill_2);
        //button for the next page
        nextButton_2 = findViewById(R.id.nextButton_2);
        //for the shared preferences (Also have it inside on create to prevent app from shutting down
        SharedPreferences sharedPreferences = getSharedPreferences("money_tracker_2", MODE_PRIVATE);
        SharedPreferences.Editor moneyTrackerEditor = sharedPreferences.edit();


        nextButton_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //actions that are going to happen when they click the next button

                //test
               /*int test = parseInt(Income_2.getText().toString());
               System.out.println(test);*/
                //if one of the text are empty
                if (
                        Income_2.getText().toString().isEmpty() ||
                                food_2.getText().toString().isEmpty() ||
                                mortage_2.getText().toString().isEmpty() ||
                                waterBill_2.getText().toString().isEmpty() ||
                                electricBill_2.getText().toString().isEmpty() ||
                                gasBill_2.getText().toString().isEmpty() ||
                                internetBill_2.getText().toString().isEmpty()
                ){
                    Toast.makeText(Input_Info_Activity_2.this, "Missing Information", Toast.LENGTH_SHORT).show();
                }
                //if all the information is complete
                else{
                    Toast.makeText(Input_Info_Activity_2.this, "Completed", Toast.LENGTH_SHORT).show();
                    //save them in our preference
                    moneyTrackerEditor.putLong("income_2", Long.parseLong(Income_2.getText().toString()));
                    moneyTrackerEditor.putLong("food_2", Long.parseLong(food_2.getText().toString()));
                    moneyTrackerEditor.putLong("mortage_2", Long.parseLong(mortage_2.getText().toString()));
                    moneyTrackerEditor.putLong("waterBill_2", Long.parseLong(waterBill_2.getText().toString()));
                    moneyTrackerEditor.putLong("electricBill_2", Long.parseLong(electricBill_2.getText().toString()));
                    moneyTrackerEditor.putLong("gasBill_2", Long.parseLong(gasBill_2.getText().toString()));
                    moneyTrackerEditor.putLong("internetBill_2", Long.parseLong(internetBill_2.getText().toString()));
                    moneyTrackerEditor.apply();
                    //check if they are saved
                    //                   System.out.println(sharedPreferences.getLong("income_2", 0));
                    //                   System.out.println(sharedPreferences.getLong("food_2", 0));
                    //                   System.out.println(sharedPreferences.getLong("mortage_2", 0));
                    //                   System.out.println(sharedPreferences.getLong("waterBill_2", 0));
                    //                   System.out.println(sharedPreferences.getLong("electricBill_2", 0));
                    //                   System.out.println(sharedPreferences.getLong("gasBill_2", 0));
                    //                   System.out.println(sharedPreferences.getLong("internetBill_2", 0));
                    //then we move to the next part of the page where they are going to ad their subscriptions or such
                    Intent intent=new Intent(Input_Info_Activity_2.this, Input_Subscriptions_Activity_Duplicate.class);
                    startActivity(intent);
                }

            }
        });

    }
}