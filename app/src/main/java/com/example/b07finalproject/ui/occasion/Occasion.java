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
    private LocalDateTime dateTime;
    private String location;
    private String description;

    //EVENT class needs 'capacity' and 'attendees' fields

    public Occasion() {
    }

    public Occasion(String name, LocalDateTime dateTime, String location, String description) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public String getLocation() {
        return location;
    }






}
