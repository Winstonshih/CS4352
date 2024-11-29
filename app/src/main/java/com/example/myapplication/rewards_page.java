/*
  rewards_page.java

  This activity displays the rewards screen, where users can view and unlock special achievements based on task
  completion. A central reward button allows users to unlock a special sword upon completing three tasks, with a
  popup providing feedback on the sword’s availability and an option to equip it. Equipped rewards are saved using
  SharedPreferences, preserving their state across sessions. The page includes a home button for navigation back
  to the main game page, allowing for seamless movement between game sections.

 */

package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class rewards_page extends AppCompatActivity {
    private SharedPreferences sharedTracker;
    private LinearLayout rewardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rewards_page);

        // Set padding to accommodate system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUpSword();
        resetColor();


        // Home Button: Navigate to Main Game Page
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(rewards_page.this, main_game_page.class);
            startActivity(intent);
        });
        LinearLayout lockedAward1=findViewById(R.id.redArmor);
        lockedAward1.setOnClickListener(v ->
        {
            View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.locked_reward_1_popup, null);
            PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popUpWindow.showAsDropDown(v);

            Button armor3Close = popUpView.findViewById(R.id.armor3close);
            armor3Close.setOnClickListener(v1 -> popUpWindow.dismiss());
        });
        LinearLayout lockedAward2=findViewById(R.id.redHelmet);
        lockedAward2.setOnClickListener(v ->
        {
            View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.locked_reward_2_pop_up, null);
            PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popUpWindow.showAsDropDown(v);

            Button armor3Close = popUpView.findViewById(R.id.helmetclose);
            armor3Close.setOnClickListener(v1 -> popUpWindow.dismiss());
        });
        LinearLayout lockedAward3=findViewById(R.id.redPants);
        lockedAward3.setOnClickListener(v ->
        {
            View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.locked_reward_3_popup, null);
            PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popUpWindow.showAsDropDown(v);

            Button armor3Close = popUpView.findViewById(R.id.pantsclose);
            armor3Close.setOnClickListener(v1 -> popUpWindow.dismiss());
        });
    }

    private void setUpSword() {
        rewardButton = findViewById(R.id.unlockableAward);
        //Textview of the button
        TextView rewardText = findViewById(R.id.unlockableAwardText);
        //LinearLayout locked message
        LinearLayout lockedMessage = findViewById(R.id.lockMessage);

        //use one last tracker if the sword is available
        sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);

        //if there is an empty list,
        if (sharedTracker.getBoolean("accepted", false)) {
            //make everything invisible and disable the button
            rewardButton.setVisibility(View.INVISIBLE);
            rewardButton.setEnabled(false);
            lockedMessage.setVisibility(View.INVISIBLE);
        }
        else if (sharedTracker.getBoolean("empty list", false)) {
            //change the color of the reward button
            rewardButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
            //change the text in textview
            rewardText.setText("UNLOCKED");
            //Linear Layout make invisible
            lockedMessage.setVisibility(View.INVISIBLE);
            //disable the button
            rewardButton.setEnabled(true);

        }
        //if its not
        else {
            //set the  button to false enable
            rewardButton.setEnabled(false);

        }

        rewardButton.setOnClickListener(v -> {
            View popUpView = LayoutInflater.from(v.getContext()).inflate(R.layout.sword_popup, null);
            PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popUpWindow.showAsDropDown(v);

            Button swordClose = popUpView.findViewById(R.id.swordclose);
            swordClose.setOnClickListener(v1 -> popUpWindow.dismiss());

            Button equipButton = popUpView.findViewById(R.id.equipbutton);
            equipButton.setOnClickListener(v12 -> {
                    // Equip the sword
                SharedPreferences.Editor editor = sharedTracker.edit();
                editor.putInt("sword", 2); // Set sword ID to 2 when equipped
                editor.putBoolean("last sword", true);
                editor.apply();
                popUpWindow.dismiss();
                Toast.makeText(rewards_page.this, "Sword equipped!", Toast.LENGTH_SHORT).show();
                rewardButton.setEnabled(false);
                rewardButton.setVisibility(View.INVISIBLE);
                //set accepted from Shared tracker to false
                sharedTracker.edit().putBoolean("accepted", true).apply();
                // Start the main game page
                Intent intent = new Intent(rewards_page.this, main_game_page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                finish();
            });
        });
    }

    private  void resetColor(){
        SharedPreferences sharedTracker = getSharedPreferences("tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedTracker.edit();
        editor.putBoolean("reward color", false);
        editor.apply();
    }

}

