package com.app.shaalastic.Users;

public class UserData {
    private String name;
    private String email;
    private String roll_no;
    private String phno;
    private String gender;
    private String id;
    UserData(String name, String email, String roll_no, String phno, String gender, String id){
        this.name=name;
        this.email=email;
        this.roll_no=roll_no;
        this.phno=phno;
        this.gender=gender;
        this.id=id;


    }

    public String getName() {
        return name;
    }
    public String getemail() {
        return email;
    }
    public String getroll_no(){return  roll_no;}
    public String getPhno(){return  phno;}
    public String getGender(){return  gender;}
    public String getid(){return id;}
}
