package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items=items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.taskView.setText(items.get(position).getTask());
        holder.rewardView.setText(items.get(position).getReward());
        holder.imageView.setImageResource(items.get(position).getImage());
        Item item=items.get(position);
        holder.taskView.setText(item.getTask());
        holder.claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message
                Toast.makeText(context, "Clicked on " + item.getTask(), Toast.LENGTH_SHORT).show();
                showPopup(v);
            }
        });
    }
    private void showPopup(View v)
    {
        View popUpView=LayoutInflater.from(context).inflate(R.layout.dialog,null);
        PopupWindow popUpWindow= new PopupWindow(popUpView,ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpWindow.showAsDropDown(v);
        Button close=popUpView.findViewById(R.id.no);
        close.setOnClickListener(a -> popUpWindow.dismiss());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}


