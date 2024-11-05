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
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class main_game_page extends AppCompatActivity {
    private ImageButton personImage;
    private ImageView helmet, chest, pants, sword;
    private RecyclerView recyclerView;
    private SharedPreferences sharedTracker;
    private GamePageViewModel viewModel;
    private MyAdapter adapter;
    private PopupWindow popUpWindow;
    private ProgressBar progressBar;
    private int totalTasks = 0;
    private int completedTasks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game_page);

        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        initializeViews();
        viewModel = new ViewModelProvider(this).get(GamePageViewModel.class);

        viewModel.getItems().observe(this, items -> {
            totalTasks = items.size(); // Update total task count
            completedTasks = getCompletedTaskCount(items);
            updateProgressBar();

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
        buttonTracker();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEquipment(); // Refresh the equipment UI
        buttonTracker(); // Refresh the button state

        // Delay the popup to allow the activity to settle fully
        new Handler().postDelayed(this::is_empty_list, 100);
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerview);
        helmet = findViewById(R.id.helmet);
        chest = findViewById(R.id.chest);
        pants = findViewById(R.id.pants);
        personImage = findViewById(R.id.personImage);
        sword = findViewById(R.id.sword);
        progressBar = findViewById(R.id.progressBar);
    }

    private void loadEquipment() {
        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedTracker.edit();
        int helmetID = sharedTracker.getInt("helmet", 0);
        int chestID = sharedTracker.getInt("chest", 0);
        int pantsID = sharedTracker.getInt("pants", 0);
        int swordID = sharedTracker.getInt("sword", 0);

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

        // Control sword visibility based on equipment status
        if (swordID == 2) {
            sword.setImageResource(R.drawable.sword);
            sword.setVisibility(View.VISIBLE); // Show if equipped
        } else if (swordID == 1) {
            sword.setVisibility(View.INVISIBLE); // Hide by default if not equipped
        }
        editor.apply();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupButtons() {
        Button inventoryButton = findViewById(R.id.inventoryButton);
        Button rewardsButton = findViewById(R.id.rewardsButton);
        Button homeButton = findViewById(R.id.homeButton);
        Button editInfoButton = findViewById(R.id.editmenubutton);

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
        editInfoButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, Input_Info_Activity.class))
        );
    }

    public void updateArmorUI() {
        loadEquipment();
        buttonTracker();
        is_empty_list();
    }

    public void incrementCompletedTasks() {
        SharedPreferences.Editor editor = sharedTracker.edit();
        int completedTasks = sharedTracker.getInt("tasksCompleted", 0);
        editor.putInt("tasksCompleted", completedTasks + 1);
        editor.apply();
    }

    private void buttonTracker() {
        Button rewardButton = findViewById(R.id.rewardsButton);
        Button inventoryButton = findViewById(R.id.inventoryButton);
        personImage = findViewById(R.id.personImage);

        // check if the person is unlocked
        if (sharedTracker.getBoolean("person", false)) {
            personImage.setEnabled(true);
            if (sharedTracker.getBoolean("person color", false)) {
                personImage.setBackgroundColor(getResources().getColor(R.color.dark_green));
            } else {
                personImage.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            personImage.setEnabled(false);
        }

        // Check if the reward is unlocked
        if (sharedTracker.getBoolean("reward", false)) {
            rewardButton.setEnabled(true);
            if (sharedTracker.getBoolean("reward color", false)) {
                rewardButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
            } else {
                rewardButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
            }
        } else {
            rewardButton.setEnabled(false);
        }

        // Check if inventory is unlocked
        if (sharedTracker.getBoolean("inventory", false)) {
            inventoryButton.setEnabled(true);
            if (sharedTracker.getBoolean("inventory color", false)) {
                inventoryButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
            } else {
                inventoryButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
            }
        } else {
            inventoryButton.setEnabled(false);
        }
    }

    private void is_empty_list() {
        if (sharedTracker.getBoolean("empty list", false)) {
            showPopup();
        }
    }

    private void showPopup() {
        if (popUpWindow == null) {
            View popUpView = LayoutInflater.from(this).inflate(R.layout.task_completion, null);
            popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

            Button taskCompleteClose = popUpView.findViewById(R.id.taskCompletionClose);
            taskCompleteClose.setOnClickListener(v -> popUpWindow.dismiss());

            popUpWindow.setOnDismissListener(() -> popUpWindow = null);
        }

        if (!popUpWindow.isShowing() && !isFinishing() && !isDestroyed()) {
            View financialGoalsView = findViewById(R.id.recyclerview);
            int[] location = new int[2];
            financialGoalsView.getLocationInWindow(location);

            int x = location[0] + 150;
            int y = location[1] + financialGoalsView.getHeight();

            popUpWindow.showAtLocation(financialGoalsView, Gravity.NO_GRAVITY, x, y);
        }
    }

    private void updateProgressBar() {
        if (totalTasks > 0) {
            int progress = (int) ((completedTasks / (float) totalTasks) * 100);
            progressBar.setProgress(progress);
        } else {
            progressBar.setProgress(0);
        }
    }

    private int getCompletedTaskCount(List<Item> items) {
        int count = 0;
        for (Item item : items) {
            if (item.isClaimed()) {
                count++;
            }
        }
        return count;
    }
}
