package com.example.b07finalproject.ui.postannoun;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class PostAnnounViewModel extends ViewModel{
    private final MutableLiveData<String> mText;

    public PostAnnounViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is post announcements fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
