package com.example.sm_project4;

import java.util.ArrayList;

public class Meatzza extends Pizza{

    public Meatzza() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.SAUSAGE));
        toppings.add(String.valueOf(Topping.BEEF));
        toppings.add(String.valueOf(Topping.HAM));
        toppings.add(String.valueOf(Topping.PEPPERONI));
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }
    @Override
    public double price() {
        double total = 16.99;
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
        return "Meatzza";
    }
    @Override
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();

    }
}
