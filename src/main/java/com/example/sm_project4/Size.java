package com.example.sm_project4;

public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String enumSize;

    Size(String size){
        this.enumSize=size;
    }
    public String getString(){
        return enumSize;
    }
}
