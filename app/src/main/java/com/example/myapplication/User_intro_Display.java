/*
  User_intro_Display.java

  This activity provides an overview of the user's financial and character status before entering the main game.
  It displays income, expenses, and remaining balance by retrieving and calculating data from SharedPreferences.
  Initial character stats and inventory settings are set up, with defensive attributes and equipped items tracked
  for the game. The activity ensures the display is updated with accurate data from `money_tracker` and
  `subscriptions` preferences, showing positive or negative savings in green or red, respectively. A "Start"
  button enables users to proceed to the main game page, transitioning them to active gameplay.

 */

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

public class User_intro_Display extends AppCompatActivity {
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
        setContentView(R.layout.activity_user_intro_display);
        income = findViewById(R.id.income);
        expenses = findViewById(R.id.expenses);
        amount = findViewById(R.id.amount);
        username = findViewById(R.id.username);
        startButton = findViewById(R.id.startButton);
        backButton = findViewById(R.id.backButton);

        //new sharable that is going to track the things that we have equped to track
        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        SharedPreferences.Editor trackerEditor = sharedTracker.edit();
        //add the tracker variables for the character intial in inventory
        trackerEditor.putString("hp", "50");
        trackerEditor.putString("chest defense", "10");
        trackerEditor.putString("pants defense", "10");
        trackerEditor.putString("head defense", "10");
        trackerEditor.putString("attack", "10");
        //add the current inventory being used
        //this keeps track of what number in the inventory they are using
        trackerEditor.putInt("helmet", 1);
        trackerEditor.putInt("chest", 1);
        trackerEditor.putInt("pants", 1);
        trackerEditor.putInt("sword", 1);
        // the buttons they have unlocked
        trackerEditor.putBoolean("inventory", false);
        trackerEditor.putBoolean("reward",false);
        trackerEditor.putBoolean("person",false);

        //the buttons on what color they may be
        trackerEditor.putBoolean("inventory color",false);
        trackerEditor.putBoolean("reward color",false);
        trackerEditor.putBoolean("person color",false);
        trackerEditor.putBoolean("home color",true);
        //last trackers
        trackerEditor.putBoolean("last tracker",false);
        trackerEditor.putBoolean("last tracker2",false);
        trackerEditor.putBoolean("last sword",false);
        trackerEditor.putBoolean("empty list", false);


        //the current
        //every time their is an increase in numbers, then a new part of the inventory is added
        trackerEditor.apply();
        //list tracker
        SharedPreferences sharedList = getSharedPreferences("GamePagePrefs", MODE_PRIVATE);
        SharedPreferences.Editor listEditor = sharedList.edit();
        listEditor.clear();
        listEditor.apply();
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
                Intent intent = new Intent(User_intro_Display.this, main_game_page.class);
                startActivity(intent);
            }
        }
                );

    }

}