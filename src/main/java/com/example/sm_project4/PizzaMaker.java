package com.example.sm_project4;

import java.util.ArrayList;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType ) {
    Pizza pizza=null;
    if (pizzaType.equals("Deluxe")) {
        pizza=new Deluxe();

    } else if (pizzaType.equals("Meatzza")) {
        pizza=new Meatzza();

    }
    else if (pizzaType.equals("Pepperoni")) {
        pizza=new Pepperoni();

    }
    else if (pizzaType.equals("Seafood")) {
        pizza=new Seafood();

    }
    else if (pizzaType.equals("Supreme")) {
        pizza=new Meatzza();

    }

        return pizza;


    }


}
