package com.example.androidtask.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDao {
    @Insert
    void insert(Alarm p);

    @Query("select * from Alarm")
    List<Alarm> getAllAlarms();

}
