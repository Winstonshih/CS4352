/*
  main_character_stats_page.java

  This activity displays the main character's stats page, allowing users to navigate between key sections
  of the app, including the inventory, rewards, and main game pages. It sets up full-screen insets to provide
  an immersive experience and uses buttons for navigation to other activities, such as `inventory_page`,
  `rewards_page`, and `main_game_page`. The page serves as a central hub for accessing character-related
  features, contributing to an organized and seamless user experience within the game.

 */
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main_character_stats_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_character_stats_page);

        // Set window insets for full-screen
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inventory Button: Navigate to Inventory Page
        Button inventoryButton = findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_character_stats_page.this, inventory_page.class);
                startActivity(intent);
            }
        });

        // Rewards Button: Navigate to Rewards Page
        Button rewardsButton = findViewById(R.id.rewardsButton);
        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_character_stats_page.this, rewards_page.class);
                startActivity(intent);
            }
        });

        // Home Button: Navigate to Main Game Page
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_character_stats_page.this, main_game_page.class);
                startActivity(intent);
            }
        });


    }
}
