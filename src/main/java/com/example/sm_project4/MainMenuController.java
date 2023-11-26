package com.example.sm_project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {
    private static final StoreOrders orders = new StoreOrders();
    private static final ArrayList<Integer> placed_orders = new ArrayList<>();

    public ArrayList<Integer> get_placed() {
        return placed_orders;
    }
    public StoreOrders getStores() {
        return orders;
    }
    public MainMenuController get_control() {
        return this;
    }
    @FXML
    protected void Navigation(ActionEvent event, String pathway, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathway));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        switch (pathway) {
            case "BuildYourOwnPizza.fxml" -> {
                BuildYourOwnPizzaController controller = loader.getController();
                controller.setMainMenuController(this);
            }
            case "SpecialtyPizza.fxml" -> {
                SpecialtyPizzasController controller = loader.getController();
                controller.setMainMenuController(this);
            }
            case "CurrentOrder.fxml" -> {
                CurrentOrderController controller = loader.getController();
                controller.setMainMenuController(this);
            }
            case "StoreOrder.fxml" -> {
                StoreOrderController controller = loader.getController();
                controller.setMainMenuController(this);
            }
        }
    }
    @FXML
    protected void BuildYourOwn(ActionEvent event) throws IOException {
        Navigation(event, "BuildYourOwnPizza.fxml", "Customize Your Pizza");
    }
    @FXML
    protected void OrderSpecialtyPizza(ActionEvent event) throws IOException {
        Navigation(event, "SpecialtyPizza.fxml", "Specialty Pizzas");
    }
    @FXML
    protected void CurrentOrder(ActionEvent event) throws IOException {
        Navigation(event, "CurrentOrder.fxml", "Order Detail");
    }
    @FXML
    protected void StoreOrders(ActionEvent event) throws IOException {
        Navigation(event, "StoreOrder.fxml", "Store Orders");
    }
}
