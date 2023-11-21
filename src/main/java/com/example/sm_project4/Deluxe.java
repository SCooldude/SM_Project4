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



        /*public static Pizza createDeluxPizza(ArrayList <Topping>toppings ,Size size,Sauce sauce, boolean extraSauce,boolean extraCheese,double price ) {

        ArrayList<Topping> deluxeToppings = new ArrayList<>();
        deluxeToppings.add(Topping.SAUSAGE);
        deluxeToppings.add(Topping.PEPPERONI);
        deluxeToppings.add(Topping.GREEN_PEPPER);
        deluxeToppings.add(Topping.ONION);
        deluxeToppings.add(Topping.MUSHROOM);

        // Create and return a Deluxe pizza
        return new Deluxe(deluxeToppings, Size.LARGE, Sauce.TOMATO, true, true, 14.99);*/

        @Override
    public double price() {
        return 14.99;
    }


}
