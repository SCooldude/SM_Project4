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
    public int getOrderNumber() {
        return orderNumber;
    }
    public void removePizza(int index){
        pizzas.remove(index);
    }
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }
    public ArrayList<String> getPizzaStrings() {
        ArrayList<String> pizzas = new ArrayList<>();
        for (Pizza pizza : this.pizzas) {
            pizzas.add(pizza.toString());
        }
        return pizzas;
    }
    public double totalCost() {
        double total = 0;
        for (Pizza pizza : this.pizzas) {
            total += pizza.price();
        }
        return total;
    }
}

