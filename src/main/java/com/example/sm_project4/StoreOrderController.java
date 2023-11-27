package com.example.sm_project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for the Store Order view.
 * Handles displaying and managing store orders.
 * Works in conjunction with JavaFX elements defined in the associated FXML file.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class StoreOrderController implements Initializable {

    @FXML
    private ListView<String> orderList;

    @FXML
    private TextField totalTx;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    private StoreOrders sOrder;
    private ArrayList<Integer> currentNumbers;

    private MainMenuController mainController = new MainMenuController().get_control();

    /**
     * Sets the reference to the main menu controller.
     * @param controller The instance of the main menu controller.
     */
    public void setMainMenuController(MainMenuController controller) {
        mainController = controller;
    }

    /**
     * Handles going back to the main menu.
     * @param event The ActionEvent triggered by the Back button.
     * @throws IOException If an I/O error occurs during the navigation.
     */
    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene mainMenuScene = new Scene(mainMenuRoot, 450, 550);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza");
        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Initializes the controller.
     * Sets default values and listeners for UI elements.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sOrder = mainController.getStores();

        if (sOrder.numOrders() - 1 == 0) {
            showAlert("No Pizza", "Nothing available");
            return;
        }
        currentNumbers = sOrder.getOrderNumbers();
        choiceBox.getItems().addAll(currentNumbers);
        choiceBox.setOnAction(this::showPizza);
    }

    /**
     * Sets the total price based on the selected order.
     */
    @FXML
    private void setPrice() {
        int orderNum = choiceBox.getValue();
        double taxes = 0.06625;
        double subtotal = sOrder.find(orderNum).totalCost();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalTx.setText(totalString);
    }

    /**
     * Displays the pizzas of the selected order.
     * @param event The ActionEvent triggered by selecting an order from the ChoiceBox.
     */
    @FXML
    private void showPizza(ActionEvent event) {
        if (choiceBox.getValue() == null) {
            totalTx.setText("");
            orderList.setItems(null);
            return;
        }
        Order selectedOrder = sOrder.find(choiceBox.getValue());
        ArrayList<String> pizzas = selectedOrder.getPizzaStrings();
        ObservableList<String> pizzaString = FXCollections.observableArrayList(pizzas);
        orderList.setItems(pizzaString);
        setPrice();
    }

    /**
     * Displays an information alert.
     * @param title The title of the alert.
     * @param content The content of the alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Cancels the selected order.
     */
    @FXML
    private void cancelOrder() {
        if (choiceBox.getValue() == null) {
            showAlert("Null", "Nothing selected");
            return;
        }

        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        int number = choiceBox.getValue();

        if (!contains(ordersPlaced, number)) {
            showAlert("Cancel", "Nothing placed");
            return;
        }
        sOrder = mainController.getStores();
        ArrayList<String> pizzaList = sOrder.find(number).getPizzaStrings();
        if (pizzaList.isEmpty()) {
            showAlert("Order", "Nothing in order");
            return;
        }
        removeOrder(number);
        choiceBox.getItems().remove(number);
        showAlert("Cancel Success!", "Cancelled!");
        if (!choiceBox.getItems().isEmpty()) {
            choiceBox.setValue(choiceBox.getItems().get(0));
        } else {
            totalTx.clear();
            orderList.setItems(null);
        }
    }

    /**
     * Checks if the list contains a specific order number.
     * @param list The list to check.
     * @param orderNumber The order number to check for.
     * @return True if the order number is in the list, false otherwise.
     */
    private boolean contains(ArrayList<Integer> list, int orderNumber) {
        for (Integer integer : list) {
            if (integer == orderNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the order with the specified number from the placed orders list.
     * @param orderNumber The order number to remove.
     */
    private void removeOrder(int orderNumber) {
        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        for (int i = 0; i < ordersPlaced.size(); i++) {
            if (ordersPlaced.get(i) == orderNumber) {
                ordersPlaced.remove(i);
                return;
            }
        }
    }

    /**
     * Checks if all orders are placed.
     * @return True if all orders are placed, false otherwise.
     */
    @FXML
    private boolean allOrders() {
        sOrder = mainController.get_control().getStores();
        currentNumbers = mainController.get_control().getStores().getOrderNumbers();
        int index = currentNumbers.get(currentNumbers.size() - 1);
        return sOrder.find(index).getPizzaStrings().isEmpty();
    }

    /**
     * Exports the orders to a file.
     * @param event The ActionEvent triggered by the Export button.
     */
    @FXML
    private void exportToFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if (!allOrders()) {
            showAlert("Not Placed", "Order not Placed");
            return;
        }
        boolean exported = sOrder.export(stage);
        if (!exported) {
            showAlert("Error", "Could not export");
            return;
        }
        showAlert("Success!", "Exported!");
    }
}
