package com.charging.animation.mobile.battery.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ActiveDao {
    @Insert
    void insert(Active active);

    @Update
    void update(Active active);

    @Delete
    void delete(Active active);

    @Query("SELECT * FROM Active WHERE ads LIKE :link")
    boolean getByLink(String link);
}
