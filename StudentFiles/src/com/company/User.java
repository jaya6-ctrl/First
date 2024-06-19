package com.company;

public class User {
    private String username;
    private String passwd;
    private String bodyNumber;
    private String phoneNumber;

    public User() {
    }

    public User(String username, String passwd, String bodyNumber, String phoneNumber) {
        this.username = username;
        this.passwd = passwd;
        this.bodyNumber = bodyNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
