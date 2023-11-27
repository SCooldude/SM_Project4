package com.example.sm_project4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildYourOwnTest {

    @Test
    void testSmallPizza_WithoutExtrasAndToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        assertEquals(8.99, pizza.price());
    }

    @Test
    void testMediumPizza_WithExtrasAndToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.Medium;
        pizza.extraCheese = true;
        pizza.extraSauce = true;
        pizza.toppings.add("Pepperoni");
        pizza.toppings.add("Mushroom");
        pizza.toppings.add("Onion");

        assertEquals(8.99 + 2 + 1 + 1, pizza.price());
    }

    @Test
    void testLargePizza_WithExtraCheese() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size  = Size.Large;
        pizza.extraCheese = true;

        assertEquals(8.99 + 4 + 1, pizza.price());
    }

    @Test
    void testSmallPizza_WithFourToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.toppings.add("Pepperoni");
        pizza.toppings.add("Mushrooms");
        pizza.toppings.add("Onions");
        pizza.toppings.add("Green Peppers");

        assertEquals(8.99 + 1.49, pizza.price());
    }

    @Test
    void testMediumPizza_WithoutExtrasAndToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.Medium;

        assertEquals(10.99, pizza.price());
    }
}
