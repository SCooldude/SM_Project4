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
    private TextField totalText;
    @FXML
    private ChoiceBox<Integer> soBox;
    private MainMenuController mainController = new MainMenuController().get_control();
    private StoreOrders orders;
    private ArrayList<Integer> currentOrderNumbers;

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
        orders = mainController.getStores();
        if (orders.numberOfOrders() == 0) {
            showAlert("No Pizza", "Nothing available");
            return;
        }
        currentOrderNumbers = orders.getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);
        soBox.setOnAction(this::displayPizzas);
        soBox.setValue(0);
    }
    @FXML
    private void setPrice() {
        int orderNum = soBox.getValue();
        double taxes = 0.06625;
        double subtotal = orders.find(orderNum).total();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalText.setText(totalString);
    }

    @FXML
    private void updateChoiceBox() {
        soBox.getItems().removeAll(currentOrderNumbers);
        currentOrderNumbers = mainController.getStores().getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);

    }
    @FXML
    private void displayPizzas(ActionEvent event) {
        if (soBox.getValue() == null || orders.numberOfOrders() == 0) {
            totalText.setText("");
            orderList.setItems(null);
            return;
        }

        Order selectedOrder = orders.find(soBox.getValue());
        ArrayList<String> pizzas = selectedOrder.getPizzas();
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
        if (soBox.getValue() == null) {
            showAlert("Null", "Nothing selected");
            return;
        }

        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        int currentNumb = soBox.getValue();

        if (!contains(ordersPlaced, currentNumb)) {
            showAlert("Cancel", "Nothing placed");
            return;
        }
        orders = mainController.getStores();
        ArrayList<String> pizzaList = orders.find(currentNumb).getPizzas();
        if (pizzaList.isEmpty()) {
            showAlert("Order", "Nothing in order");
            return;
        }
        removeOrderPlaced(currentNumb);
        updateChoiceBox();
        showAlert("Cancel Success!", "Cancelled!");
        soBox.getItems().remove(currentNumb);

        if (!soBox.getItems().isEmpty()) {
            soBox.setValue(soBox.getItems().get(0));
            displayPizzas(event);
        } else {
            totalText.clear();
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
        orders = mainController.get_control().getStores();
        currentOrderNumbers = mainController.get_control().getStores().getOrderNumbers();
        int index = currentOrderNumbers.get(currentOrderNumbers.size()-1);
        return orders.find(index).getPizzas().isEmpty();

    }
    @FXML
    private void exportToFile(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        if(!allOrdersPlaced()){
            showAlert("Not Placed", "Order not Placed");
            return;
        }
        boolean expSuccess = orders.export(stage);
        if(!expSuccess){
            showAlert("Error", "Could not export");
            return;
        }
        showAlert("Success!", "Exported!");
    }
}