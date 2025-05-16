package com.example.microgreens;

import java.util.ArrayList;

public class PlantModel {
    private String date;
    private String name;

    private ArrayList<PlantTask> tasks;

    public PlantModel() {
        tasks = new ArrayList<PlantTask>();

        name = "";
    }

    public PlantModel(String date, String name, ArrayList<PlantTask> tasks) {
        this.date = date;
        this.name = name;
        this.tasks = tasks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PlantTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<PlantTask> tasks) {
        this.tasks = tasks;
    }
}
