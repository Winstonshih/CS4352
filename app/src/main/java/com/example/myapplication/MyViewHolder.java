package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView taskView;
    TextView rewardView;
    ImageView imageView;
    Button claim;
    public MyViewHolder(View itemView) {
        super(itemView);
        taskView = itemView.findViewById(R.id.task);
        rewardView = itemView.findViewById(R.id.reward);
        imageView = itemView.findViewById(R.id.imageview);
        claim=itemView.findViewById(R.id.claim);
    }
}
