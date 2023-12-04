package com.example.b07finalproject.ui.complaint;

public class Complaint {
    String heading;
    String body;
    public Complaint(){}

    public Complaint(String heading, String body) {
        this.heading = heading;
        this.body = body;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getBody(){
        return this.body;
    }
}
