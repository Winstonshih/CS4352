/*
  Inventory_page.java

  This activity displays the user's inventory of equipment and allows them to equip different items by interacting with
  ImageView components representing helmets, armor, pants, and weapons. Each item triggers a popup window with details
  and an option to equip the item, which is saved to SharedPreferences for tracking equipment state across sessions.
  Additionally, the activity updates item visibility based on task completion, ensuring that upgraded items become visible
  only when relevant tasks are complete. Navigation buttons are included to allow users to switch between the rewards and
  main game pages.

 */

package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class inventory_page extends AppCompatActivity {
    private ImageView helmet1, armor1, pants1, helmet2, armor2, pants2, weapon1, helmet3, helmet4, pants3, armor3;
    private Button rewardsButton, homeButton;
    private SharedPreferences sharedTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory_page);
        helmet1 = findViewById(R.id.helmet1);
        pants1=findViewById(R.id.pants1);
        armor1=findViewById(R.id.armor1);
        helmet2 = findViewById(R.id.helmet2);
        armor2 = findViewById(R.id.armor2);
        pants2 = findViewById(R.id.pants2);
        weapon1=findViewById(R.id.weapon1);
        armor3=findViewById(R.id.armor3);
        helmet3=findViewById(R.id.helmet3);
        helmet4=findViewById(R.id.helmet4);
        pants3=findViewById(R.id.pants3);
        //set buttons
        rewardsButton = findViewById(R.id.rewardsButton);
        homeButton = findViewById(R.id.homeButton);
        //when they click home it takes them to main game page
        homeButton.setOnClickListener(view ->
                startActivity(new Intent(inventory_page.this, main_game_page.class))
        );
        //when they click rewards it takes them to rewards page
        rewardsButton.setOnClickListener(view ->
                startActivity(new Intent(inventory_page.this, rewards_page.class))
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        setupHelmet1();
        setupArmor1();
        setupPants1();
        setupWeapon1();
        setupPants2();
        setupArmor2();
        setupHelmet2();
        trackHelmet();
        trackChest();
        trackPants();
       // ButtonTracker();

    }
    private void setupHelmet1() {
        ImageView helmet1 = findViewById(R.id.helmet1);
        helmet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.base_helmet_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button helmet1Close = popUpView.findViewById(R.id.helmetclose);
                helmet1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });

                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedTracker.edit();
                        editor.putInt("helmet", 1); // ID for base helmet
                        editor.apply();
                        trackHelmet();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    private void setupArmor1() {
        ImageView armor1 = findViewById(R.id.armor1);
        armor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.base_chestplate_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button armor1Close = popUpView.findViewById(R.id.armorclose);
                armor1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });

                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedTracker.edit();
                        editor.putInt("chest", 1); // ID for base armor
                        editor.apply();
                        trackChest();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    private void setupPants1() {
        ImageView pants1 = findViewById(R.id.pants1);
        pants1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.base_pants_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button pants1Close = popUpView.findViewById(R.id.pantsclose);
                pants1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });

                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedTracker.edit();
                        editor.putInt("pants", 1); // ID for base pants
                        editor.apply();
                        trackPants();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    private void setupWeapon1() {
        ImageView weapon1 = findViewById(R.id.weapon1);
        weapon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.sword_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button weapon1Close = popUpView.findViewById(R.id.swordclose);
                weapon1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    private void setupHelmet2() {
        ImageView helmet2 = findViewById(R.id.helmet2);
        helmet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.upgraded_helmet_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button helmet2Close = popUpView.findViewById(R.id.upgradedhelmetclose);
                helmet2Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });

                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedTracker.edit();
                        editor.putInt("helmet", 2); // ID for upgraded helmet
                        editor.apply();
                        trackHelmet();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    private void setupArmor2() {
        ImageView armor2 = findViewById(R.id.armor2);
        armor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.upgraded_chestplate_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button armor2Close = popUpView.findViewById(R.id.upgradedarmorclose);
                armor2Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });

                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedTracker.edit();
                        editor.putInt("chest", 2); // ID for upgraded armor
                        editor.apply();
                        trackChest();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    private void setupPants2() {
        ImageView pants2 = findViewById(R.id.pants2);
        pants2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.upgraded_pants_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);

                Button pants2Close = popUpView.findViewById(R.id.upgradedpantsclose);
                pants2Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });

                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedTracker.edit();
                        editor.putInt("pants", 2); // ID for upgraded pants
                        editor.apply();
                        trackPants();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInventoryEquipment();
        trackHelmet();
        trackChest();
        trackPants();
    }
    private void loadInventoryEquipment() {
        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        int helmetID = sharedTracker.getInt("helmet", 0);
        int chestID = sharedTracker.getInt("chest", 0);
        int pantsID = sharedTracker.getInt("pants", 0);
        int swordID=sharedTracker.getInt("sword", 0);
        boolean task1Completed = sharedTracker.getBoolean("task_1_completed", false);
        boolean task2Completed = sharedTracker.getBoolean("task_2_completed", false);
        boolean task3Completed = sharedTracker.getBoolean("task_3_completed", false);
        boolean task4Completed = sharedTracker.getBoolean("task_4_completed", false);
        boolean task5Completed = sharedTracker.getBoolean("task_5_completed", false);
        boolean task6Completed = sharedTracker.getBoolean("task_6_completed", false);
        boolean task7Completed = sharedTracker.getBoolean("task_7_completed", false);
        helmet2.setVisibility(task1Completed ? View.VISIBLE : View.INVISIBLE);
        armor2.setVisibility(task2Completed ? View.VISIBLE : View.INVISIBLE);
        pants2.setVisibility(task3Completed ? View.VISIBLE : View.INVISIBLE);
        helmet3.setVisibility(task4Completed ? View.VISIBLE : View.INVISIBLE);
        armor3.setVisibility(task5Completed ? View.VISIBLE : View.INVISIBLE);
        pants3.setVisibility(task6Completed ? View.VISIBLE : View.INVISIBLE);
        helmet4.setVisibility(task7Completed ? View.VISIBLE : View.INVISIBLE);
        weapon1.setVisibility(swordID==2?View.VISIBLE:View.INVISIBLE);
    }
    private void trackChest()
    {
        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        int equippedArmorId=sharedTracker.getInt("chest", 0);
        armor1.setAlpha(equippedArmorId==1?1.0f:0.5f);
        armor2.setAlpha(equippedArmorId==2?1.0f:0.5f);
        armor3.setAlpha(equippedArmorId==3?1.0f:0.5f);
    }
    private void trackHelmet()
    {
        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        int equippedHelmetId = sharedTracker.getInt("helmet", 0);
        helmet1.setAlpha(equippedHelmetId == 1 ? 1.0f : 0.5f);
        helmet2.setAlpha(equippedHelmetId == 2 ? 1.0f : 0.5f);
        helmet3.setAlpha(equippedHelmetId == 3 ? 1.0f : 0.5f);
        helmet4.setAlpha(equippedHelmetId == 4 ? 1.0f : 0.5f);
    }
    private void trackPants()
    {
        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        int equippedPantsId=sharedTracker.getInt("pants", 0);
        pants1.setAlpha(equippedPantsId==1?1.0f:0.5f);
        pants2.setAlpha(equippedPantsId==2?1.0f:0.5f);
        pants3.setAlpha(equippedPantsId==3?1.0f:0.5f);
    }
