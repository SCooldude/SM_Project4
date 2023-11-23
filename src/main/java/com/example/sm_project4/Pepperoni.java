package com.example.sm_project4;

import java.util.ArrayList;

public class Pepperoni extends Pizza{
    protected ArrayList<Topping> getPepperoniToppings() {
        ArrayList<Topping> PepperoniToppings = new ArrayList<>();

        PepperoniToppings.add(Topping.PEPPERONI);

        return PepperoniToppings;
    }


    @Override
    public double price() {
        return 10.99;
    }

    @Override
    public String pizzaType() {
        return "Pepperoni";
    }

    @Override
    public String getToppings() {
        StringBuilder toppingsString = new StringBuilder(" ");

        // Get the deluxe toppings
        ArrayList<Topping> DeluxeToppings = getPepperoniToppings();

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
