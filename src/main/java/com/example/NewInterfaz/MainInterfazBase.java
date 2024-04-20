package com.example.NewInterfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainInterfazBase extends Application {
    /*@Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("MenuInicial.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("¡Bienvenido!");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }*/

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("InterfazBase.fxml");
        //URL fxmlUrl = getClass().getResource("InterfazBaseReescalada.fxml");
        //URL fxmlUrl = getClass().getResource("InterfazBasePantallaCompleta.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("¡Bienvenido!");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitHint("");
        primaryStage.setMaximized(true); // Pantalla completa
        primaryStage.setResizable(false); // Evitar que la ventana sea redimensionable
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}