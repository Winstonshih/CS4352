package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class main_game_page extends AppCompatActivity{
    ImageButton personImage;
    private ImageView armorImage2, pantsImage2, helmetImage2;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game_page);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        armorImage2 = findViewById(R.id.armorImage2);
        pantsImage2 = findViewById(R.id.pantsImage2);
        helmetImage2 = findViewById(R.id.helmetImage2);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Close a subscription", "Diamond helmet (+10 Protection)", R.drawable.upgradedhelmet, false));
        items.add(new Item("Make a Savings Account", "Diamond Armor (+10 Protection)", R.drawable.upgradedarmor, false));
        items.add(new Item("Add $20 to Savings Account", "Diamond pants (+10 Protection)", R.drawable.upgradedpants, false));
        MyAdapter adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inventory Button: Navigate to Inventory Page
        Button inventoryButton = findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_game_page.this, inventory_page.class);
                startActivity(intent);
            }
        });

        // Rewards Button: Navigate to Rewards Page
        Button rewardsButton = findViewById(R.id.rewardsButton);
        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_game_page.this, rewards_page.class);
                startActivity(intent);
            }
        });

        // Home Button: Navigate to Main Game Page
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_game_page.this, main_game_page.class);
                startActivity(intent);
            }
        });

        //Ruben Part when they click on the the image
        personImage = (ImageButton) findViewById(R.id.personImage);
        personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to the stats page
                Intent intent = new Intent(main_game_page.this, main_character_stats_page.class);
                startActivity(intent);
            }
        });
    }
}