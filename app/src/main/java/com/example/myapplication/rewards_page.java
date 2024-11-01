package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class rewards_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rewards_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Home Button: Navigate to Main Game Page
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rewards_page.this, main_game_page.class);
                startActivity(intent);
            }
        });
        //loadSwordVisibility();
        LinearLayout rewardButton=findViewById(R.id.unlockableAward);
        rewardButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(rewards_page.this, "You need to complete 3 tasks to unlock the sword!", Toast.LENGTH_SHORT).show();
                View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.sword_popup, null);
                PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popUpWindow.showAsDropDown(v);
                Button swordClose = popUpView.findViewById(R.id.swordclose);
                swordClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpWindow.dismiss();
                    }
                });
                Button equipButton = popUpView.findViewById(R.id.equipbutton);
                equipButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //equipSword();
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }
//    private void equipSword()
//    {
//        sword.setVisibility(View.VISIBLE);
//        saveSwordEquippedState(true);
//    }

//    private void loadSwordVisibility() {
//        SharedPreferences sharedPreferences = getSharedPreferences("tracker", MODE_PRIVATE);
//        boolean swordEquipped = sharedPreferences.getBoolean("sword_equipped", false);
//        if (swordEquipped) {
//            sword.setVisibility(View.VISIBLE); // Show sword if equipped before
//        } else {
//            sword.setVisibility(View.INVISIBLE); // Hide sword initially
//        }
    //}
    //private void saveSwordEquippedState(boolean isEquipped) {
        //SharedPreferences sharedPreferences = getSharedPreferences("tracker", MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putBoolean("sword_equipped", isEquipped);
        //editor.apply();
    //}
}