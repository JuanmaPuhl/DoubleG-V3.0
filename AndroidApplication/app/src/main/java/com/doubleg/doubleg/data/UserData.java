package com.doubleg.doubleg.data;

public class UserData {
    private String name,lastname,username,email;

    public UserData(String name, String lastname, String username, String email) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
