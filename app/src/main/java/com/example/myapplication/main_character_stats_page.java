package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class main_character_stats_page extends AppCompatActivity {
    private SharedPreferences TRACKER;
    private Button inventoryButton, rewardsButton, homeButton;
    private TextView hpText, armorText, swordText, helmetText, pantsText;
    private ImageView chest, helmet, pants, chestImage, helmetImage, pantsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_character_stats_page);

        // Initialize SharedPreferences
        TRACKER = getSharedPreferences("tracker", MODE_PRIVATE);

        // Initial things
        setColorButton();
        lockedButtons();
        setupButtons();
        updateArmorUI();
        updateStats();
    }

    private void setupButtons() {
        inventoryButton = findViewById(R.id.inventoryButton);
        rewardsButton = findViewById(R.id.rewardsButton);
        homeButton = findViewById(R.id.homeButton);

        // Set up button click listeners
        inventoryButton.setOnClickListener(view ->
            startActivity(new Intent(main_character_stats_page.this, inventory_page.class))
        );
        rewardsButton.setOnClickListener(view ->
                startActivity(new Intent(main_character_stats_page.this, rewards_page.class))
        );
        homeButton.setOnClickListener(view -> {
            if (!TRACKER.getBoolean("last tracker2", false)) {
                SharedPreferences.Editor editor = TRACKER.edit();
                editor.putBoolean("home color", false);
                editor.putBoolean("person color", false);
                editor.putBoolean("inventory color", true);
                editor.putBoolean("inventory", true);
                editor.putBoolean("last tracker2", true);
                editor.apply();
            }
            startActivity(new Intent(main_character_stats_page.this, main_game_page.class));
        });
    }

    private void updateStats() {
        // Get the TextViews
        hpText = findViewById(R.id.hpText);
        armorText = findViewById(R.id.chestText);
        swordText = findViewById(R.id.swordText);
        helmetText = findViewById(R.id.helmetText);
        pantsText = findViewById(R.id.pantsText);
        hpText.setText("HP " + TRACKER.getString("hp", "0"));
        armorText.setText("DEFENSE " + TRACKER.getString("chest defense", "0"));
        swordText.setText("ATTACK " + TRACKER.getString("attack", "0"));
        helmetText.setText("DEFENSE " + TRACKER.getString("head defense", "0"));
        pantsText.setText("DEFENSE " + TRACKER.getString("pants defense", "0"));
    }

    private void updateArmorUI() {
        // Get the images
        chest = findViewById(R.id.chest);
        helmet = findViewById(R.id.helmet);
        pants = findViewById(R.id.pants);

        // Images on the list that we have equipped
        chestImage = findViewById(R.id.armorImage);
        helmetImage = findViewById(R.id.helmetImage);
        pantsImage = findViewById(R.id.pantsImage);

        // Set my editor for the shared preferences
        SharedPreferences.Editor editor = TRACKER.edit();

        // Get the current inventory
        int helmetID = TRACKER.getInt("helmet", 0);
        int chestID = TRACKER.getInt("chest", 0);
        int pantsID = TRACKER.getInt("pants", 0);

        // Load helmet image
        if (helmetID == 1) {
            helmet.setImageResource(R.drawable.helmet);
            helmetImage.setImageResource(R.drawable.helmet);
            editor.putString("head defense", "10");
        } else if (helmetID == 2) {
            helmet.setImageResource(R.drawable.upgradedhelmet);
            helmetImage.setImageResource(R.drawable.upgradedhelmet);
            editor.putString("head defense", "30");
        }

        // Load chest image
        if (chestID == 1) {
            chest.setImageResource(R.drawable.armor);
            chestImage.setImageResource(R.drawable.armor);
            editor.putString("chest defense", "10");
        } else if (chestID == 2) {
            chest.setImageResource(R.drawable.upgradedarmor);
            chestImage.setImageResource(R.drawable.upgradedarmor);
            editor.putString("chest defense", "30");
        }

        // Load pants image
        if (pantsID == 1) {
            pants.setImageResource(R.drawable.pants);
            pantsImage.setImageResource(R.drawable.pants);
            editor.putString("pants defense", "10");
        } else if (pantsID == 2) {
            pants.setImageResource(R.drawable.upgradedpants);
            pantsImage.setImageResource(R.drawable.upgradedpants);
            editor.putString("pants defense", "30");
        }

        // Find the sword ImageView
        ImageView sword = findViewById(R.id.sword);

        // Get the sword ID from SharedPreferences
        int swordID = TRACKER.getInt("sword", 0);

        // Load sword image if equipped
        if (swordID == 2) {
            sword.setVisibility(View.VISIBLE);
            sword.setImageResource(R.drawable.sword);  // Use your sword drawable
            // Update attack value
            editor.putString("attack", "20");  // Set attack value when sword is equipped
        } else {
            sword.setVisibility(View.GONE);
            // Reset attack value if sword is not equipped
            editor.putString("attack", "0");
        }

        // Apply the changes to the shared preferences
        editor.apply();
    }
    private void setColorButton(){
        Button homeButton = findViewById(R.id.homeButton);
        //change the color if true then change to green
        if(TRACKER.getBoolean("home color", false)){
            homeButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
        }
        //if its not true then change it to dark grey
        else{
            homeButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
        }
    }
    private void lockedButtons() {
        Button inventoryButton = findViewById(R.id.inventoryButton);
        Button rewardsButton = findViewById(R.id.rewardsButton);
        //if inventory is locked
        if (TRACKER.getBoolean("inventory", false)) {
            inventoryButton.setEnabled(true);
        } else {
            inventoryButton.setEnabled(false);
        }
        //if rewards is locked
        if (TRACKER.getBoolean("reward", false)) {
            rewardsButton.setEnabled(true);
            if (TRACKER.getBoolean("reward color", false)) {
                rewardsButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
            } else {
                rewardsButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
            }
        } else {
            rewardsButton.setEnabled(false);
        }
    }
}
