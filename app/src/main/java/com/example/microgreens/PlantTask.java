package com.example.microgreens;

public class PlantTask {
    private String name;
    private String note;

    private int time;

    public PlantTask() {
        name = "";
        note = "";
    }

    public PlantTask(String name, String note, int time) {
        this.name = name;
        this.note = note;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
