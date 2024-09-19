package com.charging.animation.mobile.battery.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Active {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Ads")
    public String ads;

    public Active(int id, String ads) {
        this.id = id;
        this.ads = ads;
    }

    public Active() {
    }
}
