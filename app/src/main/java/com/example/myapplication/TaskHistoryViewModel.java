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
        h.setValue(historyList);
    }
}
