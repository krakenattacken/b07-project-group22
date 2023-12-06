package com.example.b07finalproject.ui.announcements;

import android.os.Build;

import com.example.b07finalproject.ui.occasion.Occasion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Announcement extends Occasion implements Serializable {


    public Announcement() {}

    public Announcement(String name, String time, String location, String description) {
        super(name,time,location,description);
    }

    //methods to be added as needed

    //hashcode of username and title (for unique ID)



    //to do when firebase db gets made (for now, just a tester)
    public static List<Announcement> createAnnouncementList(int numAnnoun) {
        List<Announcement> announcements = new ArrayList<Announcement>();
        //LocalDateTime now = null;
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        Announcement announ1 = new Announcement("Announ 1", "manually entered time", "Location 1", "Description 1");
        Announcement announ2 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            announ2 = new Announcement("Announ 2", "manually entered time", "Location 2", "Description 2");
        }
        Announcement announ3 = new Announcement("Announ 1", "manually entered time", "Location 1", "Description 1");
        Announcement announ4 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            announ4 = new Announcement("Announ 2", "manually entered time", "Location 2", "Description 2");
        }
        Announcement announ5 = new Announcement("Announ 1", "manually entered time", "Location 1", "Description 1");
        announcements.add(announ1);
        announcements.add(announ2);
        announcements.add(announ3);
        announcements.add(announ4);
        announcements.add(announ5);

         */
        return announcements;
    }

}