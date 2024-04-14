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

public class ControllerMenuParametrosRecursosVida {

    @FXML
    private Button buttonAnterior;

    @FXML
    private Button buttonSiguiente;

    @FXML
    private Slider probClonacionSlider;

    @FXML
    private Text probClonacionText;

    @FXML
    private Slider probReproduccionSlider;

    @FXML
    private Text probReproduccionText;

    @FXML
    private Text vidaIncialText;

    @FXML
    private Slider vidaInicialSlider;

    @FXML
    void getProbClonacion(MouseEvent event) {
        probClonacionText.setText(String.valueOf((int)probClonacionSlider.getValue()));
    }

    @FXML
    void getProbReproduccion(MouseEvent event) {
        probReproduccionText.setText(String.valueOf((int)probReproduccionSlider.getValue()));
    }

    @FXML
    void getVidaInicial(MouseEvent event) {
        vidaIncialText.setText(String.valueOf((int)vidaInicialSlider.getValue()));
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        DatosCompartidos.setProbReproduccion(String.valueOf((int)probReproduccionSlider.getValue()));
        DatosCompartidos.setProbClonacion(String.valueOf((int)probClonacionSlider.getValue()));
        DatosCompartidos.setVidaInicial(String.valueOf((int)vidaInicialSlider.getValue()));
        Stage stage = (Stage) buttonAnterior.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosTablero.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú Parámetros");
        stage.setScene(new Scene(root));
    }

    @FXML
    void goNext(MouseEvent event) throws IOException {
        DatosCompartidos.setProbReproduccion(String.valueOf((int)probReproduccionSlider.getValue()));
        DatosCompartidos.setProbClonacion(String.valueOf((int)probClonacionSlider.getValue()));
        DatosCompartidos.setVidaInicial(String.valueOf((int)vidaInicialSlider.getValue()));
        Stage stage = (Stage) buttonSiguiente.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosIndividuo.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú parámetros");
        stage.setScene(new Scene(root));
    }

    public void initialize() {
        probReproduccionText.setText(DatosCompartidos.getProbReproduccion());
        probReproduccionSlider.setValue(Double.parseDouble(probReproduccionText.getText()));
        probClonacionText.setText(DatosCompartidos.getProbClonacion());
        probClonacionSlider.setValue(Double.parseDouble(probClonacionText.getText()));
        vidaIncialText.setText(DatosCompartidos.getVidaInicial());
        vidaInicialSlider.setValue(Double.parseDouble(vidaIncialText.getText()));
    }

}
