package com.example.myapplication;

public class User {
    public String email;
    public String password;
    public String name;
    public String address;

    public User(String name, String email, String password, String address)
    {
        this.name=name;
        this.email=email;
        this.password=password;
        this.address=address;
    }
}

