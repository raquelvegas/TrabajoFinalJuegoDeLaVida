package com.example.ElJuegoDeLaVida;

import com.example.NewInterfaz.DatosCompartidos;
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

public class ControllerMenuParametrosRecursosEfectos {

    @FXML
    private Slider aguaEfectoSlider;

    @FXML
    private Text aguaEfectoText;

    @FXML
    private Slider bibliotecaEfectoSlider;

    @FXML
    private Text bibliotecaEfectoText;

    @FXML
    private Button buttonAnterior;

    @FXML
    private Button buttonJugar;

    @FXML
    private Slider comidaEfectoSlider;

    @FXML
    private Text comidaEfectoText;

    @FXML
    private Slider montanaEfectoSlider;

    @FXML
    private Text montanaEfectoText;

    @FXML
    private Slider pozoEfectoSlider;

    @FXML
    private Text pozoEfectoText;

    @FXML
    private Slider tesoroEfectoSlider;

    @FXML
    private Text tesoroEfectoText;

    @FXML
    void getAguaVida(MouseEvent event) {
        aguaEfectoText.setText(String.valueOf((int)aguaEfectoSlider.getValue()));
    }

    @FXML
    void getBibliotecaVida(MouseEvent event) {
        bibliotecaEfectoText.setText(String.valueOf((int)bibliotecaEfectoSlider.getValue()));
    }

    @FXML
    void getComidaVida(MouseEvent event) {
        comidaEfectoText.setText(String.valueOf((int)comidaEfectoSlider.getValue()));
    }

    @FXML
    void getMontanaVida(MouseEvent event) {
        montanaEfectoText.setText(String.valueOf((int)montanaEfectoSlider.getValue()));
    }

    @FXML
    void getPozoVida(MouseEvent event) {
        pozoEfectoText.setText(String.valueOf((int)pozoEfectoSlider.getValue()));
    }

    @FXML
    void getTesoroVida(MouseEvent event) {
       tesoroEfectoText.setText(String.valueOf((int)tesoroEfectoSlider.getValue()));
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        DatosCompartidos.setAguaEfecto(String.valueOf((int)aguaEfectoSlider.getValue()));
        DatosCompartidos.setComidaEfecto(String.valueOf((int)comidaEfectoSlider.getValue()));
        DatosCompartidos.setMontanaEfecto(String.valueOf((int)montanaEfectoSlider.getValue()));
        DatosCompartidos.setTesoroEfecto(String.valueOf((int)tesoroEfectoSlider.getValue()));
        DatosCompartidos.setBibliotecaEfecto(String.valueOf((int)bibliotecaEfectoSlider.getValue()));
        DatosCompartidos.setPozoEfecto(String.valueOf((int)pozoEfectoSlider.getValue()));
        Stage stage = (Stage) buttonAnterior.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosRecursosVida.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("Menú Parámetros");
        stage.setScene(new Scene(root));
    }

    @FXML
    void goPlay(MouseEvent event) throws IOException {
        Stage stage = (Stage) buttonAnterior.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("TableroJuego.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        stage.setTitle("El Juego de la Vida");
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");
    }

    public void initialize() {
        aguaEfectoText.setText(DatosCompartidos.getAguaEfecto());
        aguaEfectoSlider.setValue(Double.parseDouble(aguaEfectoText.getText()));
        comidaEfectoText.setText(DatosCompartidos.getComidaEfecto());
        comidaEfectoSlider.setValue(Double.parseDouble(comidaEfectoText.getText()));
        montanaEfectoText.setText(DatosCompartidos.getMontanaEfecto());
        montanaEfectoSlider.setValue(Double.parseDouble(montanaEfectoText.getText()));
        tesoroEfectoText.setText(DatosCompartidos.getTesoroEfecto());
        tesoroEfectoSlider.setValue(Double.parseDouble(tesoroEfectoText.getText()));
        bibliotecaEfectoText.setText(DatosCompartidos.getBibliotecaEfecto());
        bibliotecaEfectoSlider.setValue(Double.parseDouble(bibliotecaEfectoText.getText()));
        pozoEfectoText.setText(DatosCompartidos.getPozoEfecto());
        pozoEfectoSlider.setValue(Double.parseDouble(pozoEfectoText.getText()));
    }
}
