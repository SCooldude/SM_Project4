package com.example.sm_project4;
import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price();
    public abstract String pizzaType();
    public abstract String getToppings();

    @Override
    public String toString() {
        String extraSauceOutput = "extra sauce";
        String extraCheeseOutput = "extra cheese";
        double cost = price();

        if (!extraSauce) {
            extraSauceOutput = "";
            cost += 1;
        }
        if (!extraCheese) {
            extraCheeseOutput = "";
            cost += 1;
        }
        if (size.getString().equals("Medium")) {
            cost += 2;
        }
        if (size.getString().equals("Large")) {
            cost += 4;

        }
        return "[" + pizzaType() + "]" + getToppings() + sauce + size + extraSauceOutput + extraCheeseOutput + cost;
    }
}






