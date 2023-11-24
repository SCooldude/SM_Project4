package com.example.sm_project4;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CurrentOrderController {
    @FXML
    private TextField orderNumberTextField;
    @FXML
    private ListView<String> currentOrdersListView;
    @FXML
    private TextField subtotalTextField;
    @FXML
    private TextField salesTaxTextField;
    @FXML
    private TextField orderTotalTextField;

    private Order currentOrder;

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
        updateOrderListView();
    }

    private void updateOrderListView() {
        currentOrdersListView.getItems().clear();

        if (currentOrder != null) {
            orderNumberTextField.setText(String.valueOf(currentOrder.getOrderNumber()));

            for (Pizza pizza : currentOrder.getPizzas()) {
                currentOrdersListView.getItems().add(pizza.getPizzaDetails());
            }

            // Calculate and display other order details (subtotal, sales tax, order total)
            double subtotal = calculateSubtotal();
            subtotalTextField.setText(String.format("%.2f", subtotal));

            double salesTax = calculateSalesTax(subtotal);
            salesTaxTextField.setText(String.format("%.2f", salesTax));

            double orderTotal = subtotal + salesTax;
            orderTotalTextField.setText(String.format("%.2f", orderTotal));
        } else {
            orderNumberTextField.clear();
            subtotalTextField.clear();
            salesTaxTextField.clear();
            orderTotalTextField.clear();
        }
    }

    private double calculateSubtotal() {
        double subtotal = 0.0;
        for (Pizza pizza : currentOrder.getPizzas()) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    private double calculateSalesTax(double subtotal) {
        return subtotal * 0.06625;
    }


    @FXML
    private void handleRemovePizza() {
    }

    @FXML
    private void handlePlaceOrder() {
    }
}
