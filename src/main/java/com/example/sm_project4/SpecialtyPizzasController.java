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
import java.util.ArrayList;
import java.util.Objects;

public class SpecialtyPizzasController {
    private MainMenuController mainMenuController;

    @FXML
    private ToggleGroup size;

    @FXML
    private CheckBox extra_sauce;

    @FXML
    private CheckBox extra_cheese;

    @FXML
    private TextField Amount;

    @FXML
    private ListView<Object> toppingsListView;

    @FXML
    private ComboBox<String> pizzaDropdown;

    @FXML
    private ImageView pizzaImage;

    private Pizza pizza;

    public void setMainController(MainMenuController controller) {
        mainMenuController = controller;
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene mainMenuScene = new Scene(mainMenuRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza");
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public void initialize() {
        pizzaDropdown.setValue("Deluxe");
        String deluxeImagePath = "/com/example/sm_project4/deluxe.jpeg";
        Image deluxeImage = new Image(Objects.requireNonNull(getClass().getResource(deluxeImagePath)).toExternalForm());
        pizzaImage.setImage(deluxeImage);
        RadioButton smallRadioButton = (RadioButton) size.getToggles().get(0);
        smallRadioButton.setSelected(true);
        updateToppingsList("Deluxe");

        size.selectedToggleProperty().addListener((newValue) -> {
            if (newValue != null) {
                updatePizzaPrice();
            }
        });
        extra_sauce.selectedProperty().addListener((sauce) -> updatePizzaPrice());
        extra_cheese.selectedProperty().addListener((cheese) -> updatePizzaPrice());
    }

    @FXML
    private void handlePizzaSelection() {
        String selectedPizza = pizzaDropdown.getValue();
        String imagePath = "/com/example/sm_project4/" + selectedPizza.toLowerCase() + ".jpeg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        pizzaImage.setImage(image);

        updateToppingsList(selectedPizza);
    }

    private void updateToppingsList(String selectedPizza) {
        ObservableList<Object> toppingsList = FXCollections.observableArrayList();

        switch (selectedPizza) {
            case "Deluxe" -> toppingsList.addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
            case "Supreme" -> toppingsList.addAll("Sausage", "Pepperoni", "Ham", "Green Pepper", "Onion", "Black Olive", "Mushroom");
            case "Meatzza" -> toppingsList.addAll("Sausage", "Pepperoni", "Beef", "Ham");
            case "Pepperoni" -> toppingsList.addAll("Pepperoni");
            case null, default -> toppingsList.addAll("Shrimp", "Squid", "Crab Meats");
        }
        toppingsListView.setItems(toppingsList);
        updatePizzaPrice();
    }

    private void updatePizzaPrice() {
        double basePrice;
        switch (pizzaDropdown.getValue()) {
            case "Deluxe" -> basePrice = getSizeBasePrice(14.99);
            case "Supreme" -> basePrice = getSizeBasePrice(15.99);
            case "Meatzza" -> basePrice = getSizeBasePrice(16.99);
            case "Seafood" -> basePrice = getSizeBasePrice(17.99);
            case "Pepperoni" -> basePrice = getSizeBasePrice(10.99);
            default -> basePrice = getSizeBasePrice(0.0);
        }

        double saucePrice = extra_sauce.isSelected() ? 1.0 : 0.0;
        double cheesePrice = extra_cheese.isSelected() ? 1.0 : 0.0;

        double totalPrice = getSizeBasePrice(basePrice) + saucePrice + cheesePrice;
        Amount.setText(String.format("%.2f", totalPrice));
    }

    private double getSizeBasePrice(double smallPrice) {
        RadioButton selectedSize = (RadioButton) size.getSelectedToggle();
        switch (selectedSize.getText()) {
            case "Small" -> {
                return smallPrice;
            }
            case "Medium" -> {
                return smallPrice + 2.0;
            }
            case "Large" -> {
                return smallPrice + 4.0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + selectedSize.getText());
        }
    }

    @FXML
    public void handleAddToOrder() {
        pizza.sauce = Sauce.TOMATO;
        pizza.size = Size.valueOf(((RadioButton) size.getSelectedToggle()).getText());
        pizza.extraCheese = extra_cheese.isSelected();
        pizza.extraSauce = extra_sauce.isSelected();

        StoreOrders orders = mainMenuController.getStoreOrders();
        int currentOrderNumber = orders.getAvailable_OrderNumber();

        Order currentOrder = orders.find(currentOrderNumber);
        currentOrder.addPizza(pizza);
        ArrayList<String> pizzas = currentOrder.getPizzas();
        System.out.println(pizzas);

        orderAddedPopup();
        reset();
    }

    private void reset() {
        pizzaDropdown.setValue("Deluxe");
        RadioButton smallRadioButton = (RadioButton) size.getToggles().get(0);
        smallRadioButton.setSelected(true);
        extra_sauce.setSelected(false);
        extra_cheese.setSelected(false);
        updateToppingsList("Deluxe");
        updatePizzaPrice();
    }

    private void orderAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza added to the order!");

        alert.showAndWait();
    }
}
