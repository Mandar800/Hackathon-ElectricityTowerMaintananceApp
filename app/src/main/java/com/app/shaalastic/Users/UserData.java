package com.app.shaalastic.Users;

public class UserData {
    private String name,rollno;
    UserData(String name, String rollno){
        this.name=name;
        this.rollno=rollno;
    }

    public String getName() {
        return name;
    }

    public String getRollno() {
        return rollno;
    }
}
