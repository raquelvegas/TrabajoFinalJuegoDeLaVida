package com.example.ElJuegoDeLaVida;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControllerMenuParametros {

    @FXML
    private Button button;

    @FXML
    private Text text;

    @FXML
    void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuInicial.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Stage1");
        stage.setScene(new Scene(root));
    }

}
