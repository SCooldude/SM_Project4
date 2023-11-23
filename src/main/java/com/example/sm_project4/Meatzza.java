package com.example.sm_project4;

import java.util.ArrayList;

public class Meatzza extends Pizza{
    protected ArrayList<Topping> getMeatzzaToppings() {
        ArrayList<Topping> MeatzzaToppings = new ArrayList<>();
        MeatzzaToppings.add(Topping.SAUSAGE);
        MeatzzaToppings.add(Topping.PEPPERONI);
        MeatzzaToppings.add(Topping.HAM);
        MeatzzaToppings.add(Topping.BEEF);

        return  MeatzzaToppings;
    }
    protected Sauce getMeatzzaSauce(){
        return Sauce.TOMATO;
    }




    @Override
    public double price() {
        return 16.99;
    }

    @Override
    public String pizzaType() {
        return "Meatzza";
    }

    @Override
    public String getToppings() {
        StringBuilder toppingsString = new StringBuilder(" ");

        // Get the deluxe toppings
        ArrayList<Topping> DeluxeToppings = getMeatzzaToppings();

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
