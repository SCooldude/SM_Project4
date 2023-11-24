package com.example.sm_project4;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderNumber = 1;

    private final int orderNumber;
    private final List<Pizza> pizzas;

    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
