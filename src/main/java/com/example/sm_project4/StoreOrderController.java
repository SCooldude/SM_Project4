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

    public void setMainMenuController(MainMenuController controller) {
        mainController = controller;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sOrder = mainController.getStores();

        if (sOrder.numOrders() - 1 == 0) {
            showAlert("No Pizza","Nothing available");
            return;
        }
        currentNumbers = sOrder.getOrderNumbers();
        choiceBox.getItems().addAll(currentNumbers);
        choiceBox.setOnAction(this::showPizza);
    }
    @FXML
    private void setPrice() {
        int orderNum = choiceBox.getValue();
        double taxes = 0.06625;
        double subtotal = sOrder.find(orderNum).totalCost();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalTx.setText(totalString);
    }
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

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
        remove_Order(number);
        choiceBox.getItems().remove(number);
        showAlert("Cancel Success!", "Cancelled!");
        if (!choiceBox.getItems().isEmpty()) {
            choiceBox.setValue(choiceBox.getItems().get(0));
        } else {
            totalTx.clear();
            orderList.setItems(null);
        }
    }
    private boolean contains(ArrayList<Integer> list, int orderNumber){
        for (Integer integer : list) {
            if (integer == orderNumber) {
                return true;
            }
        }
        return false;
    }

    private void remove_Order(int orderNumber){
        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        for (int i = 0; i < ordersPlaced.size(); i++){
            if (ordersPlaced.get(i) == orderNumber){
                ordersPlaced.remove(i);
                return;
            }
        }
    }

    private boolean allOrders(){
        sOrder = mainController.get_control().getStores();
        currentNumbers = mainController.get_control().getStores().getOrderNumbers();
        int index = currentNumbers.get(currentNumbers.size()-1);
        return sOrder.find(index).getPizzaStrings().isEmpty();

    }
    @FXML
    private void exportToFile(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if(!allOrders()){
            showAlert("Not Placed", "Order not Placed");
            return;
        }
        boolean exported = sOrder.export(stage);
        if(!exported){
            showAlert("Error", "Could not export");
            return;
        }
        showAlert("Success!", "Exported!");
    }
}