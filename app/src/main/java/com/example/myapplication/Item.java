package com.example.myapplication;

public class Item {
    int id;
    String task;
    String reward;
    int image;
    private boolean claimed;
    public Item(int id,String t, String r, int i, boolean c) {
        task=t;
        reward=r;
        image=i;
        claimed=c;
        this.id=id;
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
    public boolean isClaimed()
    {
        return claimed;
    }
    public void setClaimed(boolean c)
    {
        claimed=c;
    }

    public int getId() {
    return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
