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

import java.util.Map;

public class Input_Subscriptions_Activity extends AppCompatActivity {
    EditText sub_name, sub_amount, sub_name_2, sub_amount_2, sub_name_3, sub_amount_3;
    Button submit, addMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_subscriptions);
        //all our name and amount
        sub_name = findViewById(R.id.sub_name);
        sub_amount = findViewById(R.id.sub_amount);
        sub_name_2 = findViewById(R.id.sub_name_2);
        sub_amount_2 = findViewById(R.id.sub_amount_2);
        sub_name_3 = findViewById(R.id.sub_name_3);
        sub_amount_3 = findViewById(R.id.sub_amount_3);
        submit = findViewById(R.id.submit);
        addMore = findViewById(R.id.addMore);

        //create the subscription sharable
        SharedPreferences sharedPreferences = getSharedPreferences("subscriptions", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //clear first to make sure its empty
        editor.clear();
        //when they click on submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the name and amount
                String name;
                Long amount;
                boolean completed = false;
                int allcompleted = 0;

                //if at least one of them is completed then it can continue
                if(!sub_name.getText().toString().equals("") && !sub_amount.getText().toString().equals("")){
                    name = sub_name.getText().toString();
                    amount = Long.parseLong(sub_amount.getText().toString());
                    editor.putLong(name, amount);
                    completed = true;
                    allcompleted++;
                }
                if(!sub_name_2.getText().toString().equals("") && !sub_amount_2.getText().toString().equals("")){
                    name = sub_name_2.getText().toString();
                    amount = Long.parseLong(sub_amount_2.getText().toString());
                    editor.putLong(name, amount);
                    completed = true;
                    allcompleted++;
                }
                if(!sub_name_3.getText().toString().equals("") && !sub_amount_3.getText().toString().equals("")){
                    name = sub_name_3.getText().toString();
                    amount = Long.parseLong(sub_amount_3.getText().toString());
                    editor.putLong(name, amount);
                    completed = true;
                    allcompleted++;
                }

                //for if statement that if the at least one have been completed
                if (completed){
                    //apply the changes
                    editor.apply();
                    //check if all of them have been saved
                    System.out.println("subs");
                    Map<String, ?> allEntries = sharedPreferences.getAll();
                    for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                        System.out.println(entry.getKey());
                        System.out.println(entry.getValue());
                    }
                    System.out.println("subs");
                    //go to the next activity
                    Intent intent = new Intent(Input_Subscriptions_Activity.this, User_intro_Display.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Input_Subscriptions_Activity.this, "Please fill in at least one subscription", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}