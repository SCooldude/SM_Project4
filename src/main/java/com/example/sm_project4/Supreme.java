package com.example.sm_project4;

import java.util.ArrayList;

public class Supreme extends Pizza{
    protected ArrayList<Topping> getSupremeToppings() {
        ArrayList<Topping> deluxeToppings = new ArrayList<>();
        deluxeToppings.add(Topping.SAUSAGE);
        deluxeToppings.add(Topping.PEPPERONI);
        deluxeToppings.add(Topping.GREEN_PEPPER);
        deluxeToppings.add(Topping.ONION);
        deluxeToppings.add(Topping.MUSHROOM);
        deluxeToppings.add(Topping.HAM);
        deluxeToppings.add(Topping.BLACK_OLIVE);
        return deluxeToppings;
    }

    protected Sauce getSupremeSauce(){
        return Sauce.TOMATO;
    }

    @Override
    public double price() {
        return 0;
    }

    @Override
    public String pizzaType() {
        return "Supreme";
    }

    @Override
    public String getToppings() {
        StringBuilder toppingsString = new StringBuilder(" ");

        // Get the deluxe toppings
        ArrayList<Topping> DeluxeToppings = getSupremeToppings();

        // Append each topping to the string
        for (Topping topping : DeluxeToppings) {
            toppingsString.append(topping).append(", ");
        }

        // Remove the trailing comma and space
        toppingsString.setLength(toppingsString.length() - 2);

        // Return the final toppings string
        return toppingsString.toString();
    }
}
