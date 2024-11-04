package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsPage extends AppCompatActivity {
    private TextView totalTasksCompleted, totalRewardsClaimed, totalMoneySaved;
    private TextView highestSavingsAchieved, longestStreak, numberOfResets;
    private ProgressBar milestoneProgress;
    private Button resetButton, backButton, shareButton;
    private SharedPreferences sharedPreferences;
    private int taskStreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics_page);

        // Initialize views
        totalTasksCompleted = findViewById(R.id.totalTasksCompleted);
        totalRewardsClaimed = findViewById(R.id.totalRewardsClaimed);
        totalMoneySaved = findViewById(R.id.totalMoneySaved);
        highestSavingsAchieved = findViewById(R.id.highestSavingsAchieved);
        longestStreak = findViewById(R.id.longestStreak);
        numberOfResets = findViewById(R.id.numberOfResets);
        milestoneProgress = findViewById(R.id.milestoneProgress);
        resetButton = findViewById(R.id.resetButton);
        backButton = findViewById(R.id.backButton);
        shareButton = findViewById(R.id.shareButton);

        // Load statistics from SharedPreferences
        sharedPreferences = getSharedPreferences("StatisticsPrefs", Context.MODE_PRIVATE);
        loadStatistics();

        // Reset button functionality
        resetButton.setOnClickListener(v -> resetStatistics());

        // Back button functionality
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(StatisticsPage.this, main_game_page.class);
            startActivity(intent);
        });

        // Share button functionality
        shareButton.setOnClickListener(v -> shareStatistics());

        // Check for achievement badges
        checkAchievements();
    }

    private void loadStatistics() {
        int tasks = sharedPreferences.getInt("tasks_completed", 0);
        int rewards = sharedPreferences.getInt("rewards_claimed", 0);
        long savings = sharedPreferences.getLong("total_money_saved", 0);
        long highestSavings = sharedPreferences.getLong("highest_savings", 0);
        taskStreak = sharedPreferences.getInt("task_streak", 0);
        int resets = sharedPreferences.getInt("resets", 0);

        totalTasksCompleted.setText("Tasks Completed: " + tasks);
        totalRewardsClaimed.setText("Rewards Claimed: " + rewards);
        totalMoneySaved.setText("Total Money Saved: $" + savings);
        highestSavingsAchieved.setText("Highest Savings Achieved: $" + highestSavings);
        longestStreak.setText("Longest Streak: " + taskStreak + " days");
        numberOfResets.setText("Number of Resets: " + resets);

        // Set progress in the milestone bar (e.g., milestone at 10 tasks)
        milestoneProgress.setProgress(tasks * 10);  // Assuming milestone is 10 tasks
    }

    private void resetStatistics() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tasks_completed", 0);
        editor.putInt("rewards_claimed", 0);
        editor.putLong("total_money_saved", 0);
        editor.putLong("highest_savings", 0);
        editor.putInt("task_streak", 0);
        editor.putInt("resets", sharedPreferences.getInt("resets", 0) + 1);
        editor.apply();
        taskStreak = 0;
        Toast.makeText(this, "Statistics Reset!", Toast.LENGTH_SHORT).show();
        loadStatistics();
    }

    private void shareStatistics() {
        int tasks = sharedPreferences.getInt("tasks_completed", 0);
        int rewards = sharedPreferences.getInt("rewards_claimed", 0);
        long savings = sharedPreferences.getLong("total_money_saved", 0);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "I've completed " + tasks + " tasks, claimed " + rewards
                + " rewards, and saved $" + savings + " with the app! Try it yourself!");
        shareIntent.setType("text/plain");

        startActivity(Intent.createChooser(shareIntent, "Share your progress"));
    }

    private void checkAchievements() {
        int tasks = sharedPreferences.getInt("tasks_completed", 0);
        long savings = sharedPreferences.getLong("total_money_saved", 0);

        if (tasks >= 10) {
            Toast.makeText(this, "Achievement Unlocked: Task Master!", Toast.LENGTH_SHORT).show();
        }
        if (savings >= 100) {
            Toast.makeText(this, "Achievement Unlocked: Savings Guru!", Toast.LENGTH_SHORT).show();
        }
        if (taskStreak >= 7) {
            Toast.makeText(this, "Achievement Unlocked: Weekly Streak!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStatistics();
    }
}
