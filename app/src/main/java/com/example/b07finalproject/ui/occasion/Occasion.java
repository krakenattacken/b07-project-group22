package com.example.b07finalproject.ui.occasion;


import android.os.Build;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//ADD Notification functions here for both events and announcements
public class Occasion {
    private String name;
    private String time;
    private String location;
    private String description;

    //EVENT class needs 'capacity' and 'attendees' fields

    public Occasion() {
    }

    public Occasion(String name, String time, String location, String description) {
        this.name = name;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }


    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() { return description; }

    public LocalDateTime toDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

        return localDateTime;
    }

    public static String toTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    @Override   //equals method should be added and modified in Announcements and Events
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Occasion)){
            return false;
        }

        Occasion other = (Occasion) obj;

        return (this.toString().equals(other.toString()));
    }

    @Override
    public String toString() {
        String newFormat = this.getName() + "," + this.getTime() + "," + this.getLocation()
                + "," + this.getDescription();
        return newFormat;
    }




}
