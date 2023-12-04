package com.example.b07finalproject.ui.login;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable {
    String username;
    public User(){

    }

    public User(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
    @Override
    public String toString(){
        return username;
    }
}
