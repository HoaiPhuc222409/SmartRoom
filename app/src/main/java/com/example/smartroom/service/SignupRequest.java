package com.example.smartroom.service;

public class SignupRequest {
    private String fullName;
    private String username;
    private String password;
    private String birthday;
    private String phone;
    private String email;


    public SignupRequest(String fullName, String username, String password, String birthday, String phone, String email) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }
}
