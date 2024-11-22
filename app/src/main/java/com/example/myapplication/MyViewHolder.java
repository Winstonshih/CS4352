/*
  MyViewHolder.java
 *
  This ViewHolder class is designed to hold and manage views for each item displayed in a RecyclerView within the app.
  It binds UI components, including TextViews for displaying task descriptions and rewards, an ImageView for item images,
  and a "Claim" button that users interact with to complete tasks. `MyViewHolder` helps organize the layout for each item,
  ensuring that data is displayed consistently and efficiently within the RecyclerView.

 */

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
