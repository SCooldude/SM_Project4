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

/**
 * This class represents the controller for the MainMenu.fxml file.
 * It handles navigation between different views and initializes controllers accordingly.
 * The class provides methods for navigating to specific views and accessing store orders.
 * The placed_orders list keeps track of orders that have been placed.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class MainMenuController {
    private static final StoreOrders orders = new StoreOrders();
    private static final ArrayList<Integer> placed_orders = new ArrayList<>();

    /**
     * Returns the list of placed orders.
     *
     * @return The list of placed orders.
     */
    public ArrayList<Integer> get_placed() {
        return placed_orders;
    }

    /**
     * Returns the StoreOrders instance.
     *
     * @return The StoreOrders instance.
     */
    public StoreOrders getStores() {
        return orders;
    }

    /**
     * Returns the MainMenuController instance.
     *
     * @return The MainMenuController instance.
     */
    public MainMenuController get_control() {
        return this;
    }

    /**
     * Handles navigation between views based on the provided pathway.
     * Initializes controllers for specific views.
     *
     * @param event   The ActionEvent triggered by the navigation.
     * @param pathway The FXML file pathway for the target view.
     * @param title   The title for the new stage.
     * @throws IOException If an error occurs during loading the FXML file.
     */
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

    /**
     * Handles navigation to the "BuildYourOwnPizza" view.
     *
     * @param event The ActionEvent triggered by selecting the "Build Your Own" option.
     * @throws IOException If an error occurs during navigation.
     */
    @FXML
    protected void BuildYourOwn(ActionEvent event) throws IOException {
        Navigation(event, "BuildYourOwnPizza.fxml", "Customize Your Pizza");
    }

    /**
     * Handles navigation to the "SpecialtyPizza" view.
     *
     * @param event The ActionEvent triggered by selecting the "Order Specialty Pizza" option.
     * @throws IOException If an error occurs during navigation.
     */
    @FXML
    protected void OrderSpecialtyPizza(ActionEvent event) throws IOException {
        Navigation(event, "SpecialtyPizza.fxml", "Specialty Pizzas");
    }

    /**
     * Handles navigation to the "CurrentOrder" view.
     *
     * @param event The ActionEvent triggered by selecting the "Current Order" option.
     * @throws IOException If an error occurs during navigation.
     */
    @FXML
    protected void CurrentOrder(ActionEvent event) throws IOException {
        Navigation(event, "CurrentOrder.fxml", "Order Detail");
    }

    /**
     * Handles navigation to the "StoreOrder" view.
     *
     * @param event The ActionEvent triggered by selecting the "Store Orders" option.
     * @throws IOException If an error occurs during navigation.
     */
    @FXML
    protected void StoreOrders(ActionEvent event) throws IOException {
        Navigation(event, "StoreOrder.fxml", "Store Orders");
    }
}
