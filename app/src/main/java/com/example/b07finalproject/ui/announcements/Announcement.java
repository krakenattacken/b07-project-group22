package com.example.b07finalproject.ui.announcements;
/*
import android.os.Build;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Announcement {
    private String name;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    //private int attendees;
    //private int capacity;

    public Announcement() {
    }
    public Announcement(String name, LocalDateTime dateTime, String location, String description) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
    }

    //methods to be added as needed

    public String getName() {
        return name;
    }

    public String getTime() {
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return dateTime.format(formatter);
        }
        return null;
    }

    public String getLocation() {
        return location;
    }

    /*  TO BE DELETED
    public boolean isFull() {
        return attendees == capacity;
    }
     */

/*
    //to do when firebase db gets made (for now, just a tester)
    public static List<Announcement> createAnnouncementList(int numAnnoun) {
        List<Announcement> announcements = new ArrayList<Announcement>();
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        Announcement announ1 = new Announcement("Event 1", now, "Location 1", "Description 1");
        Announcement announ2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            announ2 = new Announcement("Event 2", now.plusDays(1), "Location 2", "Description 2");
        }
        Announcement announ3 = new Announcement("Event 1", now, "Location 1", "Description 1");
        Announcement announ4 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            announ4 = new Announcement("Event 2", now.plusDays(1), "Location 2", "Description 2");
        }
        Announcement announ5 = new Announcement("Event 1", now, "Location 1", "Description 1");
        announcements.add(announ1);
        announcements.add(announ2);
        announcements.add(announ3);
        announcements.add(announ4);
        announcements.add(announ5);
        return announcements;
    }

}

*/