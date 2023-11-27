package com.example.sm_project4;

/**
 * This class represents a PizzaMaker that creates instances of different pizza types.
 * It includes a static method to create a pizza based on the provided pizzaType.
 * The method uses a switch statement to determine the pizza type and instantiate the corresponding class.
 * If an unknown type is provided, it throws an IllegalStateException.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class PizzaMaker {

    /**
     * Creates a pizza based on the provided pizzaType.
     *
     * @param pizzaType The type of pizza to create.
     * @return An instance of the specified pizza type.
     * @throws IllegalStateException If an unknown pizza type is provided.
     */
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe();
            case "Meatzza" -> new Meatzza();
            case "Pepperoni" -> new Pepperoni();
            case "Seafood" -> new Seafood();
            case "Supreme" -> new Supreme();
            case "Build Your Own" -> new BuildYourOwn();
            default -> throw new IllegalStateException("Unknown type");
        };
    }
}
