package com.example.b07finalproject;


import java.util.ArrayList;
import java.util.List;

//please implement this interface in any class that needs to get data from firebase
public interface DBDependent {
    /*
    the "items" is every object under a certain node in the database.
    Ex. if you call getAllFromDB with path events, items will be every event in the database
    You should probably cast items into whatever class you're working with
    This method should update the UI using items
     */
    public abstract void loadDataFromDB(List<Object> items);
    /*
        this function is called if there's an error with the database reason can be:
        - "Something went wrong" if the database just fails to read (I don't know how that happens, but just in case)
        - "ID is taken" if you try to add an already existing item to the database
        - "Path is empty" if the path given in getAllFromDB does not exist
         */
    public abstract void onDBFail(String reason);
}
