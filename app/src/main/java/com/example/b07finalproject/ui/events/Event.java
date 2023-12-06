package com.example.b07finalproject.ui.events;

import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;
import com.example.b07finalproject.ui.occasion.Occasion;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//note: will likely make a superclass for event and announcement...

public class Event extends Occasion implements Serializable {
    private int attendees;
    private int capacity;
    private ArrayList<String> students;

    public Event() {
        this.students = new ArrayList<String>();
    }

    public Event(String name, String time,
                 String location, String description, int capacity) {
        super(name, time, location, description);
        this.capacity = capacity;
        this.students = new ArrayList<String>();
    }

    //methods to be added as needed


    public int getAttendees() { return attendees;}

    public int getCapacity() { return capacity;}
    public ArrayList<String> getStudents() {
        return students;
    }

    public boolean hasSpace() {
        return attendees < capacity;
    }

    public void attendeesIncreased(Student student) {
        attendees++;
        students.add(student.getUsername());
    }

    public void attendeesDecreased() {
        attendees--;
    }

    public boolean participating(Student student) {
        return students.contains(student.getUsername());
    }

    //to do when firebase db gets made (for now, just a tester)
    public static List<Event> createEventList(int numEvents) {
        List<Event> events = new ArrayList<Event>();
        return events;
    }

}
