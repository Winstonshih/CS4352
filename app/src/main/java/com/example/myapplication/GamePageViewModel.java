/*
  GamePageViewModel.java

  This ViewModel manages the list of financial goals or tasks in the app, using LiveData to track
  changes and update the UI automatically. It initializes a set of default financial goals with associated
  rewards, such as "Diamond Helmet" or "Diamond Armor," and uses SharedPreferences to store
  and filter out any tasks marked as deleted by the user. This ensures that deleted tasks are persisted
  across app sessions and do not reappear in the task list. The ViewModel’s structure supports smooth
  UI interaction by providing the app with a readily observable list of active tasks.

*/
package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class GamePageViewModel extends AndroidViewModel {
    private MutableLiveData<List<Item>> items;
    private MutableLiveData<Integer> progress;
    private SharedPreferences sharedPreferences;

    public GamePageViewModel(Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("GamePagePrefs", Application.MODE_PRIVATE);
        items = new MutableLiveData<>();
        progress = new MutableLiveData<>(0); // Initialize progress to 0
        loadItems();
    }

    public LiveData<List<Item>> getItems() {
        return items;
    }

    public LiveData<Integer> getProgress() {
        return progress;
    }

    private void loadItems() {
        List<Item> itemList = new ArrayList<>();

        // Initialize your financial goal tasks here
        itemList.add(new Item(1, "Close a subscription", "Diamond helmet (+10 Protection)", R.drawable.upgradedhelmet, false));
        itemList.add(new Item(2, "Make a Savings Account", "Diamond Armor (+10 Protection)", R.drawable.upgradedarmor, false));
        itemList.add(new Item(3, "Add $20 to Savings Account", "Diamond pants (+10 Protection)", R.drawable.upgradedpants, false));

        // Filter out deleted items
        List<Item> filteredList = new ArrayList<>();
        int completedTasks = 0;
        for (Item item : itemList) {
            if (!sharedPreferences.getBoolean("deleted_" + item.getId(), false)) {
                filteredList.add(item);
            } else {
                completedTasks++;
            }
        }

        items.setValue(filteredList);
        updateProgress(completedTasks, itemList.size()); // Update progress on load
    }

    public void markTaskAsCompleted(int taskId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("deleted_" + taskId, true);  // Mark task as completed in SharedPreferences
        editor.apply();

        // Reload items and update progress
        loadItems();
    }

    private void updateProgress(int completedTasks, int totalTasks) {
        int progressValue = (int) (((double) completedTasks / totalTasks) * 100);
        progress.setValue(progressValue);
    }
}
