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
}
