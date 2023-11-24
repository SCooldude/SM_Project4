package com.example.sm_project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreOrderController {

    private MainMenuController mainMenuController;

    @FXML
    private ListView<String> orderList;
    @FXML
    private TextField totalText;
    @FXML
    private ChoiceBox<Integer> soBox;
    @FXML
    private Button cancelButton, exportButton;

    private StoreOrders orders;

    private ArrayList<Integer> currentOrderNumbers;


    public void setMainController(MainMenuController controller) {
        mainMenuController = controller;
    }
}