package com.example.sm_project4;
public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {

        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe();
            case "Meatzza" -> new Meatzza();
            case "Pepperoni" -> new Pepperoni();
            case "Seafood" -> new Seafood();
            case "Supreme" -> new Supreme();
            default -> null;
        };
    }
}
