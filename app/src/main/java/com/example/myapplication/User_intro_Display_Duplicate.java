package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Services.userIntroServices;

import java.util.Map;

public class User_intro_Display_Duplicate extends AppCompatActivity {
    //what we are going to update in the display
    TextView income;
    TextView expenses;
    TextView amount;
    TextView username;
    Button startButton;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_intro_display_duplicate);
        income = findViewById(R.id.income);
        expenses = findViewById(R.id.expenses);
        amount = findViewById(R.id.amount);
        username = findViewById(R.id.username);
        startButton = findViewById(R.id.startButton);
        backButton = findViewById(R.id.backButton);





        //dont touch anything after this

        userIntroServices services = new userIntroServices(this);
        Long incomeCalculated = services.getIncome();
        Long expensesCalculated = services.getExpenses();
        Long amountSaved = services.getAmountSaved();
        String usernameReceived = services.getUsername();
        username.setText(usernameReceived);
        income.setText("$ " + String.valueOf(incomeCalculated));
        expenses.setText("$ " + String.valueOf(expensesCalculated));

        if(amountSaved > 0){
            amount.setTextColor(getResources().getColor(R.color.dark_green));
            amount.setText("$ " + String.valueOf(amountSaved));
        }
        else if (amountSaved < 0){
            amount.setTextColor(getResources().getColor(R.color.dark_red));
            amount.setText("$ " + String.valueOf(amountSaved));

        }
        else{
            amount.setText("$ " + String.valueOf(amountSaved));
        }
        //print
        services.printSharedPreferences();
        backButton.setOnClickListener(view -> finish()); // Navigate back to the previous activity
        //now that all the text and such has been updated we just wait for them to start
        startButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //when they click the button we move to the next activity
                                               Intent intent = new Intent(User_intro_Display_Duplicate.this, main_game_page.class);
                                               startActivity(intent);
                                           }
                                       }
        );

    }

}