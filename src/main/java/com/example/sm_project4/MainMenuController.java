package com.example.sm_project4;

import javafx.fxml.FXML;

public class MainMenuController {
    private HelloApplication mainApplication;

    @FXML
    private void handleBuildYourOwn() {
        mainApplication.openWindow("BuildYourOwnPizza.fxml", "Customize Your Pizza");
    }

    @FXML
    private void handleOrderSpecialtyPizzas() {
        mainApplication.openWindow("SpecialtyPizza.fxml", "Specialty Pizzas");
    }
    @FXML
    private void handleCurrentOrder() {
        mainApplication.openWindow("CurrentOrder.fxml", "Order Detail");
    }

    @FXML
    private void handleStoreOrders() {
        mainApplication.openWindow("StoreOrder.fxml", "Store Orders");
    }

    public void setMainApplication(HelloApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
