package com.example.b07finalproject.ui.events;

import java.util.ArrayList;

public class EventStats {
    EventFeedback[] eventFeedbacks;

    public EventStats(EventFeedback[] eventFeedbacks) {
        this.eventFeedbacks = eventFeedbacks;
    }

    public int getCount(Event event) {
        int count = 0;

        for (EventFeedback eventFeedback : eventFeedbacks) {
            if (eventFeedback.event.equals(event)) {
                count ++;
            }
        }
        return count;
    }
    public double getAverage(Event event) {
        int count = 0;
        double sum = 0;

        for (EventFeedback eventFeedback : eventFeedbacks) {
            if (eventFeedback.event.equals(event)) {
                sum += eventFeedback.rating;
                count ++;
            }
        }
        return (sum / (double) count);
    }

    public String[] getComments(Event event) {
        ArrayList<String> commentsArray = new ArrayList<>();
        String[] comments;
        for (EventFeedback eventFeedback : eventFeedbacks) {
            if (eventFeedback.event.equals(event)) {
                commentsArray.add(eventFeedback.comment);
            }
        }
        comments = new String[commentsArray.size()];

        for (int i = 0; i < comments.length; i++) {
            comments[i] = commentsArray.get(i);
        }

        return comments;
    }
}