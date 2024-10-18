package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;

public class Input_Subscriptions_Activity3 extends AppCompatActivity {

    EditText netflix, spotify, hulu, amazon, addSub, addSub2;
    Button submit, addMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_subscriptions3);
        netflix=findViewById(R.id.Netflix);
        spotify=findViewById(R.id.Spotify);
        hulu=findViewById(R.id.Hulu);
        amazon=findViewById(R.id.AmazonPrime);
        submit=findViewById(R.id.submit);
        addMore=findViewById(R.id.addMore);
        addSub=findViewById(R.id.addSub);
        addSub2=findViewById(R.id.addSub2);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(netflix.getText().toString().isEmpty()||spotify.getText().toString().isEmpty()||
                        amazon.getText().toString().isEmpty()||hulu.getText().toString().isEmpty()||
                        addSub.getText().toString().isEmpty()||addSub2.getText().toString().isEmpty())
                {
                    Toast.makeText(Input_Subscriptions_Activity3.this, "Missing Information", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Input_Subscriptions_Activity3.this, "Completed", Toast.LENGTH_SHORT).show();
                    editor.putLong("sub netflix", Long.parseLong(netflix.getText().toString()));
                    editor.putLong("sub spotify", Long.parseLong(spotify.getText().toString()));
                    editor.putLong("sub amazon", Long.parseLong(amazon.getText().toString()));
                    editor.putLong("sub hulu", Long.parseLong(hulu.getText().toString()));
                    editor.putLong("sub addsub", Long.parseLong(addSub.getText().toString()));
                    editor.putLong("sub addsub2", Long.parseLong(addSub2.getText().toString()));
                    editor.apply();
                    Intent intent=new Intent(Input_Subscriptions_Activity3.this,User_intro_Display.class);
                    startActivity(intent);
                }
            }
        });
        // addMore will be implemented later
        addMore=findViewById(R.id.addMore);
        addMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Input_Subscriptions_Activity3.this,Input_Subscriptions_Activity4.class);
                startActivity(intent);

            }
        });
    }
}