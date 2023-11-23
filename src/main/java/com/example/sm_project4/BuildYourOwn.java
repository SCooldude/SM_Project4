package com.example.sm_project4;

public class BuildYourOwn extends Pizza{

    @Override
    public double price() {
        return 8.99;
    }

    @Override
    public String pizzaType() {
        return "BuildYourOwn";
    }
}