//    private void ButtonTracker(){
//        rewardsButton = findViewById(R.id.rewardsButton);
//        homeButton = findViewById(R.id.homeButton);
//        //first we are going to the check the shared preference
//        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedTracker.edit();
//        //if last tracker is false
//        if (!sharedTracker.getBoolean("last tracker", false)){
//            //change the color to green
//            rewardsButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
//            //unlock the rewards page and set it to gree
//            editor.putBoolean("reward", true);
//            editor.putBoolean("reward color", true);
//            //change the inventory page to back to original color
//            editor.putBoolean("inventory color", false);
//
//
//            //apply these changes
//            editor.apply();
//        }
//        //if its something else then turn it into shadow grey
//        else{
//            if(sharedTracker.getBoolean("reward color", false)){
//                rewardsButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
//            }
//            rewardsButton.setBackgroundColor(getResources().getColor(R.color.shadow_grey));
//        }
//
//
//        //if they click the rewards button the rewards page will open and also change it back to dark grey
//        rewardsButton.setOnClickListener(view ->{
//            //if they click to it then set the last tracker to true
//            editor.putBoolean("last tracker", true);
//            editor.apply();
//            //go to the rewards page
//            startActivity(new Intent(inventory_page.this, rewards_page.class));
//                }
//
//        );
//        homeButton.setOnClickListener(view ->
//                startActivity(new Intent(inventory_page.this, main_game_page.class))
//        );
//
//    }
    //set the buttons

}

