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
    private Slider aguaVidaSlider;

    @FXML
    private Text aguaVidaText;

    @FXML
    private Text bibliotecaVidaText;

    @FXML
    private Slider bibliotecaVidaSlider;

    @FXML
    private Button buttonAnterior;

    @FXML
    private Button buttonSiguiente;

    @FXML
    private Slider comidaVidaSlider;

    @FXML
    private Text comidaVidaText;

    @FXML
    private Slider montanaVidaSlider;

    @FXML
    private Text montanaVidaText;

    @FXML
    private Slider pozoVidaSlider;

    @FXML
    private Text pozoVidaText;

    @FXML
    private Slider tesoroVidaSlider;

    @FXML
    private Text tesoroVidaText;

    @FXML
    void getAguaVida(MouseEvent event) {
        aguaVidaText.setText(String.valueOf((int)aguaVidaSlider.getValue()));
    }

    @FXML
    void getBibliotecaVida(MouseEvent event) {
        bibliotecaVidaText.setText(String.valueOf((int)bibliotecaVidaSlider.getValue()));
    }

    @FXML
    void getComidaVida(MouseEvent event) {
        comidaVidaText.setText(String.valueOf((int)comidaVidaSlider.getValue()));
    }

    @FXML
    void getMontanaVida(MouseEvent event) {
        montanaVidaText.setText(String.valueOf((int)montanaVidaSlider.getValue()));
    }

    @FXML
    void getPozoVida(MouseEvent event) {
        pozoVidaText.setText(String.valueOf((int)pozoVidaSlider.getValue()));
    }

    @FXML
    void getTesoroVida(MouseEvent event) {
        tesoroVidaText.setText(String.valueOf((int)tesoroVidaSlider.getValue()));
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        DatosCompartidos.setAguaVida(String.valueOf((int)aguaVidaSlider.getValue()));
        DatosCompartidos.setComidaVida(String.valueOf((int)comidaVidaSlider.getValue()));
        DatosCompartidos.setMontanaVida(String.valueOf((int)montanaVidaSlider.getValue()));
        DatosCompartidos.setTesoroVida(String.valueOf((int)tesoroVidaSlider.getValue()));
        DatosCompartidos.setBibliotecaVida(String.valueOf((int)bibliotecaVidaSlider.getValue()));
        DatosCompartidos.setPozoVida(String.valueOf((int)pozoVidaSlider.getValue()));
        Stage stage = (Stage) buttonAnterior.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosIndividuo.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú Parámetros");
        stage.setScene(new Scene(root));
    }

    @FXML
    void goNext(MouseEvent event) throws IOException {
        DatosCompartidos.setAguaVida(String.valueOf((int)aguaVidaSlider.getValue()));
        DatosCompartidos.setComidaVida(String.valueOf((int)comidaVidaSlider.getValue()));
        DatosCompartidos.setMontanaVida(String.valueOf((int)montanaVidaSlider.getValue()));
        DatosCompartidos.setTesoroVida(String.valueOf((int)tesoroVidaSlider.getValue()));
        DatosCompartidos.setBibliotecaVida(String.valueOf((int)bibliotecaVidaSlider.getValue()));
        DatosCompartidos.setPozoVida(String.valueOf((int)pozoVidaSlider.getValue()));
        Stage stage = (Stage) buttonSiguiente.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosRecursosEfectos.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú parámetros");
        stage.setScene(new Scene(root));
    }

    public void initialize() {
        aguaVidaText.setText(DatosCompartidos.getAguaVida());
        aguaVidaSlider.setValue(Double.parseDouble(aguaVidaText.getText()));
        comidaVidaText.setText(DatosCompartidos.getComidaVida());
        comidaVidaSlider.setValue(Double.parseDouble(comidaVidaText.getText()));
        montanaVidaText.setText(DatosCompartidos.getMontanaVida());
        montanaVidaSlider.setValue(Double.parseDouble(montanaVidaText.getText()));
        tesoroVidaText.setText(DatosCompartidos.getTesoroVida());
        tesoroVidaSlider.setValue(Double.parseDouble(tesoroVidaText.getText()));
        bibliotecaVidaText.setText(DatosCompartidos.getBibliotecaVida());
        bibliotecaVidaSlider.setValue(Double.parseDouble(bibliotecaVidaText.getText()));
        pozoVidaText.setText(DatosCompartidos.getPozoVida());
        pozoVidaSlider.setValue(Double.parseDouble(pozoVidaText.getText()));
    }
}
