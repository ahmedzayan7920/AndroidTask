package com.example.androidtask.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Calendar calender;

    public Alarm() {
    }

    public Alarm(int id) {
        this.id = id;
    }

    public Alarm(String name, Calendar calender) {
        this.name = name;
        this.calender = calender;
    }

    public Alarm(int id, String name, Calendar calender) {
        this.id = id;
        this.name = name;
        this.calender = calender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCalender() {
        return calender;
    }

    public void setCalender(Calendar calender) {
        this.calender = calender;
    }
}
