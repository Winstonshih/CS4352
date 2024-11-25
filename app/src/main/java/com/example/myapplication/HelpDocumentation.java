package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelpDocumentation extends AppCompatActivity {
    private Button taskButton, instructionsButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_help_documentation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        taskButton=findViewById(R.id.TasksButton);
        backButton=findViewById(R.id.backButton);
        instructionsButton=findViewById(R.id.instructionsButton);
        backButton.setOnClickListener(v -> {
            // Navigate to SignUp activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        taskButton.setOnClickListener(v -> {
            // Navigate to SignUp activity
            Intent intent = new Intent(this, TaskOverview.class);
            startActivity(intent);
        });
        instructionsButton.setOnClickListener(v -> {
            // Navigate to SignUp activity
            Intent intent = new Intent(this, Instructions.class);
            startActivity(intent);
        });
    }
}