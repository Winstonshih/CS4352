/* Ruben Rodriguez
 * rar180001
 * Completed
 */

/*
  MainActivity.java

  This activity serves as the main login screen for the app, where users can enter a username and password to access the
  application. It includes hardcoded login credentials for demonstration purposes, with a login button that verifies the input.
  Upon successful login, users are directed to `Input_Info_Activity` to continue. A sign-up button is also provided, allowing
  users to navigate to a separate `SignUp` activity to set up new credentials. The activity utilizes `Toast` messages to
  provide immediate feedback on login success or failure, enhancing the user experience with clear guidance.
*/

package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // UI elements
    private EditText username, password;
    private Button loginButton, signUpButton;

    // SharedPreferences for user data
    private SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this activity
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences
        userPreferences = getSharedPreferences("user", MODE_PRIVATE);

        // Retrieve stored user data (or use defaults)
        String storedUsername = userPreferences.getString("username", "user");
        String storedPassword = userPreferences.getString("password", "1234");

        // Bind UI elements
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signupButton);

        // Set up button click listeners
        loginButton.setOnClickListener(v -> {
            String enteredUsername = username.getText().toString();
            String enteredPassword = password.getText().toString();

            // Validate login credentials
            if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                login();
            } else {
                Toast.makeText(this, "Invalid username or password! Create an Account", Toast.LENGTH_SHORT).show();
            }
        });

        signUpButton.setOnClickListener(v -> {
            // Navigate to SignUp activity
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
    }

    private void login() {
        //just go to the main game page directly
        Intent intent = new Intent(this, main_game_page.class);
        startActivity(intent);

    }
}
