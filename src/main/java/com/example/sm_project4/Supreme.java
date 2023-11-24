package com.example.sm_project4;

import java.util.ArrayList;

public class Supreme extends Pizza{

    public Supreme() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.SAUSAGE));
        toppings.add(String.valueOf(Topping.PEPPERONI));
        toppings.add(String.valueOf(Topping.GREEN_PEPPER));
        toppings.add(String.valueOf(Topping.ONION));
        toppings.add(String.valueOf(Topping.MUSHROOM));
        toppings.add(String.valueOf(Topping.HAM));
        toppings.add(String.valueOf(Topping.BLACK_OLIVE));

        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }
    @Override
    public double price() {
        double total = 15.99;
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
        return "Supreme";
    }
    @Override
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();

    }
}
