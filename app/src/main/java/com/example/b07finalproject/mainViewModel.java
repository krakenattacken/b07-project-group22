package com.example.b07finalproject;

import androidx.lifecycle.ViewModel;

import com.example.b07finalproject.ui.login.User;

public class mainViewModel extends ViewModel {
    public User currentUser;
    public User getCurrentUser(){
        return currentUser;
    }
}
