package com.example.b07finalproject.ui.events;

public class EventFeedback {
    private Event event;
    private String comment;
    private int rating;

    public EventFeedback() {

    }

    public EventFeedback(Event event, String comment, int rating) {
        this.event = event;
        this.comment = comment;
        this.rating = rating;
    }

    public Event getEvent() {
        return event;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public EventFeedback submitFeedback(EventFeedback eventFeedback) {
        return eventFeedback;
    }

}
