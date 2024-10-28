package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView taskView;
    TextView rewardView;
    ImageView imageView, pantsImage2, helmetImage2, armorImage2;
    Button claim;
    public MyViewHolder(View itemView) {
        super(itemView);
        taskView = itemView.findViewById(R.id.task);
        rewardView = itemView.findViewById(R.id.reward);
        imageView = itemView.findViewById(R.id.imageview);
        claim=itemView.findViewById(R.id.claim);
       // helmetImage2=itemView.findViewById(R.id.helmetImage2);
        //pantsImage2=itemView.findViewById(R.id.pantsImage2);
        //armorImage2=itemView.findViewById(R.id.armorImage2);
    }
}
