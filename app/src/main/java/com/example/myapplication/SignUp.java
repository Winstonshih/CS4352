/*
  SignUp.java

  This activity provides a user interface for creating a new account, allowing users to input a custom username
  and password. It includes validation to ensure both fields are filled before submission. Upon successful entry,
  a toast message confirms the sign-up, and the user is directed to the `Input_Info_Activity` to continue setting
  up their profile. If any field is empty, an error message guides the user to complete the necessary fields,
  enhancing the onboarding experience.

 */

package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
EditText newLogin, newPassword;
Button signUp, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        newLogin=findViewById(R.id.createUsername);
        newPassword=findViewById(R.id.createPassword);
        signUp=findViewById(R.id.signUpButton2);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newLogin.getText().toString().isEmpty()||newPassword.getText().toString().isEmpty()||(newLogin.getText().toString().equals("user")&&newPassword.getText().toString().equals("1234"))){
                    Toast.makeText(SignUp.this, "You need to input a new username or password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUp.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                    //add the password and username to the shared preferences
                    String username=newLogin.getText().toString();
                    String password=newPassword.getText().toString();
                    //reset the moneyTracker and subscription data
                    SharedPreferences sharedPreferences = getSharedPreferences("money_tracker", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    SharedPreferences subscriptionPreferences = getSharedPreferences("subscription_data", MODE_PRIVATE);
                    SharedPreferences.Editor subscriptionEditor = subscriptionPreferences.edit();
                    subscriptionEditor.clear();
                    subscriptionEditor.apply();
                    //add the username and password to the shared preferences
                    addAccount(username,password);
                    Intent intent=new Intent(SignUp.this,Input_Info_Activity.class);
                    startActivity(intent);
                }
            }
        });
        back=findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(SignUp.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void addAccount(String username, String password) {
        //create a sharabble that contains the password and user
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
        System.out.println("username: "+username);
        System.out.println("password: "+password);
    }
}