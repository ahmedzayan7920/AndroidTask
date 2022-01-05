package com.example.androidtask.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Calendar;

public class Converters {

    @TypeConverter
    public String fromCalendarToString(Calendar c){

        Gson g = new Gson();
        String s = g.toJson(c);

        return s;
    }

    @TypeConverter
    public Calendar fromStringToCalendar(String s){

        return new Gson().fromJson(s, Calendar.class);
    }
}
