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






