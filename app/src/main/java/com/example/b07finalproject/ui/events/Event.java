package com.example.b07finalproject.ui.events;

import com.example.b07finalproject.ui.login.Student;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//note: will likely make a superclass for event and announcement...
public class Event implements Serializable {
    private String name;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private HashSet<Student> students;
    private int attendees;
    private int capacity;

    public Event() {
    }
    public Event(String name, LocalDateTime dateTime,
                 String location, String description, int capacity) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
    }

    //methods to be added as needed

    public String getName() {
        return name;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() { return description;}

    public int getAttendees() { return attendees;}

    public int getCapacity() { return capacity;}

    public boolean hasSpace() {
        return attendees < capacity;
    }

    public void attendeesIncreased(Student student) {
        attendees++;
        students.add(student);
    }

    public void attendeesDecreased(Student student) {
        attendees--;
        students.remove(student);
    }

    //to do when firebase db gets made (for now, just a tester)
    public static List<Event> createEventList(int numEvents) {
        List<Event> events = new ArrayList<Event>();
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event("Event 1", now, "Location 1",
                "Description 1", 150);
        Event event2 = new Event("Event 2", now.plusDays(1), "Location 2",
                "Description 2" , 70);
        Event event3 = new Event("Event 1", now, "Location 1",
                "Description 1", 150);
        Event event4 = new Event("Event 2", now.plusDays(1), "Location 2",
                "Description 2", 70);
        Event event5 = new Event("Event 1", now, "Location 1",
                "Description 1", 150);
        Event event6 = new Event("Event 2", now.plusDays(1), "Location 2",
                "Description 2", 70);
        Event event7 = new Event("Event 1", now, "Location 1",
                "Description 1", 150);
        Event event8 = new Event("Event 2", now.plusDays(1), "Location 2",
                "Description 2", 70);
        Event event9 = new Event("Event 1", now, "Location 1",
                "Description 1", 150);
        Event event10 = new Event("Event 2", now.plusDays(1), "Location 2",
                "Description 2", 70);
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);
        events.add(event9);
        events.add(event10);
        return events;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Event other = (Event) obj;

        return (name.equals(other.getName()) &&
                description.equals(other.description) && location.equals(other.getLocation()));
    }


}
