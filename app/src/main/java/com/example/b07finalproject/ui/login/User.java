package com.example.b07finalproject.ui.login;

public abstract class User {
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
