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


    public void setMainMenuController(MainMenuController controller) {
        mainMenuController = controller;
    }



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

    @FXML
    private void handlePizzaSelection() {
        String selectedPizza = pizzaDropdown.getValue();
        String imagePath = "/com/example/sm_project4/" + selectedPizza.toLowerCase() + ".jpeg";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        this.image.setImage(image);
        if (Objects.equals(pizzaDropdown.getValue(), "Seafood")) {
            tomato_alfredo.setText("Alfredo");
        }
        else {
            tomato_alfredo.setText("Tomato");
        }
        updateToppingsList(selectedPizza);
    }

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

    @FXML
    public void handleAddToOrder() {

        Pizza pizza = PizzaMaker.createPizza(pizzaDropdown.getValue());
        pizza.sauce = Sauce.TOMATO;

        if (pizza.toString().equals("Seafood") ) {
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

    private void reset() {
        pizzaDropdown.setValue("Deluxe");
        RadioButton smallRadioButton = (RadioButton) size.getToggles().get(0);
        smallRadioButton.setSelected(true);
        extraSauce.setSelected(false);
        extraCheese.setSelected(false);
        updateToppingsList("Deluxe");
        updatePizzaPrice();
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

    private void orderAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza added to the order!");

        alert.showAndWait();
    }
}
