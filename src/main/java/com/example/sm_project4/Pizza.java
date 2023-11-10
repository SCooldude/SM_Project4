package com.example.sm_project4;
import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList <Topping> toppings; //Topping is an enum class
    protected Size size; //Size is an enum class
    protected Sauce sauce; //Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    public abstract double price(); //polymorphism
}
