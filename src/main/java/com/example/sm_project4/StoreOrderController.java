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
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreOrderController implements Initializable {

    @FXML
    private ListView<String> orderList;
    @FXML
    private TextField totalText;
    @FXML
    private ChoiceBox<Integer> soBox;
    @FXML
    private Button cancelButton, exportButton;

    private MainMenuController mainController = new MainMenuController().getReference();
    private StoreOrders orders;

    private ArrayList<Integer> currentOrderNumbers;


    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }
    void noPizzaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Pizza");
        alert.setContentText("No Pizza Orders Placed");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orders = mainController.getStoreOrders();
        if (orders.numberOfOrders() == 0) {
            noPizzaAlert();
            return;
        }
        currentOrderNumbers = orders.getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);
        // int currentNum = currentOrderNumbers.get(0);
        // soBox.setValue(currentNum);
        //setPrice();

        soBox.setOnAction(this::displayPizzas);
        // orderList.getItems().addAll(pizzaString);


    }


    void setPrice() {
        int orderNum = soBox.getValue();
        double taxes = 0.06625;
        double subtotal = orders.find(orderNum).total();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalText.setText(totalString);
    }

    @FXML
    void updateChoiceBox(ActionEvent event, int orderNumber) {
        soBox.getItems().removeAll(currentOrderNumbers);
        currentOrderNumbers = mainController.getStoreOrders().getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);

    }

    @FXML
    void displayPizzas(ActionEvent event) {
        if (soBox.getValue() == null || orders.numberOfOrders() == 0) {
            //noPizzaAlert();
            totalText.setText("");
            orderList.setItems(null);
            return;
        }

        orders = new MainMenuController().getReference().getStoreOrders();

        Order selectedOrder = orders.find(soBox.getValue());
        ArrayList<String> pizzas = selectedOrder.getPizzas();
        ObservableList<String> pizzaString = FXCollections.observableArrayList(pizzas);
        orderList.setItems(pizzaString);

        setPrice();
    }

    @FXML
    void emptyCancelAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Cannot Cancel, No Pizzas In Order");
        alert.showAndWait();
    }

    @FXML
    void nullAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Cannot Cancel, No Pizzas Selected");
        alert.showAndWait();
    }

    @FXML
    void cancelSuccessAlert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Successful");
        alert.setContentText("Order Successfully Cancelled");
        alert.showAndWait();
    }

    @FXML
    void notPlacedAlert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Order has not been officially placed");
        alert.showAndWait();
    }

    @FXML
    void cancelOrder(ActionEvent event) {
        if (soBox.getValue() == null) {
            nullAlert(event);
            return;
        }
        ArrayList<Integer> ordersPlaced = mainController.getReference().getOrdersPlaced();
        if(!contains(ordersPlaced, soBox.getValue())){
            notPlacedAlert(event);
            return;
        }

        new MainMenuController();
        orders = mainController.getStoreOrders();
        int currentNumb = soBox.getValue();
        ArrayList<String> pizzaList = orders.find(currentNumb).getPizzas();
        if (pizzaList.isEmpty()) {
            emptyCancelAlert(event);
            return;
        }
        boolean removeMe = orders.removeOrder(soBox.getValue());
        removeOrderPlaced(currentNumb);
        updateChoiceBox(event, currentNumb);
        cancelSuccessAlert(event);
        displayPizzas(event);


    }
    private boolean contains(ArrayList<Integer> list, int orderNumber){
        for(int i =0; i<list.size(); i++){
            if(list.get(i) == orderNumber){
                return true;
            }
        }
        return false;
    }

    private void removeOrderPlaced(int orderNumber){
        ArrayList<Integer> ordersPlaced = mainController.getReference().getOrdersPlaced();
        for(int i =0; i<ordersPlaced.size(); i++){
            if(ordersPlaced.get(i) == orderNumber){
                ordersPlaced.remove(i);
                return;
            }
        }
    }

    //For testing will delete later
    private void printPlacedCurrent(ArrayList<Integer> placed, ArrayList<Integer> current){
        System.out.println("Beginning Placed List");
        for (Integer value : placed) {
            System.out.println(value);
        }
        System.out.println("End Placed List");
        System.out.println("Beginning Current List");
        for (Integer integer : current) {
            System.out.println(integer);
        }
        System.out.println("End Current List");
    }



    private boolean allOrdersPlaced(StoreOrders orders){
        orders = mainController.getStoreOrders();
        ArrayList<Integer> ordersPlaced = mainController.getReference().getOrdersPlaced();
        currentOrderNumbers = mainController.getStoreOrders().getOrderNumbers();

        int index = currentOrderNumbers.get(currentOrderNumbers.size()-1);
        return orders.find(index).getPizzas().isEmpty();

    }
    @FXML
    void exportErrorAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Error");
        alert.setContentText("Could Not Export");
        alert.showAndWait();
    }

    @FXML
    void exportSuccessAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Successful");
        alert.setContentText("Successfully Exported Store Orders");
        alert.showAndWait();
    }

    @FXML
    void orderNotPlacedExportAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Error");
        alert.setContentText("Last Order Has Not Been Officially Placed");
        alert.showAndWait();
    }
    @FXML
    void exportToFile(ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        if(!allOrdersPlaced(orders)){
            orderNotPlacedExportAlert(event); //check if we have a order that has pizzas but not been officially "placed"
            return;
        }

        boolean expSuccess = orders.export(stage);

        if(!expSuccess){
            exportErrorAlert(event);
            return;
        }
        exportSuccessAlert(event);

    }

}