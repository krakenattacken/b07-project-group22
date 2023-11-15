package com.example.b07finalproject.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SatisfactionViewModel extends ViewModel {
    private final MutableLiveData<Boolean> satisfied = new MutableLiveData<Boolean>();
    public void setSatisfied(Boolean check) {
        satisfied.setValue(check);
    }
    public LiveData<Boolean> getSatisfied() {
        return satisfied;
    }
}
