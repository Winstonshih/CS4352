/*Ruben Rodriguez
rar180001
incomplete
* */
package com.example.myapplication;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Input_Info_Activity extends AppCompatActivity {
    EditText Income;
    EditText Food;
    EditText mortage;
    EditText waterBill;
    EditText electricBill;
    EditText gasBill;
    EditText internetBill;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_info);
        Income = findViewById(R.id.Income);
        Food = findViewById(R.id.Food);
        mortage = findViewById(R.id.mortage);
        waterBill = findViewById(R.id.waterBill);
        electricBill= findViewById(R.id.electricBill);
        gasBill = findViewById(R.id.gasBill);
        internetBill = findViewById(R.id.internetBill);
        nextButton = findViewById(R.id.nextButton);

        //when they click the next button
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //actions that are going to happen when they click the next button

               //test
               /*int test = parseInt(Income.getText().toString());
               System.out.println(test);*/
                //if one of the text are empty
               if (
                       Income.getText().toString().isEmpty() ||
                               Food.getText().toString().isEmpty() ||
                                   mortage.getText().toString().isEmpty() ||
                                   waterBill.getText().toString().isEmpty() ||
                               electricBill.getText().toString().isEmpty() ||
                               gasBill.getText().toString().isEmpty() ||
                               internetBill.getText().toString().isEmpty()
               ){
                   Toast.makeText(Input_Info_Activity.this, "Missing Information", Toast.LENGTH_SHORT).show();
               }
               //if all the information is complete
               else{
                   Toast.makeText(Input_Info_Activity.this, "Completed", Toast.LENGTH_SHORT).show();
                   //test
                   Intent intent=new Intent(Input_Info_Activity.this,Input_Subscriptions_Activity.class);
                   startActivity(intent);
               }

            }
        });

    }
}