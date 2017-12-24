package com.calvin.model;

public class User {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private int id;

    public User(int id, String fullname, String email, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
