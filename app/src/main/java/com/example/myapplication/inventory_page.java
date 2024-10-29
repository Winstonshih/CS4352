package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class inventory_page extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button rewardsButton = findViewById(R.id.rewardsButton);
        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventory_page.this, rewards_page.class);
                startActivity(intent);
            }
        });

        // Home Button: Navigate to Main Game Page
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inventory_page.this, main_game_page.class);
                startActivity(intent);
            }
        });
        //Procedure for clicking on helmet 1 for stats.
        ImageView helmet1=findViewById(R.id.helmet1);
        helmet1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.base_helmet_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button helmet1Close=popUpView.findViewById(R.id.helmetclose); // Ensure your dialog layout has a button with this ID
                helmet1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
        //Procedure for clicking on armor 1 for stats.
        ImageView armor1=findViewById(R.id.armor1);
        armor1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.base_chestplate_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button armor1Close=popUpView.findViewById(R.id.armorclose); // Ensure your dialog layout has a button with this ID
                armor1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
        //Procedure for clicking on pants 1 for stats.
        ImageView pants1=findViewById(R.id.pants1);
        pants1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.base_pants_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button pants1Close=popUpView.findViewById(R.id.pantsclose); // Ensure your dialog layout has a button with this ID
                pants1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
        //Procedure for clicking on secret unlockable sword for stats.
        ImageView weapon1=findViewById(R.id.weapon1);
        weapon1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.sword_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button weapon1Close=popUpView.findViewById(R.id.swordclose); // Ensure your dialog layout has a button with this ID
                weapon1Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });

        ImageView pants2=findViewById(R.id.pants2);
        pants2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.upgraded_pants_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button pants2Close=popUpView.findViewById(R.id.upgradedpantsclose); // Ensure your dialog layout has a button with this ID
                pants2Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
        ImageView armor2=findViewById(R.id.armor2);
        armor2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.upgraded_chestplate_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button armor2Close=popUpView.findViewById(R.id.upgradedarmorclose); // Ensure your dialog layout has a button with this ID
                armor2Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
        ImageView helmet2=findViewById(R.id.helmet2);
        helmet2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.upgraded_helmet_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button helmet2Close=popUpView.findViewById(R.id.upgradedhelmetclose); // Ensure your dialog layout has a button with this ID
                helmet2Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        updateInventoryUI();
    }
    public void updateInventoryUI() {
        SharedPreferences sharedPreferences = getSharedPreferences("inventory", Context.MODE_PRIVATE);
        boolean isTask1Complete = sharedPreferences.getBoolean("task1_complete", false); // Replace with your actual task key
        boolean isTask2Complete = sharedPreferences.getBoolean("task2_complete", false); // Replace with your actual task key
        boolean isTask3Complete = sharedPreferences.getBoolean("task3_complete", false); // Replace with your actual task key
        boolean isHelmet2Visible = sharedPreferences.getBoolean("helmet2_visible", false)&&isTask1Complete;
        boolean isArmor2Visible = sharedPreferences.getBoolean("armor2_visible", false)&&isTask2Complete;
        boolean arePantsVisible = sharedPreferences.getBoolean("pants2_visible", false)&&isTask3Complete;

        ImageView helmet2 = findViewById(R.id.helmet2);
        ImageView armor2 = findViewById(R.id.armor2);
        ImageView pants2 = findViewById(R.id.pants2);

        // Update visibility based on SharedPreferences values
        helmet2.setVisibility(isHelmet2Visible ? View.VISIBLE : View.GONE);
        armor2.setVisibility(isArmor2Visible ? View.VISIBLE : View.GONE);
        pants2.setVisibility(arePantsVisible ? View.VISIBLE : View.GONE);
    }
}