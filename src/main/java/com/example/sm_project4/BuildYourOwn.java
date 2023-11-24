package com.example.sm_project4;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{

    public static double price = 8.99;

    @Override
    public double price(){
        double total = price;
        if(this.size == Size.Medium){
            total= 2;
        }
        if(this.size == Size.Large){
            total+=4;
        }
        if(this.extraCheese){
            total+=1;
        }
        if(this.extraSauce){
            total+=1;
        }
        if(this.toppings.size() > 3){
            total += (toppings.size() - 3) * 1.49;
        }
        return total;
    }
    @Override
    public String toString() {
        return "your momma";
    }

    @Override
    public String pizzaType() {
        return "BuildYourOwn";
    }

    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
        this.size = Size.Small;
    }
}
