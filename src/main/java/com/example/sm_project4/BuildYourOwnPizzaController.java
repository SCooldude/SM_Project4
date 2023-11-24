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

public class BuildYourOwnPizzaController {

    private MainMenuController mainMenuController;

    public TextField amountTextField;
    public ImageView pizzaImage;
    public ToggleGroup two_sauce;

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
        sizeDropdown.setValue("Small");
        String deluxeImagePath = "/com/example/sm_project4/deluxe.jpeg";
        Image deluxeImage = new Image(Objects.requireNonNull(getClass().getResource(deluxeImagePath)).toExternalForm());
        pizzaImage.setImage(deluxeImage);
        sizeDropdown.setOnAction(event -> updatePizzaPrice());

        extraSauceCheckbox.selectedProperty().addListener((sauce) -> updatePizzaPrice());
        extraCheeseCheckbox.selectedProperty().addListener((cheese) -> updatePizzaPrice());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleAddTopping() {
        // Get the selected topping from the Additional Toppings ListView
        String selectedTopping = additionalToppingsListView.getSelectionModel().getSelectedItem();

        // Check if a topping is selected
        if (selectedTopping != null) {
            // Add the selected topping to the Selected Toppings ListView
            selectedToppingsListView.getItems().add(selectedTopping);

            // Remove the selected topping from the Additional Toppings ListView
            additionalToppingsListView.getItems().remove(selectedTopping);
        }
        int selectedToppingsCount = selectedToppingsListView.getItems().size();

        if (selectedToppingsCount <= 7) {
            updatePizzaPrice();
        } else {
            showAlert("Maximum 7 toppings allowed.");
        }
    }

    public void handleRemoveTopping() {
        String selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();

        // Check if a topping is selected
        if (selectedTopping != null) {
            // Add the selected topping back to the Additional Toppings ListView
            additionalToppingsListView.getItems().add(selectedTopping);
            // Remove the selected topping from the Selected Toppings ListView
            selectedToppingsListView.getItems().remove(selectedTopping);
        }
        int selectedToppingsCount = selectedToppingsListView.getItems().size();

        if (selectedToppingsCount > 3) {
            updatePizzaPrice();
        } else {
            showAlert("Minimum 3 toppings required.");
        }
    }
    private void updatePizzaPrice() {
        int selectedToppingsCount = selectedToppingsListView.getItems().size();

        // Calculate additional toppings cost
        double additionalToppingsCost = Math.max(selectedToppingsCount - 3, 0) * 1.49;

        // Calculate size cost
        double sizeCost = calculateSizeCost();

        // Calculate sauce cost
        double sauceCost = (extraSauceCheckbox.isSelected()) ? 1.0 : 0.0;

        // Calculate cheese cost
        double cheeseCost = (extraCheeseCheckbox.isSelected()) ? 1.0 : 0.0;

        // Calculate total cost
        double totalPrice = additionalToppingsCost + sizeCost + sauceCost + cheeseCost;

        amountTextField.setText(String.format("%.2f", totalPrice));
    }
    private double calculateSizeCost() {
        String selectedSize = sizeDropdown.getValue();
        return switch (selectedSize) {
            case "Medium" -> 10.99;
            case "Large" -> 12.99;
            default -> 8.99;
        };
    }

    public void handlePlaceOrder() {
        OrderAddedPopup();
        reset();
    }

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
    private void OrderAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Order");
        alert.setContentText("Pizza added to the order!");

        alert.showAndWait();
    }
}
