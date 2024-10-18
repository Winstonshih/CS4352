package com.example.myapplication;
import android.view.View;
import androidx.annotation.NonNull;
import android.widget.ImageView;
import android.media.Image;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView taskView, rewardView;

    public MyViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageview);
        taskView=itemView.findViewById(R.id.task);
        rewardView=itemView.findViewById(R.id.reward);
    }
}
