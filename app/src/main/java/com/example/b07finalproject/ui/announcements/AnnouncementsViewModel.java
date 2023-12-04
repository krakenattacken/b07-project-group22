package com.example.b07finalproject.ui.announcements;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class AnnouncementsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnnouncementsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where students and admins can view announcements,\nonly admins will be able to see the button to post a new announcement");
    }

    public LiveData<String> getText() {
        return mText;
    }
}


/*
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementsViewModel extends ViewModel {
    //data for layout checking purposes; will be deleted
    MutableLiveData<List<Announcement>> announList;

    List<Announcement> x;
    Announcement e;

    public LiveData<List<Announcement>> getItemList() {
        if (announList == null) {
            announList = new MutableLiveData<>();
            x = e.createAnnouncementList(1);
            announList.setValue(x);
        }
        return announList;
    }
}

 */