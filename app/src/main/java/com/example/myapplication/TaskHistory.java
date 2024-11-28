package com.example.myapplication;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private TaskHistoryViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedTracker;
    private ImageButton personImage;
    private ImageView helmet, chest, pants, sword;
    private MutableLiveData<List<History>> h;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_history);

        initializeViews();
        initializeButtons();
        setupRecyclerView();

        sharedPreferences = getSharedPreferences("GamePagePrefs", MODE_PRIVATE);

        viewModel = new ViewModelProvider(this).get(TaskHistoryViewModel.class);

        adapter = new MyAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel.getHistory().observe(this, histories -> {
            if (histories != null) {
                adapter.updateItems(new ArrayList<>(histories));
            }
        });

        loadEquipment();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
//    public LiveData<List<History>> getHistory() {
//        if (h == null) {
//            h = new MutableLiveData<>();
//            loadHistory();
//        }
//        return h;
//    }
    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerview);
        helmet = findViewById(R.id.helmet);
        chest = findViewById(R.id.chest);
        pants = findViewById(R.id.pants);
        personImage = findViewById(R.id.personImage);
        sword = findViewById(R.id.sword);
    }
    private void initializeButtons()
    {
        Button inventoryButton = findViewById(R.id.inventoryButton);
        Button rewardsButton = findViewById(R.id.rewardsButton);
        Button homeButton = findViewById(R.id.homeButton);
        Button editmenubutton = findViewById(R.id.editmenubutton);
        ImageView personImage=findViewById(R.id.personImage);
        Button help=findViewById(R.id.helpbutton);
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
        help.setOnClickListener(view ->
                startActivity(new Intent(TaskHistory.this, task_history_documentation.class))
        );

    }
    public void updateArmorUI() {
        loadEquipment();
        //  buttonTracker();
    }
    private void loadEquipment() {
        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedTracker.edit();
        int helmetID = sharedTracker.getInt("helmet", 0);
        int chestID = sharedTracker.getInt("chest", 0);
        int pantsID = sharedTracker.getInt("pants", 0);
        int swordID = sharedTracker.getInt("sword", 0);

        // Load helmet image
        if (helmetID == 1) {
            helmet.setImageResource(R.drawable.helmet);
        } else if (helmetID == 2) {
            helmet.setImageResource(R.drawable.upgradedhelmet);
        }else if(helmetID==3){
            helmet.setImageResource(R.drawable.greenhelmet);
        }else if(helmetID==4)
        {
            helmet.setImageResource(R.drawable.goldhelmet);
        }

        // Load chest image
        if (chestID == 1) {
            chest.setImageResource(R.drawable.armor);
        } else if (chestID == 2) {
            chest.setImageResource(R.drawable.upgradedarmor);
        }  else if (chestID == 3) {
            chest.setImageResource(R.drawable.greenarmor);
        }

        // Load pants image
        if (pantsID == 1) {
            pants.setImageResource(R.drawable.pants);
        } else if (pantsID == 2) {
            pants.setImageResource(R.drawable.upgradedpants);
        } else if (pantsID == 3) {
            pants.setImageResource(R.drawable.greenpants);
        }

        // Control sword visibility based on equipment status
        if (swordID == 2) {
            sword.setImageResource(R.drawable.sword);
            sword.setVisibility(View.VISIBLE); // Show if equipped
        } else if(swordID==1){
            sword.setVisibility(View.INVISIBLE); // Hide by default if not equipped
        }
        editor.apply();
    }
//    private void loadHistory()
//    {
//        List<History> historyList = new ArrayList<>();
//
//        // Load your initial list here
//        historyList.add(new History(1, "Close a subscription", "Diamond helmet (+30 Protection)", R.drawable.upgradedhelmet));
//        historyList.add(new History(2, "Make a Savings Account", "Diamond Armor (+30 Protection)", R.drawable.upgradedarmor));
//        historyList.add(new History(3, "Add $20 to Savings Account", "Diamond pants (+30 Protection)", R.drawable.upgradedpants));
//        h.setValue(historyList);
//    }
}