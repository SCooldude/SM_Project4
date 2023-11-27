package com.example.sm_project4;

import java.util.ArrayList;

/**
 * This class represents a Supreme Pizza which extends off Pizza.
 * It includes specific methods and constants related to Supreme Pizza.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class Supreme extends Pizza {

    /**
     * Constructs a Supreme pizza with predefined toppings, sauce, and size.
     */
    public Supreme() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.SAUSAGE));
        toppings.add(String.valueOf(Topping.PEPPERONI));
        toppings.add(String.valueOf(Topping.MUSHROOM));
        toppings.add(String.valueOf(Topping.GREEN_PEPPER));
        toppings.add(String.valueOf(Topping.BLACK_OLIVE));
        toppings.add(String.valueOf(Topping.ONION));
        toppings.add(String.valueOf(Topping.HAM));

        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the Supreme pizza.
     *
     * @return The total price of the pizza.
     */
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

    /**
     * Gets the pizza_type for the toString method.
     *
     * @return The pizza_type as a String.
     */
    @Override
    public String pizzaType() {
        return "Supreme";
    }

    /**
     * Gets the String representation of the pizza.
     *
     * @return The String representation of the pizza.
     */
    @Override
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();
    }
}
