package com.example.ElJuegoDeLaVida;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControllerMenuPrincipal {

    @FXML
    private Button button;

    @FXML
    private Text text;

    @FXML
    private Slider slider;


    @FXML
    void getValor(MouseEvent event) {
        int valor = (int) slider.getValue();
        text.setText(String.valueOf(valor));
    }

    @FXML
    void goNext(MouseEvent event) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuSecundario.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Stage2");
        stage.setScene(new Scene(root));
    }

}
