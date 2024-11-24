package com.example.myapplication;

public class History {
    int id;
    String task;
    String reward;
    int image;
    public History(int id, String t, String r, int i)
    {
        this.id=id;
        task=t;
        reward=r;
        image=i;
    }
    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getTask()
    {
        return task;
    }
    public void setTask(String task)
    {
        this.task=task;
    }
    public String getReward()
    {
        return reward;
    }
    public void setReward(String r)
    {
        reward=r;
    }
    public int getImage()
    {
        return image;
    }
    public void setImage(int i)
    {
        image=i;
    }
}
