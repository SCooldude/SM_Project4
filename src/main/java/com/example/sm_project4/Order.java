package com.example.sm_project4;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an order containing a list of pizzas.
 * It provides methods to add and remove pizzas, calculate the total cost,
 * and retrieve information about the order.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class Order {
    private final int orderNumber;
    private final List<Pizza> pizzas;

    /**
     * Constructor to initialize the order with an order number and a list of pizzas.
     *
     * @param orderNumber The unique identifier for the order.
     * @param pizzas      The list of pizzas in the order.
     */
    public Order(int orderNumber, List<Pizza> pizzas) {
        this.orderNumber = orderNumber;
        this.pizzas = pizzas;
    }

    /**
     * Gets the order number.
     *
     * @return The order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Removes a pizza from the order based on its index.
     *
     * @param index The index of the pizza to be removed.
     */
    public void removePizza(int index) {
        pizzas.remove(index);
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The pizza to be added.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Retrieves a list of strings representing each pizza in the order.
     *
     * @return The list of pizza strings.
     */
    public ArrayList<String> getPizzaStrings() {
        ArrayList<String> pizzaStrings = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            pizzaStrings.add(pizza.toString());
        }
        return pizzaStrings;
    }

    /**
     * Calculates the total cost of the order by summing up the prices of all pizzas.
     *
     * @return The total cost of the order.
     */
    public double totalCost() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }
}
