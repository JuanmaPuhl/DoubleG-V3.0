package com.doubleg.doubleg.data;

public class UserData {
    private int id;
    private String name,lastname,username,email,birthday;

    public UserData(int id, String name, String lastname, String username, String email, String birthday) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
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

    public String getBirthday() {
        return birthday;
    }
}
