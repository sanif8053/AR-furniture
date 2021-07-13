package com.example.foodcenter.Model;

public class User {

    private String Name;
    private String PhoneNumber;
    private String Password;
    private String Email;


    public User(){

    }


    public User(String name, String phoneNumber, String password, String email) {
        Name = name;
        PhoneNumber = phoneNumber;
        Password = password;
        Email = email;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
