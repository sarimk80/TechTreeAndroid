package com.example.techtreeandroid.fragmentmodel;


public class ConstrollerModel {
    private String name;
    private String id;
    private String data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ConstrollerModel(String data, String name, String id) {
        this.name = name;
        this.id = id;
        this.data=data;
    }
}
