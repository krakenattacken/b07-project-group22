package com.example.b07finalproject.ui.login;

public class Student extends User{
    public Student(){
        super();
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
