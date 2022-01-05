package com.example.androidtask.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Alarm.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AlarmDB extends RoomDatabase {

    public abstract AlarmDao getDao();

    public static AlarmDB dbConnection;

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    public static synchronized AlarmDB getDbConnection(Context c){

        if (dbConnection == null){
            dbConnection = Room.databaseBuilder(c.getApplicationContext(), AlarmDB.class, "AlarmDB")
                    .fallbackToDestructiveMigration()
                    .build();

            return dbConnection;
        }
        return dbConnection;
    }
}
