package com.example.sm_project4;

import java.util.ArrayList;

/**
 * This abstract class represents a generic Pizza.
 * It includes common attributes and methods that are applicable to all types of pizzas.
 * Subclasses must implement the price and pizzaType methods.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public abstract class Pizza {
    protected ArrayList<String> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Abstract method to calculate the price of the pizza.
     *
     */
    public abstract double price();

    /**
     * Abstract method to get the type of pizza.
     *
     */
    public abstract String pizzaType();

    /**
     * Gets the String representation of the pizza.
     *
     * @return The String representation of the pizza.
     */
    @Override
    public String toString() {
        String extraSauceOutput = extraSauce ? "extra sauce" : "";
        String extraCheeseOutput = extraCheese ? "extra cheese" : "";

        StringBuilder toppingsOutput = new StringBuilder();
        for (String topping : toppings) {
            toppingsOutput.append(topping).append(", ");
        }
        if (!toppingsOutput.isEmpty()) {
            toppingsOutput.setLength(toppingsOutput.length() - 2);
        }
        String formattedPrice = String.format("%.2f", price());

        return toppingsOutput + "  " + size + "  " + extraSauceOutput + " " + extraCheeseOutput + " $" + formattedPrice;
    }
}
