package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Input_Subscriptions_Activity extends AppCompatActivity {
    EditText netflix, spotify, hulu, amazon;
    Button submit, addMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_subscriptions);
        //initializing the variables
        netflix=findViewById(R.id.Netflix);
        spotify=findViewById(R.id.Spotify);
        hulu=findViewById(R.id.Hulu);
        amazon=findViewById(R.id.AmazonPrime);
        //our buttons
        submit=findViewById(R.id.submit);
        addMore=findViewById(R.id.addMore);
        //shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //when submit is clicked
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                if(netflix.getText().toString().isEmpty()||spotify.getText().toString().isEmpty()||
                        amazon.getText().toString().isEmpty()||hulu.getText().toString().isEmpty())
                {
                    Toast.makeText(Input_Subscriptions_Activity.this, "Missing Information", Toast.LENGTH_SHORT).show();
                }
                //when there is no missing information
                else
                {
                    //display that is completed
                    Toast.makeText(Input_Subscriptions_Activity.this, "Completed", Toast.LENGTH_SHORT).show();
                    //save the subscriptions in shared preferences
                    editor.putLong("sub netflix", Long.parseLong(netflix.getText().toString()));
                    editor.putLong("sub spotify", Long.parseLong(spotify.getText().toString()));
                    editor.putLong("sub amazon", Long.parseLong(amazon.getText().toString()));
                    editor.putLong("sub hulu", Long.parseLong(hulu.getText().toString()));
                    editor.apply();
                    //move to the next activity
                    Intent intent=new Intent(Input_Subscriptions_Activity.this,User_intro_Display.class);
                    startActivity(intent);
                    //test if they saved
//                    System.out.println(sharedPreferences.getLong("sub netflix", 0));
//                    System.out.println(sharedPreferences.getLong("sub spotify", 0));
//                    System.out.println(sharedPreferences.getLong("sub amazon", 0));
//                    System.out.println(sharedPreferences.getLong("sub hulu", 0));
                }
            }
        });
        // addMore will be implemented later
       addMore=findViewById(R.id.addMore);
       addMore.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v)
            {
                //later dues to shortage on time
                Intent intent1=new Intent(Input_Subscriptions_Activity.this,Input_Subscriptions_Activity2.class);
                startActivity(intent1);
//
            }
        });
    }
}