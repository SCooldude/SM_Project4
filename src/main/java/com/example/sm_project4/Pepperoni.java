package com.example.sm_project4;

import java.util.ArrayList;

public class Pepperoni extends Pizza{

    public Pepperoni() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.PEPPERONI));
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }
    @Override
    public double price() {
        double total = 10.99;
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
        return "Pepperoni";
    }
    @Override
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();

    }
}
