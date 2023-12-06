package com.example.b07finalproject.ui.complaint;

public class Complaint {
    String heading;
    String body;
    public Complaint(){}

    public Complaint(String heading, String body) {
        this.heading = heading;
        this.body = body;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getBody(){
        return this.body;
    }

    public String generateUniqueID() {
        int intID = (3*this.heading.hashCode() + 5*this.body.hashCode());
        return Integer.toString(intID);
    }
}
