package com.example.sm_project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

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

    public void setMainMenuController(MainMenuController controller) {
        mainController = controller;
        setOrderNumber();
        initializePizza();
        initializePrice();
    }
    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene mainMenuScene = new Scene(mainMenuRoot, 450, 550);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza");
        stage.setScene(mainMenuScene);
        stage.show();
    }
    @FXML
    private void setOrderNumber() {
        String orderNumber = String.valueOf(mainController.getStores().nextAvailableNumber());
        orderNumberTextField.setText(orderNumber);
    }
    @FXML
    private void initializePrice() {
        int currentOrderNum = mainController.getStores().nextAvailableNumber();
        StoreOrders orders = mainController.getStores();
        Order currentOrder = orders.find(currentOrderNum);

        double subtotalDouble = currentOrder.totalCost();
        String subtotalString = new DecimalFormat("#,##0.00").format(subtotalDouble);
        subtotalTextField.setText(String.valueOf(subtotalString));
        double tax = subtotalDouble * 0.06625;
        String taxString = new DecimalFormat("#,##0.00").format(tax);
        salesTaxTextField.setText(taxString);
        double total = subtotalDouble + tax;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        orderTotalTextField.setText(String.valueOf(totalString));
    }
    @FXML
    private void initializePizza() {
        int currentOrderNum = mainController.getStores().nextAvailableNumber();
        StoreOrders orders = mainController.getStores();
        Order currentOrder = orders.find(currentOrderNum);
        ArrayList<String> pizzas = currentOrder.getPizzaStrings();
        ObservableList<String> pizzaString = FXCollections.observableArrayList(pizzas);
        currentOrdersListView.setItems(pizzaString);
    }
    @FXML
    protected void handleRemovePizza() {
        int selectedIndex = currentOrdersListView.getSelectionModel().getSelectedIndex();
        StoreOrders orders = mainController.getStores();
        Order currentOrder = orders.find(mainController.getStores().nextAvailableNumber());
        try {
            currentOrder.removePizza(selectedIndex);
        } catch (IndexOutOfBoundsException e) {
            showAlert("Error", "No pizza to remove at the selected index");
            return;
        }
        initializePizza();
        initializePrice();
    }

    @FXML
    protected void handlePlaceOrder() {
        int currIndex = mainController.getStores().nextAvailableNumber();
        StoreOrders orders = mainController.getStores();

        ArrayList<String> pizzaList = orders.find(currIndex).getPizzaStrings();
        if (pizzaList.isEmpty()) {
            showAlert("Empty", "No pizzas to place");
            return;
        }

        orders.addOrder(orders.find(currIndex));
        mainController.get_placed().add(currIndex);

        initializePizza();
        initializePrice();
        setOrderNumber();
        showAlert("Success!", "Order Placed!");

    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}




