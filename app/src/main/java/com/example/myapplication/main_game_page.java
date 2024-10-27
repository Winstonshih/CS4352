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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class main_game_page extends AppCompatActivity {
    private ImageButton personImage;
    private ImageView helmet, chest, pants;
    private RecyclerView recyclerView;
    private SharedPreferences sharedTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game_page);

        initializeViews();
        loadEquipment();
        setupRecyclerView();
        setupButtons();
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
        List<Item> items = new ArrayList<>();
        items.add(new Item("Close a subscription", "Diamond helmet (+10 Protection)", R.drawable.upgradedhelmet, false));
        items.add(new Item("Make a Savings Account", "Diamond Armor (+10 Protection)", R.drawable.upgradedarmor, false));
        items.add(new Item("Add $20 to Savings Account", "Diamond pants (+10 Protection)", R.drawable.upgradedpants, false));

        MyAdapter adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupButtons() {
        Button inventoryButton = findViewById(R.id.inventoryButton);
        Button rewardsButton = findViewById(R.id.rewardsButton);
        Button homeButton = findViewById(R.id.homeButton);

        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_game_page.this, inventory_page.class));
            }
        });

        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_game_page.this, rewards_page.class));
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_game_page.this, main_game_page.class));
            }
        });

        personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_game_page.this, main_character_stats_page.class));
            }
        });
    }
}
