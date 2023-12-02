package com.example.b07finalproject.ui.events;

public class EventFeedback {
    Event event;
    String comment;
    int rating;

    public EventFeedback(Event event, String comment, int rating) {
        this.event = event;
        this.comment = comment;
        this.rating = rating;
    }

    public EventFeedback submitFeedback(EventFeedback eventFeedback) {
        return eventFeedback;
    }

}
