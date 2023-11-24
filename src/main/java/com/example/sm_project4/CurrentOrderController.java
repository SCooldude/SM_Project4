package com.example.sm_project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrentOrderController {

    private MainMenuController mainController;

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

    public void setMainController(MainMenuController controller) {
        mainController = controller;
        setOrderNumber();
        setPizzas();
        setPrices();
    }
    @FXML
    void setOrderNumber() {
        String currentOrderNum = String.valueOf(mainController.getStoreOrders().getAvailable_OrderNumber());
        orderNumberTextField.setText(currentOrderNum);
    }
    @FXML
    void setPizzas() {
        int currentOrderNum = mainController.getStoreOrders().getAvailable_OrderNumber();
        StoreOrders orders = mainController.getStoreOrders();
        Order currentOrder = orders.find(currentOrderNum);
        ArrayList<String> pizzas = currentOrder.getPizzas();

        ObservableList<String> pizzaString = FXCollections.observableArrayList(pizzas);
        currentOrdersListView.setItems(pizzaString);
        System.out.println(pizzaString);
    }
    @FXML
    void setPrices() {
        int currentOrderNum = mainController.getStoreOrders().getAvailable_OrderNumber();
        StoreOrders orders = mainController.getStoreOrders();
        Order currentOrder = orders.find(currentOrderNum);

        double subtotalDouble = currentOrder.total();
        String subtotalString = new DecimalFormat("#,##0.00").format(subtotalDouble);
        subtotalTextField.setText(String.valueOf(subtotalString));
        double taxDouble = subtotalDouble * 0.06625;
        String taxString = new DecimalFormat("#,##0.00").format(taxDouble);
        salesTaxTextField.setText(taxString);
        double totalDouble = subtotalDouble + taxDouble;
        String totalString = new DecimalFormat("#,##0.00").format(totalDouble);
        orderTotalTextField.setText(String.valueOf(totalString));
    }

}




