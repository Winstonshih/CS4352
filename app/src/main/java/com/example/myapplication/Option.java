package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Option extends AppCompatActivity {
    Button back, finances, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_option);
        back = findViewById(R.id.backButton);
        finances = findViewById(R.id.finances);
        account = findViewById(R.id.account);
        back.setOnClickListener(v -> finish());
        //finance button go to activity input 2
        finances.setOnClickListener(v -> {
            Intent intent = new Intent(Option.this,Input_Info_Activity_2.class);
            startActivity(intent);
        });
        //account button go to activity edit account
        account.setOnClickListener(v -> {
            Intent intent = new Intent(Option.this,edit_account.class);
            startActivity(intent);
        });





    }
}