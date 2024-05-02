package com.example.NewInterfaz;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class ControllerTableroPropiedades {

    @FXML
    private Slider altoSlider;

    @FXML
    private Text altoText;

    @FXML
    private Slider anchoSlider;

    @FXML
    private Text anchoText;

    @FXML
    private Button buttonIniciarPartida;

    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAlto = new SimpleIntegerProperty(0);

    @FXML
    void iniciarPartida(MouseEvent event) {
        DatosCompartidos.setAltoMatriz(String.valueOf((int)altoSlider.getValue()));
        DatosCompartidos.setAnchoMatriz(String.valueOf((int)anchoSlider.getValue()));
        Platform.exit();
    }

    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }

    @FXML
    public void initialize(){
        initializeBindingSliders(anchoSlider,anchoText, medidaAncho);
        initializeBindingSliders(altoSlider,altoText, medidaAlto);
    }

}
