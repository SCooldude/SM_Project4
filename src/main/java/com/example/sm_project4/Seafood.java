package com.example.sm_project4;

import java.util.ArrayList;

public class Seafood extends Pizza {
    protected ArrayList<Topping> getSeaFoodToppings() {
        ArrayList<Topping> SeaFoodToppings = new ArrayList<>();
        SeaFoodToppings.add(Topping.SHRIMP);
        SeaFoodToppings.add(Topping.SQUID);
        SeaFoodToppings.add(Topping.CRAB_MEAT);

        return SeaFoodToppings;
    }

    protected Sauce getSeaFoodSauce(){
        return Sauce.ALFREDO;
    }
    @Override
    public double price() {
            return 17.99;
        }

    @Override
    public String pizzaType() {
        return "Seafood";
    }

    @Override
    public String getToppings() {
        StringBuilder toppingsString = new StringBuilder(" ");

        // Get the deluxe toppings
        ArrayList<Topping> DeluxeToppings = getSeaFoodToppings();

        // Append each topping to the string
        for (Topping topping : DeluxeToppings) {
            toppingsString.append(topping).append(", ");
        }

        // Remove the trailing comma and space
        toppingsString.setLength(toppingsString.length() - 2);

        // Return the final toppings string
        return toppingsString.toString();
    }

};

