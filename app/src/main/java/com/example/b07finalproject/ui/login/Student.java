package com.example.b07finalproject.ui.login;

import com.example.b07finalproject.ui.events.Event;

import java.util.ArrayList;


public class Student extends User{
    ArrayList<Event> eventsToAttend;
    public Student(){
        super();
        eventsToAttend = new ArrayList<Event>();
    }

    public Student(String username, String password){
        super(username, password);
    }

    public Student(String username, String password, ArrayList<Event> eventsToAttend){
        super(username, password);
        this.eventsToAttend = eventsToAttend;
    }

    public ArrayList<Event> getEventsToAttend() {
        return eventsToAttend;
    }

}
