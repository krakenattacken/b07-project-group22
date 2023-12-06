package com.example.b07finalproject.ui.announcements;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

