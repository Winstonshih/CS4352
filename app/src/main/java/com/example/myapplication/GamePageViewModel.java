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
    private SharedPreferences sharedPreferences;

    public GamePageViewModel(Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("GamePagePrefs", Application.MODE_PRIVATE);
    }

    public LiveData<List<Item>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
            loadItems();
        }
        return items;
    }

    private void loadItems() {
        List<Item> itemList = new ArrayList<>();

        // Load your initial list here
        itemList.add(new Item(1, "Close a subscription", "Diamond helmet (+30 Protection)", R.drawable.upgradedhelmet, false));
        itemList.add(new Item(2, "Make a Savings Account", "Diamond Armor (+30 Protection)", R.drawable.upgradedarmor, false));
        itemList.add(new Item(3, "Add $20 to Savings Account", "Diamond pants (+30 Protection)", R.drawable.upgradedpants, false));
        itemList.add(new Item(4, "Save $20 for a Rainy Day Fund", "Jade helmet (+50 Protection)", R.drawable.greenhelmet, false));
        itemList.add(new Item(5, "Make minimum credit card payment before deadline", "Jade Armor (+50 Protection)", R.drawable.greenarmor, false));
        itemList.add(new Item(6, "Redeem a Cash Back offer at a store", "Jade pants (+50 Protection)", R.drawable.greenpants, false));
        itemList.add(new Item(7, "Automate monthly savings transfers", "Gold helmet (+70 Protection)", R.drawable.goldhelmet, false));
        // Filter out deleted items
        List<Item> filteredList = new ArrayList<>();
        for (Item item : itemList) {
            if (!sharedPreferences.getBoolean("deleted_" + item.getId(), false)) {
                filteredList.add(item);
            }
        }

        items.setValue(filteredList);
    }
}
