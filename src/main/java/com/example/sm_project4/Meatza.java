package com.example.sm_project4;

import java.util.ArrayList;

public class Meatza extends Pizza{
    protected ArrayList<Topping> getMeatzaToppings() {
        ArrayList<Topping> MeatzaToppings = new ArrayList<>();
        MeatzaToppings.add(Topping.SAUSAGE);
        MeatzaToppings.add(Topping.PEPPERONI);
        MeatzaToppings.add(Topping.HAM);
        MeatzaToppings.add(Topping.BLACK_OLIVE);
        MeatzaToppings.add(Topping.ONION);
        MeatzaToppings.add(Topping.GREEN_PEPPER);
        MeatzaToppings.add(Topping.MUSHROOM);
        return  MeatzaToppings;
    }




    @Override
    public double price() {
        return 16.99;
    }
}
