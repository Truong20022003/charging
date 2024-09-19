package com.charging.animation.mobile.battery.api;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Results  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "link")
    public String link;
    @ColumnInfo(name = "type")
    public int type;
    @ColumnInfo(name = "folder")
    public String folder;
    @Ignore
    public String name;
    @Ignore
    public boolean free;


    public Results(int id, String link, int type, String folder) {
        this.id = id;
        this.link = link;
        this.type = type;
        this.folder = folder;
    }

    public Results(int id, String link, int type, String folder,  boolean free) {
        this.id = id;
        this.link = link;
        this.type = type;
        this.folder = folder;
        this.free = free;
    }
}
