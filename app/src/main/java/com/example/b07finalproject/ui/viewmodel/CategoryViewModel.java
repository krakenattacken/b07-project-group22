package com.example.b07finalproject.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<String>();
    public void setCategory(String category) {
        selectedCategory.setValue(category);
    }
    public LiveData<String> getSelectedCategory() {
        return selectedCategory;
    }
}
