package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Input_Subscriptions_Activity2 extends AppCompatActivity {
    EditText netflix, spotify, hulu, amazon, addSub;
    Button submit, addMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_subscriptions2);
        netflix=findViewById(R.id.Netflix);
        spotify=findViewById(R.id.Spotify);
        hulu=findViewById(R.id.Hulu);
        amazon=findViewById(R.id.AmazonPrime);
        submit=findViewById(R.id.submit);
        addMore=findViewById(R.id.addMore);
        addSub=findViewById(R.id.addSub);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(netflix.getText().toString().isEmpty()||spotify.getText().toString().isEmpty()||
                        amazon.getText().toString().isEmpty()||hulu.getText().toString().isEmpty()||
                        addSub.getText().toString().isEmpty())
                {
                    Toast.makeText(Input_Subscriptions_Activity2.this, "Missing Information", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Input_Subscriptions_Activity2.this, "Completed", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Input_Subscriptions_Activity2.this,main_game_page.class);
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
                Intent intent1=new Intent(Input_Subscriptions_Activity2.this,Input_Subscriptions_Activity3.class);
                startActivity(intent1);

            }
        });
    }
}