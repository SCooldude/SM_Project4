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
}
