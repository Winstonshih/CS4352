/* Ruben Rodriguez
* rar180001
* Completed*/
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class  MainActivity extends AppCompatActivity {
    // these is our user and password that is already set
EditText username;
EditText password;
Button loginButton, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //set the content that we are receiving from the user
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        //when they click the button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the username and password is correct
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity.this, "Log in Successful!", Toast.LENGTH_SHORT).show();
                    //go to the next page or activity
                    Intent intent=new Intent(MainActivity.this,Input_Info_Activity.class);
                    startActivity(intent);
                    //there we go to the next page or better know as activity Input_Info_Activity
                } else {
                    //if the username and password is incorrect

                    Toast.makeText(MainActivity.this, "Log in Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signUpButton=findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Set up your username and password!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}