package com.example.sm_project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class represents the controller for the BuildYourOwnPizza.fxml file.
 * It handles user interactions and updates related to building custom pizzas.
 * It utilizes JavaFX components for the GUI.
 * The class includes methods for initializing, updating pizza details, handling toppings,
 * and placing orders.
 * It communicates with the MainMenuController to access store orders.
 *
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class BuildYourOwnPizzaController {

    private MainMenuController mainMenuController;

    @FXML
    private TextField amountTextField;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private ToggleGroup two_sauce;

    @FXML
    private ComboBox<String> sizeDropdown;

    @FXML
    private ListView<String> additionalToppingsListView;

    @FXML
    private ListView<String> selectedToppingsListView;

    @FXML
    private CheckBox extraSauceCheckbox;

    @FXML
    private CheckBox extraCheeseCheckbox;

    /**
     * Sets the mainMenuController for communication with store orders.
     *
     * @param controller The MainMenuController instance.
     */
    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }

    /**
     * Handles the BackButton action, returning to the main menu.
     *
     * @param event The ActionEvent triggered by the BackButton.
     * @throws IOException If an error occurs during loading the main menu FXML.
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
     * Initializes the BuildYourOwnPizzaController.
     * Sets default values, loads pizza image, and sets event listeners.
     */
    public void initialize() {
        sizeDropdown.setValue("Small");
        String deluxeImagePath = "/com/example/sm_project4/deluxe.jpeg";
        Image deluxeImage = new Image(Objects.requireNonNull(getClass().getResource(deluxeImagePath)).toExternalForm());
        pizzaImage.setImage(deluxeImage);
        sizeDropdown.setOnAction(event -> updatePizzaPrice());

        extraSauceCheckbox.selectedProperty().addListener((sauce) -> updatePizzaPrice());
        extraCheeseCheckbox.selectedProperty().addListener((cheese) -> updatePizzaPrice());
    }

    /**
     * Displays an alert with the given message.
     *
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Handles the addition of toppings to the pizza.
     * Checks the number of toppings and updates the pizza price accordingly.
     */
    public void handleAddTopping() {
        String selectedTopping = additionalToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null && selectedToppingsListView.getItems().size() <= 6) {
            selectedToppingsListView.getItems().add(selectedTopping);
            additionalToppingsListView.getItems().remove(selectedTopping);
            updatePizzaPrice();
        } else {
            showAlert("Maximum 7 toppings allowed.");
        }
    }

    /**
     * Handles the removal of toppings from the pizza.
     * Checks the number of toppings and updates the pizza price accordingly.
     */
    public void handleRemoveTopping() {
        String selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();

        if (selectedTopping != null && selectedToppingsListView.getItems().size() >= 4) {
            additionalToppingsListView.getItems().add(selectedTopping);
            selectedToppingsListView.getItems().remove(selectedTopping);
            updatePizzaPrice();
        } else {
            showAlert("Minimum 3 toppings required.");
        }
    }

    /**
     * Updates the pizza price based on selected options and toppings.
     */
    private void updatePizzaPrice() {
        int selectedToppingsCount = selectedToppingsListView.getItems().size();
        double additionalToppingsCost = Math.max(selectedToppingsCount - 3, 0) * 1.49;
        double sizeCost = calculateSizeCost();
        double sauceCost = (extraSauceCheckbox.isSelected()) ? 1.0 : 0.0;
        double cheeseCost = (extraCheeseCheckbox.isSelected()) ? 1.0 : 0.0;
        double totalPrice = additionalToppingsCost + sizeCost + sauceCost + cheeseCost;

        amountTextField.setText(String.format("%.2f", totalPrice));
    }

    /**
     * Calculates the cost based on the selected pizza size.
     *
     * @return The cost of the selected pizza size.
     */
    private double calculateSizeCost() {
        String selectedSize = sizeDropdown.getValue();
        return switch (selectedSize) {
            case "Medium" -> 10.99;
            case "Large" -> 12.99;
            default -> 8.99;
        };
    }

    /**
     * Handles the placement of the pizza order.
     * Validates the number of toppings and updates the store order accordingly.
     */
    public void handlePlaceOrder() {
        if (selectedToppingsListView.getItems().size() <= 2) {
            showAlert("Not Enough Toppings");
            return;
        }

        Pizza pizza = PizzaMaker.createPizza("Build Your Own");

        pizza.size = Size.valueOf(sizeDropdown.getValue());
        pizza.extraCheese = extraCheeseCheckbox.isSelected();
        pizza.extraSauce = extraSauceCheckbox.isSelected();

        pizza.toppings.addAll(selectedToppingsListView.getItems());

        StoreOrders orders = mainMenuController.getStores();
        int currentOrderNumber = orders.nextAvailableNumber();

        Order currentOrder = orders.find(currentOrderNumber);
        currentOrder.addPizza(pizza);
        OrderAddedPopup();
        reset();
    }

    /**
     * Resets the pizza builder to default values.
     * Clears selected and additional toppings, resets checkboxes, and sets default size.
     */
    private void reset() {
        sizeDropdown.setValue("Small");
        extraCheeseCheckbox.setSelected(false);
        extraSauceCheckbox.setSelected(false);
        amountTextField.setText(null);
        additionalToppingsListView.getItems().clear();
        selectedToppingsListView.getItems().clear();

        additionalToppingsListView.getItems().addAll(
                "Sausage",
                "Chicken",
                "Beef",
                "Ham",
                "Pepperoni",
                "Shrimp",
                "Squid",
                "CrabMeats",
                "GreenPepper",
                "Onion",
                "Mushroom",
                "Pineapple",
                "BlackOlives"
        );

        two_sauce.selectToggle(two_sauce.getToggles().get(0));
    }

    /**
     * Displays a popup alert indicating that the pizza has been added to the order.
     */
    private void OrderAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza added to the order!");

        alert.showAndWait();
    }
}
