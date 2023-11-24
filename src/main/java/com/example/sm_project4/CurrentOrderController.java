package com.example.sm_project4;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CurrentOrderController {

    private MainMenuController mainMenuController;
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

    public void setMainController(MainMenuController controller) {
        mainMenuController = controller;
    }

    @FXML
    private void initialize() {
        System.out.println("Updating Order ListView");
        this.currentOrder = new Order();
        updateOrderListView();
    }

    private void updateOrderListView() {

        orderNumberTextField.setText(String.valueOf(currentOrder.getOrderNumber()));

            for (Pizza pizza : currentOrder.getPizzas()) {
                currentOrdersListView.getItems().add(String.valueOf(pizza));
            }

            double subtotal = calculateSubtotal();
            subtotalTextField.setText(String.format("%.2f", subtotal));

            double salesTax = calculateSalesTax(subtotal);
            salesTaxTextField.setText(String.format("%.2f", salesTax));

            double orderTotal = subtotal + salesTax;
            orderTotalTextField.setText(String.format("%.2f", orderTotal));
        }

    private double calculateSubtotal() {
        double subtotal = 0.0;
        for (Pizza pizza : currentOrder.getPizzas()) {
            subtotal += pizza.getPrice_ofPizza();
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

