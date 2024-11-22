package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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

        initializeViews();
        setupRecyclerView();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerview);
    }



    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}