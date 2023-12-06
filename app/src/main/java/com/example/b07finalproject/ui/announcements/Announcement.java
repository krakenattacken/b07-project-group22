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

        return announcements;
    }

}
