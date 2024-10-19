package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class main_game_page extends AppCompatActivity{
    ImageButton personImage;
    private Button claim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_game_page);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Close a subscription.", "Diamond helmet (+10 Protection)", R.drawable.upgradedhelmet));
        items.add(new Item("Make a Savings Account.", "Diamond Armor (+10 Protection)", R.drawable.upgradedarmor));
        items.add(new Item("Add $20 to Savings Account.", "Diamond pants (+10 Protection)", R.drawable.upgradedpants));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));




        //Ruben Part when they click on the the image
        personImage = (ImageButton) findViewById(R.id.personImage);
        personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to the stats page
                Intent intent = new Intent(main_game_page.this, main_character_stats_page.class);
                startActivity(intent);
            }
        });
    }
}