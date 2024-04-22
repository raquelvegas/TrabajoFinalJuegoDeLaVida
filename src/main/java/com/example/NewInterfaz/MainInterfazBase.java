package com.example.NewInterfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class MainInterfazBase extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("InterfazBase.fxml");
        //URL fxmlUrl = getClass().getResource("InterfazBaseReescalada.fxml");
        //URL fxmlUrl = getClass().getResource("InterfazBasePantallaCompleta.fxml");
        //URL fxmlUrl = getClass().getResource("InterfazBase.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("¡Bienvenido!");
        primaryStage.setScene(new Scene(root,1920,1080));
        //primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitHint("");
        primaryStage.setMaximized(true); // Pantalla completa
        primaryStage.setResizable(false); // Evitar que la ventana sea redimensionable
        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // Consumir el evento para evitar que la ventana se cierre directamente

            // Mostrar un diálogo de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar salida");
            alert.setHeaderText("¿Estás seguro de que quieres salir?");
            alert.setContentText("Si sales, perderás todos los cambios no guardados.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Si el usuario confirma, cierra la aplicación
                primaryStage.close();
            }
        });

        primaryStage.show();
}


    public static void main(String[] args) {
        launch();
    }
}