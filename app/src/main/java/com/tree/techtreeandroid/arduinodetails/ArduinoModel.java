package com.tree.techtreeandroid.arduinodetails;

public class ArduinoModel {
    String Title;
    String Details;

    public ArduinoModel(String title, String details) {
        Title = title;
        Details = details;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
