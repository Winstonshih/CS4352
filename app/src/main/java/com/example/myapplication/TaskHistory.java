package com.example.myapplication;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private GamePageViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_history);

        sharedPreferences = getSharedPreferences("GamePagePrefs", MODE_PRIVATE);
        initializeButtons();
        initializeViews();
        setupRecyclerView();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerview);
    }
    private void initializeButtons()
    {
        Button inventoryButton = findViewById(R.id.inventoryButton);
        Button rewardsButton = findViewById(R.id.rewardsButton);
        Button homeButton = findViewById(R.id.homeButton);
        Button editmenubutton = findViewById(R.id.editmenubutton);
        ImageView personImage=findViewById(R.id.personImage);
        inventoryButton.setOnClickListener(view ->
                startActivity(new Intent(TaskHistory.this, inventory_page.class))
        );
        rewardsButton.setOnClickListener(view ->
                startActivity(new Intent(TaskHistory.this, rewards_page.class))
        );
        homeButton.setOnClickListener(view ->
                startActivity(new Intent(TaskHistory.this, main_game_page.class))
        );
        personImage.setOnClickListener(view ->
                startActivity(new Intent(TaskHistory.this, main_character_stats_page.class))
        );
        editmenubutton.setOnClickListener(view ->
                startActivity(new Intent(TaskHistory.this, Input_Info_Activity_2.class))
        );
    }


    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}