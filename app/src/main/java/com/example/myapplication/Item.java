package com.example.myapplication;

public class Item {
    String task;
    String reward;
    int image;
    public Item(String t, String r, int i) {
        task=t;
        reward=r;
        image=i;
    }

    public String getTask() {
        return task;
    }

    public String getReward() {
        return reward;
    }

    public int getImage() {
        return image;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
