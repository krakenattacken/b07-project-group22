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
            if (eventFeedback.getEvent().equals(event)) {
                count ++;
            }
        }
        return count;
    }
    public double getAverage(Event event) {
        int count = 0;
        double sum = 0;

        for (EventFeedback eventFeedback : eventFeedbacks) {
            if (eventFeedback.getEvent().equals(event)) {
                sum += eventFeedback.getRating();
                count ++;
            }
        }
        return (sum / (double) count);
    }

    public String[] getComments(Event event) {
        ArrayList<String> commentsArray = new ArrayList<>();
        String[] comments;
        for (EventFeedback eventFeedback : eventFeedbacks) {
            if (eventFeedback.getEvent().equals(event)) {
                commentsArray.add(eventFeedback.getComment());
            }
        }

        return commentsArray.toArray(new String[0]);
    }
}