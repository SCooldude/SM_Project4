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
import javafx.scene.control.SelectionModel;
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
    private void onBackButtonClick(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene mainMenuScene = new Scene(mainMenuRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza");
        stage.setScene(mainMenuScene);
        stage.show();
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
    @FXML
    protected void handleRemovePizza() {
        SelectionModel<String> selected = currentOrdersListView.getSelectionModel();
        int selectedIndex = selected.getSelectedIndex();

        StoreOrders orders = mainController.getStoreOrders();
        Order currentOrder = orders.find(mainController.getStoreOrders().getAvailable_OrderNumber());
        currentOrder.removePizza(selectedIndex);
        setPizzas();
        setPrices();
    }

    @FXML
    protected void handlePlaceOrder() {
        int currIndex = mainController.getStoreOrders().getAvailable_OrderNumber();
        StoreOrders orders = mainController.getStoreOrders();

        ArrayList<String> pizzaList = orders.find(currIndex).getPizzas();
        if (pizzaList.isEmpty()) {
            emptyPizzaPopup();
            return;
        }

        orders.addOrder(orders.find(currIndex));
        mainController.getOrdersPlaced().add(currIndex); //purpose since all orders whether it was placed or not shows in storeOrders

        setPizzas();
        setPrices();
        setOrderNumber();
        orderSuccessPopup();

    }
    void emptyPizzaPopup() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Order Warning");
        alert.setContentText("Cannot place an order without any pizzas!");
        alert.showAndWait();
    }

    /**
     * Create popup for successful order.
     */
    void orderSuccessPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Success");
        alert.setContentText("Your order was successfully placed!");
        alert.showAndWait();
    }


}




