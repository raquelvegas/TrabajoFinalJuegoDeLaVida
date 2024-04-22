package com.example.borderpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar la interfaz de usuario desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfazPrueba.fxml"));
        Parent root = loader.load();

        // Configurar la escena
        Scene scene = new Scene(root);

        // Establecer el título de la ventana
        primaryStage.setTitle("Mi Aplicación JavaFX");

        // Establecer la escena en la ventana principal
        primaryStage.setScene(scene);

        // Mostrar la ventana principal
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

