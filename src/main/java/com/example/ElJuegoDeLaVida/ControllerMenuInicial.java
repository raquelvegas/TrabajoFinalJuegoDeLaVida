package com.example.ElJuegoDeLaVida;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControllerMenuInicial {

    @FXML
    private Button button;

    @FXML
    void goNewPlay(MouseEvent event) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosTablero.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú Parámetros");
        stage.setScene(new Scene(root));
    }

}
