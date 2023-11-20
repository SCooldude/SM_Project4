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
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.setMainApplication(this);

        stage.setTitle("Main-Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void openWindow(String fxmlFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFileName));
            Scene scene = new Scene(loader.load(), 400, 300);

            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
