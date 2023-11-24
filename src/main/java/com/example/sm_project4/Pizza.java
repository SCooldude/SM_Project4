package com.example.sm_project4;
import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<String> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price();
    public abstract String pizzaType();

    @Override
    public String toString() {
        String extraSauceOutput = "extra sauce";
        String extraCheeseOutput = "extra cheese";
        String toppings = this.toppings.toString();

        if (!extraSauce) {
            extraSauceOutput = "";
        }
        if (!extraCheese) {
            extraCheeseOutput = "";
        }
        return "[" + toppings  + "]" + extraSauceOutput + " " + extraCheeseOutput + "$" + price();
    }
}






