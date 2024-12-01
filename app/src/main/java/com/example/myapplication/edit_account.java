package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class edit_account extends AppCompatActivity {
    Button backButton, submitButton;
    EditText oldPassword, newPassword, matchPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_account);
        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submitButton);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        matchPassword = findViewById(R.id.matchPassword);

        //when they click submit button it goes back to the previous activity
        backButton.setOnClickListener(v -> finish());
        submitButton.setOnClickListener(v -> submit());
    }
    private void submit() {
        String oldPass = oldPassword.getText().toString();
        String newPass = newPassword.getText().toString();
        String matchPass = matchPassword.getText().toString();
        //user shared Preference
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        //get the old password
        String oldPassword = sharedPreferences.getString("password", "");
        //check if the old password matches and the new password matches the match password
        if (oldPass.equals(oldPassword) && newPass.equals(matchPass)) {
            //if it matches then update the password
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("password", newPass);
            editor.apply();
            Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
            //go to main game page
            Intent intent = new Intent(this, main_game_page.class);
            startActivity(intent);
        } else {
            //toast message if it doesnt match
            Toast.makeText(this, "Old password or new password do not match", Toast.LENGTH_SHORT).show();
        }
    }
}