package com.example.eljuegodelavida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("hello-view.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("Stage1");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}