package com.example.sm_project4;

import java.util.ArrayList;

/**
 * This class represents a BuildYourOwn Pizza which extends off Pizza
 * It includes specific methods and constants related to BuildYourOwn
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class BuildYourOwn extends Pizza {
    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
        this.size = Size.Small;
    }
    /**
     * Calculates the price for the BuildYourOwnPizza
     * @return The total price of the pizza.
     *
     */
    @Override
    public double price(){
        double total = 8.99;
        if (this.size == Size.Medium){
            total += 2;
        }
        if(this.size == Size.Large){
            total += 4;
        }
        if(this.extraCheese){
            total += 1;
        }
        if(this.extraSauce){
            total += 1;
        }
        if(this.toppings.size() > 3){
            total += (toppings.size() - 3) * 1.49;
        }
        return total;
    }

    /**
     * Used whenever you want to get the String version of the pizza
     * @return The String to be used
     */
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();
    }

    /**
     * Used to get the pizza_type for the toString method
     * @return the pizza type
     */
    @Override
    public String pizzaType() {
        return "BuildYourOwn";
    }
}
