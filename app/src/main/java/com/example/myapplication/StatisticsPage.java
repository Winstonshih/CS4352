package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsPage extends AppCompatActivity {

    private TextView totalTasksCompletedView, totalRewardsClaimedView, totalMoneySavedView;
    private Button resetButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics_page);

        // Initialize TextViews and Buttons
        totalTasksCompletedView = findViewById(R.id.totalTasksCompleted);
        totalRewardsClaimedView = findViewById(R.id.totalRewardsClaimed);
        totalMoneySavedView = findViewById(R.id.totalMoneySaved);
        resetButton = findViewById(R.id.resetButton);
        backButton = findViewById(R.id.backButton);

        // Load initial statistics
        loadStatistics();

        // Set up reset button to clear stats
        resetButton.setOnClickListener(v -> resetStatistics());

        // Set up back button to return to main game page
        backButton.setOnClickListener(v -> finish());
    }

    private void loadStatistics() {
        SharedPreferences statsPrefs = getSharedPreferences("statistics", MODE_PRIVATE);
        int tasksCompleted = statsPrefs.getInt("tasks_completed", 0);
        int rewardsClaimed = statsPrefs.getInt("rewards_claimed", 0);
        long moneySaved = statsPrefs.getLong("total_money_saved", 0);

        // Update TextViews with data
        totalTasksCompletedView.setText("Tasks Completed: " + tasksCompleted);
        totalRewardsClaimedView.setText("Rewards Claimed: " + rewardsClaimed);
        totalMoneySavedView.setText("Total Money Saved: $" + moneySaved);
    }

    public void updateStatistics(int newTasks, int newRewards, long moneySaved) {
        SharedPreferences statsPrefs = getSharedPreferences("statistics", MODE_PRIVATE);
        SharedPreferences.Editor editor = statsPrefs.edit();

        int tasksCompleted = statsPrefs.getInt("tasks_completed", 0) + newTasks;
        int rewardsClaimed = statsPrefs.getInt("rewards_claimed", 0) + newRewards;
        long totalMoneySaved = statsPrefs.getLong("total_money_saved", 0) + moneySaved;

        // Save updated statistics
        editor.putInt("tasks_completed", tasksCompleted);
        editor.putInt("rewards_claimed", rewardsClaimed);
        editor.putLong("total_money_saved", totalMoneySaved);
        editor.apply();

        // Refresh TextViews to reflect updated data
        loadStatistics();
    }

    private void resetStatistics() {
        SharedPreferences statsPrefs = getSharedPreferences("statistics", MODE_PRIVATE);
        SharedPreferences.Editor editor = statsPrefs.edit();

        editor.putInt("tasks_completed", 0);
        editor.putInt("rewards_claimed", 0);
        editor.putLong("total_money_saved", 0);
        editor.apply();

        // Reload statistics to reset views
        loadStatistics();
    }
}
