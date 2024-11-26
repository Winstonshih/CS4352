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
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class main_game_page extends AppCompatActivity {
    private ImageButton personImage;
    private ImageView helmet, chest, pants, sword, arrow;
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private SharedPreferences sharedTracker;
    private GamePageViewModel viewModel;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game_page);

        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        initializeViews();
        viewModel = new ViewModelProvider(this).get(GamePageViewModel.class);

        viewModel.getItems().observe(this, items -> {
            if (adapter == null) {
                // Initialize the adapter with an empty list initially
                adapter = new MyAdapter(this, new ArrayList<>(items));
                recyclerView.setAdapter(adapter);
            } else {
                // Ensure items is of type List<Object> if that's what your adapter expects
                List<Object> objectItems = new ArrayList<>(items);
                adapter.updateItems(objectItems);
            }

            // Logging for debugging purposes
            if (items.isEmpty()) {
                System.out.println("empty");
            } else {
                System.out.println("not empty");
            }
        });
        loadEquipment();
        setupRecyclerView();
        setupButtons();
        rewardButton();
//        buttonTracker();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEquipment(); // Refresh the equipment UI
        rewardButton();
       // buttonTracker(); // Refresh the button state

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
        } else if(swordID==1){
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
        Button taskHistoryButton = findViewById(R.id.taskHistory);
        Button editmenubutton = findViewById(R.id.editmenubutton);
        inventoryButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, inventory_page.class))
        );
        rewardsButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, rewards_page.class))
        );
        taskHistoryButton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, TaskHistory.class))
        );
        personImage.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, main_character_stats_page.class))
        );
        editmenubutton.setOnClickListener(view ->
                startActivity(new Intent(main_game_page.this, Input_Info_Activity_2.class))
        );


    }

    public void updateArmorUI() {
        loadEquipment();
        rewardButton();
      //  buttonTracker();
        is_empty_list();

    }
    public void incrementCompletedTasks() {
        SharedPreferences.Editor editor = sharedTracker.edit();
        int completedTasks = sharedTracker.getInt("tasksCompleted", 0);
        editor.putInt("tasksCompleted", completedTasks + 1);
        editor.apply();
    }
    private void rewardButton()
    {
        Button rewardsButton = findViewById(R.id.rewardsButton);
        if(sharedTracker.getBoolean("reward", false)) {
            rewardsButton.setEnabled(true);
        }
        else{
            rewardsButton.setEnabled(false);
            Toast.makeText(main_game_page.this, "You need to complete three tasks to unlock sword!", Toast.LENGTH_SHORT).show();
        }
    }
//    private void buttonTracker(){
//        Button rewardButton = findViewById(R.id.rewardsButton);
//        Button inventoryButton = findViewById(R.id.inventoryButton);
//        personImage = findViewById(R.id.personImage);
//        arrow = findViewById(R.id.arrrow);
//        //check if the person is unlocked
//        if(sharedTracker.getBoolean("person", false)){
//            personImage.setEnabled(true);
//            if(sharedTracker.getBoolean("person color", false)){
//                //if its true then dark green
//
//                arrow.setVisibility(View.VISIBLE);
//            }
//            else{
//                //set back to the original
//                arrow.setVisibility(View.INVISIBLE);
//            }
//        }
//        else{
//            personImage.setEnabled(false);
//
//        }
//
//        // Check if the reward is unlocked
//        if(sharedTracker.getBoolean("reward", false)){
//            rewardButton.setEnabled(true);
//            //change the color of the button if it is the next thing they need to click
//            if(sharedTracker.getBoolean("reward color", false)){
//                rewardButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
//            }
//            else{
//                rewardButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
//            }
//        } else {
//            rewardButton.setEnabled(false); // Explicitly disable if reward is locked
//        }
//
//        //check if inventory is unlocked
//        if(sharedTracker.getBoolean("inventory", false)){
//            inventoryButton.setEnabled(true);
//            //change the color of the button if it is the next thing
//            if(sharedTracker.getBoolean("inventory color", false)){
//                inventoryButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
//            }
//            else{
//                inventoryButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
//            }
//        }
//        else{
//            inventoryButton.setEnabled(false);
//        }
//
//    }
    private void is_empty_list(){
        //set the relative layout to gone
        relativeLayout = findViewById(R.id.relativeLayout);

        //IF ITS TRUE THAT IT IS EMPTY
        if (sharedTracker.getBoolean("empty list", false)){
            //MAKE POP UP SHOW THAT WAIT 1 WEEK FOR NEXT GOAL
            //make it visible
            System.out.println("Checker: empty list");
            relativeLayout.setVisibility(View.VISIBLE);
        }
        else{
            System.out.println("Checker: not empty list");
            //so it is gone first
            relativeLayout.setVisibility(View.GONE);
        }
    }

}
