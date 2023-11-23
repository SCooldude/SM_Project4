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

};

