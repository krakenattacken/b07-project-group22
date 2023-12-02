package com.example.b07finalproject.ui.announcements;

import android.os.Build;

import com.example.b07finalproject.ui.occasion.Occasion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Announcement extends Occasion {

    public Announcement(String name, LocalDateTime dateTime, String location, String description) {
        super(name,dateTime,location,description);
    }

    //methods to be added as needed

    //hashcode of username and title (for unique ID)



    //to do when firebase db gets made (for now, just a tester)
    public static List<Announcement> createAnnouncementList(int numAnnoun) {
        List<Announcement> announcements = new ArrayList<Announcement>();
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        Announcement announ1 = new Announcement("Announ 1", now, "Location 1", "Description 1");
        Announcement announ2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            announ2 = new Announcement("Announ 2", now.plusDays(1), "Location 2", "Description 2");
        }
        Announcement announ3 = new Announcement("Announ 1", now, "Location 1", "Description 1");
        Announcement announ4 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            announ4 = new Announcement("Announ 2", now.plusDays(1), "Location 2", "Description 2");
        }
        Announcement announ5 = new Announcement("Announ 1", now, "Location 1", "Description 1");
        announcements.add(announ1);
        announcements.add(announ2);
        announcements.add(announ3);
        announcements.add(announ4);
        announcements.add(announ5);
        return announcements;
    }

}

