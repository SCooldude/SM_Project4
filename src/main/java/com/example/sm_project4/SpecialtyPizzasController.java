package com.example.sm_project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class SpecialtyPizzasController {
    public ToggleGroup size;
    public CheckBox extra_sauce;
    public CheckBox extra_cheese;
    public TextField Amount;

    @FXML
    private ListView<Object> toppingsListView;

    @FXML
    private ComboBox<String> pizzaDropdown;

    @FXML
    private ImageView pizzaImage;

    public void initialize() {
        pizzaDropdown.setValue("Deluxe");
        String deluxeImagePath = "/com/example/sm_project4/deluxe.jpeg";
        Image deluxeImage = new Image(Objects.requireNonNull(getClass().getResource(deluxeImagePath)).toExternalForm());
        pizzaImage.setImage(deluxeImage);
        RadioButton smallRadioButton = (RadioButton) size.getToggles().get(0);
        smallRadioButton.setSelected(true);
        updateToppingsList("Deluxe");

        size.selectedToggleProperty().addListener((observable,oldValue,newValue) -> {
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


    public void handleAddToOrder() {
    }
}

