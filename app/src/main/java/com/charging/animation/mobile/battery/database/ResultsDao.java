package com.charging.animation.mobile.battery.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.charging.animation.mobile.battery.api.Results;

import java.util.List;


@Dao
public interface ResultsDao {
    @Insert
    void insert(Results results);

    @Update
    void update(Results results);

    @Delete
    void delete(Results results);

    @Query("SELECT * FROM results WHERE folder LIKE :folder")
    List<Results> getAll(String folder);

    @Query("SELECT * FROM results WHERE  type=:type ORDER BY id DESC")
    List<Results> getAll(int type);

    @Query("SELECT * FROM results")
    List<Results> getAll();

}