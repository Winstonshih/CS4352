package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TaskHistoryViewModel extends AndroidViewModel{
    private MutableLiveData<List<History>> h;
    private SharedPreferences sharedPreferences;
    public TaskHistoryViewModel(Application a)
    {
        super(a);
        sharedPreferences = a.getSharedPreferences("GamePagePrefs", Application.MODE_PRIVATE);
    }
    public LiveData<List<History>> getHistory() {
        if (h == null) {
            h = new MutableLiveData<>();
            loadHistory();
        }
        return h;
    }
    private void loadHistory()
    {
        List<History> historyList = new ArrayList<>();

        // Load your initial list here
        historyList.add(new History(1, "Close a subscription", "Diamond helmet (+30 Protection)", R.drawable.upgradedhelmet));
        historyList.add(new History(2, "Make a Savings Account", "Diamond Armor (+30 Protection)", R.drawable.upgradedarmor));
        historyList.add(new History(3, "Add $20 to Savings Account", "Diamond pants (+30 Protection)", R.drawable.upgradedpants));
        historyList.add(new History(4, "Save $20 for a Rainy Day Fund", "Jade helmet (+50 Protection)", R.drawable.greenhelmet));
        historyList.add(new History(5, "Make minimum credit card payment before deadline", "Jade Armor (+50 Protection)", R.drawable.greenarmor));
        historyList.add(new History(6, "Redeem a Cash Back offer at a store", "Jade pants (+50 Protection)", R.drawable.greenpants));
        historyList.add(new History(7, "Automate monthly savings transfers", "Gold helmet (+70 Protection)", R.drawable.goldhelmet));
        // Add completed tasks
        List<History> filteredList = new ArrayList<>();
        for (History history : historyList) {
            if (sharedPreferences.getBoolean("deleted_" + history.getId(), false)) {
                filteredList.add(history);
            }
        }

        h.setValue(filteredList);
    }
}
