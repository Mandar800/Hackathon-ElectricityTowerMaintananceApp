package com.app.shaalastic.Users;

public class UserData {
    private String name;
    private String email;
    private String role;
    private String phno;
    private String gender;
    private String uid;
    UserData(String name, String email, String role, String phno, String gender, String uid){
        this.name=name;
        this.email=email;
        this.role=role;
        this.phno=phno;
        this.gender=gender;
        this.uid=uid;


    }

    public String getName() {
        return name;
    }
    public String getemail() {
        return email;
    }
    public String getrole(){return  role;}
    public String getPhno(){return  phno;}
    public String getGender(){return  gender;}
    public String getUid(){return uid;}
}
