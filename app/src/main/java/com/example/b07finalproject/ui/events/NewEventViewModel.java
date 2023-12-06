package com.example.b07finalproject.ui.events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NewEventViewModel extends ViewModel {
    //data for layout checking purposes; will be deleted
    MutableLiveData<List<Event>> eventsList;

    List<Event> x;
    Event e;

    public LiveData<List<Event>> getItemList() {
        if (eventsList == null) {
            eventsList = new MutableLiveData<>();
            x = e.createEventList(1);
            eventsList.setValue(x);
        }
        return eventsList;
    }
}