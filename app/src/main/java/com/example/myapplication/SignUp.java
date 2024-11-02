package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
EditText newLogin, newPassword;
Button signUp;
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
                if(newLogin.getText().toString().isEmpty()||newPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignUp.this, "You need to input a new username or password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUp.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUp.this,Input_Info_Activity.class);
                    startActivity(intent);
                }
            }
        });

    }
}