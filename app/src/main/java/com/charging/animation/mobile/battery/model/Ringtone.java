package com.charging.animation.mobile.battery.model;

public class Ringtone {
    public String name;
    public String uri;
    public boolean check;
    public String time;

    public Ringtone(String name, String uri,  String time) {
        this.name = name;
        this.uri = uri;
        check = false;
        this.time = time;
    }
}
