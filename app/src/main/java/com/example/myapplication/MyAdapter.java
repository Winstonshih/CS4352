/*
  MyAdapter.java

  This adapter class populates a RecyclerView with `Item` objects, displaying tasks and associated rewards for users to interact with
  in the main game interface. Each item includes a "Claim" button, allowing users to complete tasks and receive rewards, which are
  tracked through SharedPreferences for persistence across sessions. The adapter implements a popup window for task completion
  with an option to "delete" items upon claiming, dynamically updating both the equipment status and inventory visibility based on
  completed tasks. Additionally, it notifies the relevant activity (main game or inventory page) to refresh UI elements as needed.

 */

package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW = 0;
    private static final int TASK_HISTORY = 1;

    private Context context;
    private List<Object> items; // This list can contain both Item and History objects

    public MyAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Item) {
            return ITEM_VIEW;
        } else if (items.get(position) instanceof History) {
            return TASK_HISTORY;
        }
        return -1; // Invalid type
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TASK_HISTORY) {
            View view = LayoutInflater.from(context).inflate(R.layout.task_history, parent, false);
            return new HistoryViewHolder(view);
        }
        throw new IllegalArgumentException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            bindItemViewHolder((ItemViewHolder) holder, (Item) items.get(position));
        } else if (holder instanceof HistoryViewHolder) {
            bindHistoryViewHolder((HistoryViewHolder) holder, (History) items.get(position));
        }
    }

    private void bindItemViewHolder(ItemViewHolder holder, Item item) {
        holder.taskView.setText(item.getTask());
        holder.rewardView.setText(item.getReward());
        holder.imageView.setImageResource(item.getImage());

        // Only show the claim button for the first item
        holder.claim.setVisibility(holder.getAdapterPosition() == 0 ? View.VISIBLE : View.GONE);

        holder.claim.setOnClickListener(v -> {
            Toast.makeText(context, "You need to " + item.getTask() + " to get " + item.getReward() + ".", Toast.LENGTH_SHORT).show();
            showPopup(v, item);
        });
    }

    private void bindHistoryViewHolder(HistoryViewHolder holder, History history) {
        holder.taskView.setText(history.getTask());
        holder.rewardView.setText(history.getReward());
        holder.imageView.setImageResource(history.getImage());
    }

    private void showPopup(View v, Item item) {
        View popUpView = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        PopupWindow popUpWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpWindow.showAsDropDown(v);

        Button deleteButton = popUpView.findViewById(R.id.yes);
        deleteButton.setOnClickListener(view -> {
            item.setClaimed(true);
            removeItem(item);
            popUpWindow.dismiss();
            Toast.makeText(context, "You completed " + item.getTask() + " and got " + item.getReward() + ".", Toast.LENGTH_SHORT).show();
            updateArmor(item);
        });

        Button closeButton = popUpView.findViewById(R.id.no);
        closeButton.setOnClickListener(view -> popUpWindow.dismiss());
    }

    private void removeItem(Item item) {
        int position = items.indexOf(item);

        SharedPreferences sharedPreferences = context.getSharedPreferences("GamePagePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("deleted_" + item.getId(), true); // Use a unique ID for each item
        editor.apply();

        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    private void updateArmor(Item item) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("tracker", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (item.getReward()) {
            case "Diamond helmet (+30 Protection)":
                editor.putInt("helmet", 2);
                editor.putBoolean("task_1_completed", true);
                break;
            case "Diamond Armor (+30 Protection)":
                editor.putInt("chest", 2);
                editor.putBoolean("task_2_completed", true);
                break;
            case "Diamond pants (+30 Protection)":
                editor.putInt("pants", 2);
                editor.putBoolean("task_3_completed", true);
//                editor.putBoolean("person color", true);
//                editor.putBoolean("person", true);
                break;
            case "Jade helmet (+50 Protection)":
                editor.putInt("helmet", 3);
                editor.putBoolean("task_4_completed", true);
                break;
            case "Jade Armor (+50 Protection)":
                editor.putInt("chest", 3);
                editor.putBoolean("task_5_completed", true);
                break;
            case "Jade pants (+50 Protection)":
                editor.putInt("pants", 3);
                editor.putBoolean("task_6_completed", true);
                break;
            case "Gold helmet (+70 Protection)":
                editor.putInt("helmet", 4);
                editor.putBoolean("task_7_completed", true);
                editor.putBoolean("empty list", true);
                break;
            default:
                break;
        }

        if (item.getTask().isEmpty()) {
            editor.putInt("sword", 2);
        }

        editor.apply();

        if (context instanceof main_game_page) {
            ((main_game_page) context).incrementCompletedTasks();
            ((main_game_page) context).updateArmorUI();
        }
        if(context instanceof TaskHistory)
        {
            ((TaskHistory) context).updateArmorUI();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void updateItems(List<Object> newItems) {
        this.items.clear(); // Clear current list
        this.items.addAll(newItems); // Add all new items
        notifyDataSetChanged(); // Notify adapter about data changes
    }
    public void updateHistory(List<Object> newHistory)
    {
        this.items.clear();
        this.items.addAll(newHistory);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView taskView, rewardView;
        ImageView imageView;
        Button claim;

        ItemViewHolder(View itemView) {
            super(itemView);
            taskView = itemView.findViewById(R.id.task);
            rewardView = itemView.findViewById(R.id.reward);
            imageView = itemView.findViewById(R.id.imageview);
            claim = itemView.findViewById(R.id.claim);
        }
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView taskView, rewardView;
        ImageView imageView;

        HistoryViewHolder(View itemView) {
            super(itemView);
            taskView = itemView.findViewById(R.id.task);
            rewardView = itemView.findViewById(R.id.reward);
            imageView = itemView.findViewById(R.id.imageview); // Ensure this ID matches your layout
        }
    }
}

