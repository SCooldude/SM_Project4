package com.example.sm_project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainMenuController {
    private static final StoreOrders orders = new StoreOrders();
    private static final ArrayList<Integer> ordersPlaced = new ArrayList<>();

    @FXML
    protected void handleBuildYourOwn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BuildYourOwnPizza.fxml"));

        Parent buildYourOwnRoot = loader.load();
        Scene buildYourOwnScene = new Scene(buildYourOwnRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customize Your Pizza");

        stage.setScene(buildYourOwnScene);
        stage.show();
        BuildYourOwnPizzaController byoController = loader.getController();
        byoController.setMainController(this);
    }

    @FXML
    protected void handleOrderSpecialtyPizzas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpecialtyPizza.fxml"));

        Parent specialtyPizzaRoot = loader.load();
        Scene specialtyPizzaScene = new Scene(specialtyPizzaRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Specialty Pizzas");

        stage.setScene(specialtyPizzaScene);
        stage.show();
        SpecialtyPizzasController specialtyPizzaController = loader.getController();
        specialtyPizzaController.setMainController(this);
    }

    @FXML
    protected void handleCurrentOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrder.fxml"));

        Parent currentOrderRoot = loader.load();
        Scene currentOrderScene = new Scene(currentOrderRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Order Detail");

        stage.setScene(currentOrderScene);
        stage.show();

        CurrentOrderController currentOrderController = loader.getController();
        currentOrderController.setMainController(this);
    }
    @FXML
    protected void handleStoreOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrder.fxml"));

        Parent storeOrdersRoot = loader.load();
        Scene storeOrdersScene = new Scene(storeOrdersRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Store Orders");

        stage.setScene(storeOrdersScene);
        stage.show();

        StoreOrderController storeOrdersController = loader.getController();

        storeOrdersController.setMainController(this);
    }

    public StoreOrders getStoreOrders() {
        return orders;
    }

    public MainMenuController getReference(){
        return this;
    }

    public ArrayList<Integer> getOrdersPlaced(){ return ordersPlaced;}

    public void setMainApplication(HelloApplication mainApplication) {
    }
}
