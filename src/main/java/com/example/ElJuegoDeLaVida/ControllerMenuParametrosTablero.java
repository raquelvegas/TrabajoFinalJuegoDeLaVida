package com.example.ElJuegoDeLaVida;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

public class ControllerMenuParametrosTablero {

    @FXML
    private Text altoMatriz;

    @FXML
    private Slider altoSlider;

    @FXML
    private Text anchoMatriz;

    @FXML
    private Slider anchoSlider;


    @FXML
    private Button buttonSiguiente;

    @FXML
    void getAlto(MouseEvent event) {
        altoMatriz.setText(String.valueOf((int)altoSlider.getValue()));
    }

    @FXML
    void getAncho(MouseEvent event) {
        anchoMatriz.setText(String.valueOf((int)anchoSlider.getValue()));
    }

    @FXML
    void goNext(MouseEvent event) throws IOException {
        DatosCompartidos.setAltoMatriz(String.valueOf((int)altoSlider.getValue()));
        DatosCompartidos.setAnchoMatriz(String.valueOf((int)anchoSlider.getValue()));
        Stage stage = (Stage) buttonSiguiente.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosIndividuo.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú parámetros");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void initialize() {
        altoMatriz.setText(DatosCompartidos.getAltoMatriz());
        altoSlider.setValue(Double.parseDouble(altoMatriz.getText()));
        anchoMatriz.setText(DatosCompartidos.getAnchoMatriz());
        anchoSlider.setValue(Double.parseDouble(anchoMatriz.getText()));
    }
}