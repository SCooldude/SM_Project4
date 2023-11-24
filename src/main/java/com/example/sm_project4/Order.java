package com.example.sm_project4;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int orderNumber;
    private final List<Pizza> pizzas;

    public Order(int OrderNumber, List<Pizza> pizzas) {
        this.orderNumber = OrderNumber;
        this.pizzas = pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }
    public void removePizza(int index){ pizzas.remove(index);
    }
    public ArrayList<String> getPizzas() {
        ArrayList<String> pizzas = new ArrayList<>();
        for (Pizza pizza : this.pizzas) {
            pizzas.add(pizza.toString());
        }
        return pizzas;
    }
    public double total() {
        double total = 0.0;
        for (Pizza pizza : this.pizzas) {
            total += pizza.price();
        }
        return total;
    }
    public Pizza getPizza(int index){
        return pizzas.get(index);
    }
    public int getOrderNumber() {
        return orderNumber;
    }
}

