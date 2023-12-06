package com.example.b07finalproject;

import androidx.lifecycle.ViewModel;

import com.example.b07finalproject.ui.login.User;

public class mainViewModel extends ViewModel {
    public User currentUser;
    public mainDBModel model;

    public mainViewModel(){
        super();
        model = new mainDBModel();
    }
    public User getCurrentUser(){
        return currentUser;
    }

    public void updateUser(User user){
        model.updateUser(user);
    }

    /*
    -path should be what is being taken ex events, feedback etc
    -presenter is just the object that called this method, just use this
    -itemClass is the class of the object you want ex Event.class
    Calling this method will then call loadDataFromDB
     */
    public void getAllFromDB(String path, DBDependent presenter, Class itemClass){
        model.getAllFromDB(path, presenter, itemClass);
    }

    public void addToDB(Object item, String path, String id, DBDependent presenter){
        model.tryToAdd(item, path, id, presenter);
        if ("events".equals(path)){
            model.add("events","notifications", "notifs");
        }
        else if ("announcements".equals(path) || "announcement".equals(path)){
            model.add("announcements","notifications", "notifs");
        }
    }

    public void removeFromDB(String path, String id){
        model.remove(path, id);
    }

    public void startNotif(MainActivity main){
        model.add("","notifications", "notifs");
        model.startNotif(main);
    }
}


