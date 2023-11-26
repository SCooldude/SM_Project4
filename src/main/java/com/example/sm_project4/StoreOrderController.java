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
    private ArrayList<Integer> currentOrderNumb;

    private MainMenuController mainController = new MainMenuController().get_control();

    public void setMainMenuController(MainMenuController controller) {
        mainController = controller;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sOrder = mainController.getStores();
        if (sOrder.numberOfOrders() == 0) {
            showAlert("No Pizza", "Nothing available");
            return;
        }
        currentOrderNumb = sOrder.getOrderNumbers();
        choiceBox.getItems().addAll(currentOrderNumb);
        choiceBox.setOnAction(this::displayPizzas);
        choiceBox.setValue(0);
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
    private void updateChoiceBox() {
        choiceBox.getItems().removeAll(currentOrderNumb);
        currentOrderNumb = mainController.getStores().getOrderNumbers();
        choiceBox.getItems().addAll(currentOrderNumb);

    }
    @FXML
    private void displayPizzas(ActionEvent event) {
        if (choiceBox.getValue() == null || sOrder.numberOfOrders() == 0) {
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
    private void cancelOrder(ActionEvent event) {
        if (choiceBox.getValue() == null) {
            showAlert("Null", "Nothing selected");
            return;
        }

        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        int currentNumb = choiceBox.getValue();

        if (!contains(ordersPlaced, currentNumb)) {
            showAlert("Cancel", "Nothing placed");
            return;
        }
        sOrder = mainController.getStores();
        ArrayList<String> pizzaList = sOrder.find(currentNumb).getPizzaStrings();
        if (pizzaList.isEmpty()) {
            showAlert("Order", "Nothing in order");
            return;
        }
        removeOrderPlaced(currentNumb);
        updateChoiceBox();
        showAlert("Cancel Success!", "Cancelled!");
        choiceBox.getItems().remove(currentNumb);

        if (!choiceBox.getItems().isEmpty()) {
            choiceBox.setValue(choiceBox.getItems().get(0));
            displayPizzas(event);
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

    private void removeOrderPlaced(int orderNumber){
        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        for (int i =0; i<ordersPlaced.size(); i++){
            if(ordersPlaced.get(i) == orderNumber){
                ordersPlaced.remove(i);
                return;
            }
        }
    }

    private boolean allOrdersPlaced(){
        sOrder = mainController.get_control().getStores();
        currentOrderNumb = mainController.get_control().getStores().getOrderNumbers();
        int index = currentOrderNumb.get(currentOrderNumb.size()-1);
        return sOrder.find(index).getPizzaStrings().isEmpty();

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
    private void exportToFile(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if(!allOrdersPlaced()){
            showAlert("Not Placed", "Order not Placed");
            return;
        }
        boolean expSuccess = sOrder.export(stage);
        if(!expSuccess){
            showAlert("Error", "Could not export");
            return;
        }
        showAlert("Success!", "Exported!");
    }
}