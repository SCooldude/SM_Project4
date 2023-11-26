package com.example.sm_project4;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {
    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
        this.size = Size.Small;
    }
    @Override
    public double price(){
        double total = 8.99;
        if (this.size == Size.Medium){
            total = 2;
        }
        if(this.size == Size.Large){
            total += 4;
        }
        if(this.extraCheese){
            total += 1;
        }
        if(this.extraSauce){
            total += 1;
        }
        if(this.toppings.size() > 3){
            total += (toppings.size() - 3) * 1.49;
        }
        return total;
    }
    public String toString() {
        return "[" + pizzaType() + "] " + super.toString();
    }

    @Override
    public String pizzaType() {
        return "BuildYourOwn";
    }
}
