package com.example.NewInterfaz;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class ControllerInicioJuego {

    private Stage primaryStage; // Referencia al Stage principal

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button buttonBuevaPartida;

    @FXML
    private Button buttonCargarPartida;

    @FXML
    void cargarPartida(MouseEvent event) throws IOException {
        URL fxmlUrl = getClass().getResource("InterfazTableroPropiedades.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage optionStage = new Stage();
        optionStage.setScene(new Scene(root));
        optionStage.setResizable(true); // Evitar que la ventana sea redimensionable
        optionStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        optionStage.initOwner(primaryStage);

        optionStage.initStyle(StageStyle.UNDECORATED);
        optionStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");
        optionStage.show();
    }

    @FXML
    void nuevaPartida(MouseEvent event) throws IOException {
        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazTableroPropiedades.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.initModality(Modality.APPLICATION_MODAL);
        configStage.initOwner(primaryStage);
        configStage.show();
    }
}