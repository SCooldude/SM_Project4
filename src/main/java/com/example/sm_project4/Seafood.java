package com.example.sm_project4;

import java.util.ArrayList;

/**
 * This class represents a Seafood Pizza which extends off Pizza.
 * It includes specific methods and constants related to Seafood Pizza.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class Seafood extends Pizza {

    /**
     * Constructs a Seafood pizza with predefined toppings, sauce, and size.
     */
    public Seafood() {
        ArrayList<String> toppings = new ArrayList<>();
        toppings.add(String.valueOf(Topping.SQUID));
        toppings.add(String.valueOf(Topping.SHRIMP));
        toppings.add(String.valueOf(Topping.CRAB_MEAT));

        this.toppings = toppings;
        this.sauce = Sauce.ALFREDO;
        this.size = Size.Small;
    }

    /**
     * Calculates the price for the Seafood pizza.
     *
     * @return The total price of the pizza.
     */
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

    /**
     * Gets the pizza_type for the toString method.
     *
     * @return The pizza_type as a String.
     */
    @Override
    public String pizzaType() {
        return "Seafood";
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
