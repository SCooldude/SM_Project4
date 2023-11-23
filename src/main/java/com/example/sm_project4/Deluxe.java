package com.example.sm_project4;

import java.util.ArrayList;

public class Deluxe extends Pizza{

    protected ArrayList<Topping> getDeluxeToppings() {
        ArrayList<Topping> deluxeToppings = new ArrayList<>();
        deluxeToppings.add(Topping.SAUSAGE);
        deluxeToppings.add(Topping.PEPPERONI);
        deluxeToppings.add(Topping.GREEN_PEPPER);
        deluxeToppings.add(Topping.ONION);
        deluxeToppings.add(Topping.MUSHROOM);
        return deluxeToppings;
    }

    protected Sauce getDeluxeSauce(){
        return Sauce.TOMATO;
    }


        @Override
    public double price() {

        return 14.99;
    }

    @Override
    public String pizzaType() {
        return "Deluxe";
    }



}
