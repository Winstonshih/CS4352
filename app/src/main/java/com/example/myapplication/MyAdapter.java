/*
  MyAdapter.java

  This adapter class populates a RecyclerView with `Item` objects, displaying tasks and associated rewards for users to interact with
  in the main game interface. Each item includes a "Claim" button, allowing users to complete tasks and receive rewards, which are
  tracked through SharedPreferences for persistence across sessions. The adapter implements a popup window for task completion
  with an option to "delete" items upon claiming, dynamically updating both the equipment status and inventory visibility based on
  completed tasks. Additionally, it notifies the relevant activity (main game or inventory page) to refresh UI elements as needed.

 */

package com.example.myapplication;
import android.content.SharedPreferences;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { //ignore this part if it gives you an error
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
    private void showPopup(View v, MyViewHolder holder, final int position) {
        View popUpView = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpWindow.showAsDropDown(v);
        Item item = items.get(position);

        Button deleteButton = popUpView.findViewById(R.id.yes);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setClaimed(true);
                removeItem(position);
                popUpWindow.dismiss();
                Toast.makeText(context, "You completed " + item.getTask() + " and got " + item.getReward() + ".", Toast.LENGTH_SHORT).show();

                // Update the armor based on the completed task
                updateArmor(item);

            }
        });

        Button close = popUpView.findViewById(R.id.no);
        close.setOnClickListener(a -> popUpWindow.dismiss());
    }

    private void updateArmor(Item item) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("tracker", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (item.getReward()) {
            case "Diamond helmet (+30 Protection)":
                editor.putInt("helmet", 2);
                break;
            case "Diamond Armor (+30 Protection)":
                editor.putInt("chest", 2);
                break;
            case "Diamond pants (+30 Protection)":
                editor.putInt("pants", 2);
                editor.putBoolean("person color", true);
                editor.putBoolean("person", true);
                editor.putBoolean("empty list", true);
                break;
        }
        if(item.getTask().isEmpty())
        {
            editor.putInt("sword", 2);
        }
        editor.apply();
        if (context instanceof main_game_page) {
            ((main_game_page) context).incrementCompletedTasks();
        }
        if (context instanceof main_game_page) {
            ((main_game_page) context).updateArmorUI();
        }
    }
    private void incrementTasksCompleted() {
        SharedPreferences sharedTracker = context.getSharedPreferences("tracker", Context.MODE_PRIVATE);
        int tasksCompleted = sharedTracker.getInt("tasksCompleted", 0);
        SharedPreferences.Editor editor = sharedTracker.edit();
        editor.putInt("tasksCompleted", tasksCompleted + 1);
        editor.apply();
    }
    @Override
    public int getItemCount () {
        return items.size();
    }
    public void updateItems(List<Item> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }
}


