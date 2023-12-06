package com.example.b07finalproject.ui.login;

import java.io.Serializable;

public abstract class User implements Serializable {
    String username;
    String password;
    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString(){
        return username;
    }
}
