package com.tree.techtreeandroid.log_out.model;

public class User {

    private String User_name;
    private String User_email;
    private String User_phone;

    public User(String user_name, String user_email, String user_phone) {
        User_name = user_name;
        User_email = user_email;
        User_phone = user_phone;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getUser_email() {
        return User_email;
    }

    public void setUser_email(String user_email) {
        User_email = user_email;
    }

    public String getUser_phone() {
        return User_phone;
    }

    public void setUser_phone(String user_phone) {
        User_phone = user_phone;
    }
}
