package com.example.sm_project4;
import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList <Topping> toppings; //Topping is an enum class

    protected Size size; //enum
    protected Sauce sauce;  //enum
    protected boolean extraSauce;
    protected boolean extraCheese;

    public abstract double price(); //polymorphism

    public abstract String pizzaType();

    public String getPizzaDetails() {
        return String.format("%s - Size: %s, Sauce: %s, Extra Sauce: %s, Extra Cheese: %s, Price: $%.2f",
                pizzaType(), size, sauce, extraSauce, extraCheese, price());
    }

}


