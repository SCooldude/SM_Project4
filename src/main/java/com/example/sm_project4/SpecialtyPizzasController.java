package com.example.sm_project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Controller class for the Specialty Pizzas view.
 * Handles user interactions and updates related to specialty pizzas.
 * Works in conjunction with JavaFX elements defined in the associated FXML file.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class SpecialtyPizzasController {

    @FXML
    private TextField tomato_alfredo;
    private MainMenuController mainMenuController;

    @FXML
    private ToggleGroup size;

    @FXML
    private CheckBox extraSauce;

    @FXML
    private CheckBox extraCheese;

    @FXML
    private TextField Amount;

    @FXML
    private ListView<Object> toppingsListView;

    @FXML
    private ComboBox<String> pizzaDropdown;

    @FXML
    private ImageView image;

    /**
     * Sets the reference to the main menu controller.
     * @param controller The instance of the main menu controller.
     */
    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }

    /**
     * Initializes the controller.
     * Sets default values and listeners for UI elements.
     */
    public void initialize() {
        pizzaDropdown.setValue("Deluxe");
        String deluxeImagePath = "/com/example/sm_project4/deluxe.jpeg";
        Image deluxeImage = new Image(Objects.requireNonNull(getClass().getResource(deluxeImagePath)).toExternalForm());
        image.setImage(deluxeImage);
        RadioButton smallRadioButton = (RadioButton) size.getToggles().get(0);
        smallRadioButton.setSelected(true);
        updateToppingsList("Deluxe");

        size.selectedToggleProperty().addListener((newValue) -> {
            if (newValue != null) {
                updatePizzaPrice();
            }
        });
        extraSauce.selectedProperty().addListener((sauce) -> updatePizzaPrice());
        extraCheese.selectedProperty().addListener((cheese) -> updatePizzaPrice());
    }

    /**
     * Handles the selection of a pizza from the dropdown.
     * Updates the pizza image and toppings list based on the selected pizza.
     */
    @FXML
    private void handlePizzaSelection() {
        String selectedPizza = pizzaDropdown.getValue();
        String imagePath = "/com/example/sm_project4/" + selectedPizza.toLowerCase() + ".jpeg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        this.image.setImage(image);

        tomato_alfredo.setText(Objects.equals(pizzaDropdown.getValue(), "Seafood") ? "Alfredo" : "Tomato");

        updateToppingsList(selectedPizza);
    }

    /**
     * Updates the toppings list based on the selected pizza.
     * @param selectedPizza The selected pizza type.
     */
    private void updateToppingsList(String selectedPizza) {
        ObservableList<Object> toppingsList = FXCollections.observableArrayList();

        switch (selectedPizza) {
            case "Deluxe" -> toppingsList.addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
            case "Supreme" -> toppingsList.addAll("Sausage", "Pepperoni", "Ham", "Green Pepper", "Onion", "Black Olive", "Mushroom");
            case "Pepperoni" -> toppingsList.addAll("Pepperoni");
            case "Meatzza" -> toppingsList.addAll("Sausage", "Pepperoni", "Beef", "Ham");
            default -> toppingsList.addAll("Shrimp", "Squid", "Crab Meats");
        }
        toppingsListView.setItems(toppingsList);

        updatePizzaPrice();
    }

    /**
     * Updates the pizza price based on size and toppings.
     */
    private void updatePizzaPrice() {
        double basePrice;

        switch (pizzaDropdown.getValue()) {
            case "Deluxe" -> basePrice = getSizeBasePrice(14.99);
            case "Seafood" -> basePrice = getSizeBasePrice(17.99);
            case "Meatzza" -> basePrice = getSizeBasePrice(16.99);
            case "Supreme" -> basePrice = getSizeBasePrice(15.99);
            case "Pepperoni" -> basePrice = getSizeBasePrice(10.99);
            default -> basePrice = getSizeBasePrice(0.0);
        }

        double saucePrice = extraSauce.isSelected() ? 1.0 : 0.0;
        double cheesePrice = extraCheese.isSelected() ? 1.0 : 0.0;

        double totalPrice = getSizeBasePrice(basePrice) + saucePrice + cheesePrice;
        Amount.setText(String.format("%.2f", totalPrice));
    }

    /**
     * Calculates the base price based on pizza size.
     * @param small_Price The base price for a small-sized pizza.
     * @return The calculated base price based on the selected size.
     */
    private double getSizeBasePrice(double small_Price) {
        RadioButton selectedSize = (RadioButton) size.getSelectedToggle();

        switch (selectedSize.getText()) {
            case "Small" -> {
                return small_Price;
            }
            case "Medium" -> {
                return small_Price + 1.0;
            }
            case "Large" -> {
                return small_Price + 2.0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + selectedSize.getText());
        }
    }

    /**
     * Handles adding the selected pizza to the order.
     * Creates a Pizza object based on user selections and adds it to the current order.
     */
    @FXML
    public void handleAddToOrder() {
        Pizza pizza = PizzaMaker.createPizza(pizzaDropdown.getValue());
        pizza.sauce = Sauce.TOMATO;

        if (pizza.toString().equals("Seafood")) {
            pizza.sauce = Sauce.ALFREDO;
        }

        pizza.size = Size.valueOf(((RadioButton) size.getSelectedToggle()).getText());
        pizza.extraCheese = extraCheese.isSelected();
        pizza.extraSauce = extraSauce.isSelected();

        StoreOrders orders = mainMenuController.getStores();
        int currentOrderNumber = orders.nextAvailableNumber();
        Order currentOrder = orders.find(currentOrderNumber);
        currentOrder.addPizza(pizza);

        orderAddedPopup();
        reset();
    }

    /**
     * Resets UI elements to default values.
     */
    private void reset() {
        pizzaDropdown.setValue("Deluxe");
        RadioButton smallRadioButton = (RadioButton) size.getToggles().get(0);
        smallRadioButton.setSelected(true);
        extraSauce.setSelected(false);
        extraCheese.setSelected(false);

        updateToppingsList("Deluxe");
        updatePizzaPrice();
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
     * Shows an information alert indicating that the pizza has been added to the order.
     */
    private void orderAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza added to the order!");

        alert.showAndWait();
    }
}
