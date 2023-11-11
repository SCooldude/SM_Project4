package com.example.sm_project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Stage secondStage = new Stage();
        FXMLLoader secondLoader = new FXMLLoader(HelloApplication.class.getResource("CurrentOrder.fxml"));

        Scene secondScene = new Scene(fxmlLoader.load(), 320, 240);
        secondStage.setTitle("Current Orders");
        secondStage.setScene(secondScene);
        secondStage.show();


    }



    public static void main(String[] args) {
        launch();
    }


}

