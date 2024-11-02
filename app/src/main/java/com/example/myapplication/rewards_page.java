/*
  rewards_page.java

  This activity displays the rewards screen, where users can view and unlock special achievements based on task
  completion. A central reward button allows users to unlock a special sword upon completing three tasks, with a
  popup providing feedback on the sword’s availability and an option to equip it. Equipped rewards are saved using
  SharedPreferences, preserving their state across sessions. The page includes a home button for navigation back
  to the main game page, allowing for seamless movement between game sections.

 */

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
                        saveSwordEquippedState(true);
                        popUpWindow.dismiss();
                    }
                });
            }
        });
    }
    private void saveSwordEquippedState(boolean isEquipped) {
        SharedPreferences sharedPreferences = getSharedPreferences("tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("sword_equipped", isEquipped);
        editor.apply();
    }
}