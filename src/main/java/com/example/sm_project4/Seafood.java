package com.example.sm_project4;

import java.util.ArrayList;

public class Seafood extends Pizza{

    public Seafood() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.SQUID));
        toppings.add(String.valueOf(Topping.SHRIMP));
        toppings.add(String.valueOf(Topping.CRAB_MEAT));
        this.toppings = toppings;
        this.sauce = Sauce.ALFREDO;
        this.size = Size.Small;
    }
    @Override
    public double price() {
        double total = 17.99;
        if (this.extraCheese) {
            total += 1.0;
        }
        if (this.extraSauce) {
            total += 1.0;
        }
        if (this.size == Size.Medium) {
            total += 2.0;
        }
        if (this.size == Size.Large) {
            total += 4.0;
        }
        return total;
    }
    @Override
    public String pizzaType() {
        return "Seafood";
    }
    @Override
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();

    }
}
