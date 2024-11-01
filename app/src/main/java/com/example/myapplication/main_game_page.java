/*
  main_game_page.java

  This activity serves as the primary game interface, displaying the character's equipment, available tasks, and
  navigation options. It integrates a RecyclerView populated with task items from the `GamePageViewModel`, allowing
  users to interact with and complete tasks. The page dynamically loads and updates equipped items (helmet, chest,
  and pants) based on stored preferences, giving users visual feedback on their character's progression. Navigation
  buttons enable users to switch between inventory, rewards, and character stats pages, providing a seamless experience
  across key game features.

 */
package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class main_game_page extends AppCompatActivity {
    private ImageButton personImage;
    private ImageView helmet, chest, pants;
    private RecyclerView recyclerView;
    private SharedPreferences sharedTracker;
    private GamePageViewModel viewModel;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game_page);

        initializeViews();
        viewModel = new ViewModelProvider(this).get(GamePageViewModel.class);

        viewModel.getItems().observe(this, items -> {
            if (adapter == null) {
                adapter = new MyAdapter(this, items);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        });

        loadEquipment();
        setupRecyclerView();
        setupButtons();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadEquipment(); // Refresh the equipment UI
    }
    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerview);
        helmet = findViewById(R.id.helmet);
        chest = findViewById(R.id.chest);
        pants = findViewById(R.id.pants);
        personImage = findViewById(R.id.personImage);
    }

    private void loadEquipment() {
        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedTracker.edit();

        int helmetID = sharedTracker.getInt("helmet", 0);
        int chestID = sharedTracker.getInt("chest", 0);
        int pantsID = sharedTracker.getInt("pants", 0);

        // Load helmet image
        if (helmetID == 1) {
            helmet.setImageResource(R.drawable.helmet);
        } else if (helmetID == 2) {
            helmet.setImageResource(R.drawable.upgradedhelmet);
        }

        // Load chest image
        if (chestID == 1) {
            chest.setImageResource(R.drawable.armor);
        } else if (chestID == 2) {
            chest.setImageResource(R.drawable.upgradedarmor);
        }

        // Load pants image
        if (pantsID == 1) {
            pants.setImageResource(R.drawable.pants);
        } else if (pantsID == 2) {
            pants.setImageResource(R.drawable.upgradedpants);
        }
    }
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupButtons() {
        Button inventoryButton = findViewById(R.id.inventoryButton);
        Button rewardsButton = findViewById(R.id.rewardsButton);
        Button homeButton = findViewById(R.id.homeButton);
        inventoryButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, inventory_page.class))
        );
        rewardsButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, rewards_page.class))
        );
        homeButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, main_game_page.class))
        );
        personImage.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, main_character_stats_page.class))
        );
    }
    public void updateArmorUI() {
        loadEquipment();
    }
}