package com.example.myapplication;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {  // Make position final
        Item item = items.get(position);
        holder.taskView.setText(item.getTask());
        holder.rewardView.setText(item.getReward());
        holder.imageView.setImageResource(item.getImage());
        //holder.helmetImage2.setVisibility(item.isClaimed() ? View.VISIBLE : View.INVISIBLE);

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
        items.remove(position);  // Remove the item from the list
        notifyItemRemoved(position);  // Notify the adapter that the item has been removed
        notifyItemRangeChanged(position, items.size());  // Update the remaining items in the list
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


