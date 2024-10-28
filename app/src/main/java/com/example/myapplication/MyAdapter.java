package com.example.myapplication;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<Item> items;
    SharedPreferences sharedTracker;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) { //ignore this part if it gives you an error
        Item item = items.get(position);
        holder.taskView.setText(item.getTask());
        holder.rewardView.setText(item.getReward());
        holder.imageView.setImageResource(item.getImage());

        // Only show the claim button for the first item
        if (position == 0) {
            holder.claim.setVisibility(View.VISIBLE);
        } else {
            holder.claim.setVisibility(View.GONE);
        }



    // Claim button logic
        holder.claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message
                Toast.makeText(context, "You need to " + item.getTask() + " to get " + item.getReward() + ".", Toast.LENGTH_SHORT).show();
                showPopup(v, holder, position);  // Pass the final position to the popup
            }
        });
    }

    // Method to remove item from the list
    public void removeItem(int position) {
        // Save the deleted item's ID or position to SharedPreferences
        Item deletedItem = items.get(position);
        SharedPreferences sharedPreferences = context.getSharedPreferences("GamePagePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("deleted_" + deletedItem.getId(), true);  // Use a unique ID for each item
        editor.apply();

        // Remove the item from the list
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }



    // Popup window for claim with delete functionality
    private void showPopup(View v, MyViewHolder holder, final int position) {  // Accept final position
        View popUpView = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpWindow.showAsDropDown(v);
        Item item=items.get(position);
        // Button "Yes" to delete the item
        Button deleteButton = popUpView.findViewById(R.id.yes);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setClaimed(true);
                //holder.imageView.setVisibility(View.VISIBLE);
                removeItem(position);  // Delete the item
                popUpWindow.dismiss();  // Close the popup
                Toast.makeText(context, "You completed " +item.getTask()+" and got "+item.getReward()+".", Toast.LENGTH_SHORT).show();
            }
        });

        // Close button
        Button close = popUpView.findViewById(R.id.no);
        close.setOnClickListener(a -> popUpWindow.dismiss());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


