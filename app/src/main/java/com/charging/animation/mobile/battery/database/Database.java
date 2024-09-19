package com.charging.animation.mobile.battery.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.charging.animation.mobile.battery.api.Results;


@androidx.room.Database(entities = {Results.class, Active.class}, version = 3)
public abstract class Database extends RoomDatabase{
    private static Database database;

    public static final String DATABASE_NAME = "Room-database";

    public abstract ResultsDao resultsDao();
    public abstract ActiveDao activeDao();

    public static Database getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
