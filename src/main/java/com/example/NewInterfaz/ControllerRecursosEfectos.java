package com.example.NewInterfaz;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerRecursosEfectos {

    @FXML
    private Slider aguaEfectoSlider, bibliotecaEfectoSlider, comidaEfectoSlider,
            montanaEfectoSlider, tesoroEfectoSlider;
    @FXML
    private Text aguaEfectoText, bibliotecaEfectoText, comidaEfectoText,
            montanaEfectoText, tesoroEfectoText;
    @FXML
    private Button buttonSiguiente;

    protected IntegerProperty medidaAguaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoEfecto = new SimpleIntegerProperty(0);

    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }


    @FXML
    void next(MouseEvent event) {
        DatosCompartidos.setAguaEfecto(String.valueOf((int)aguaEfectoSlider.getValue()));
        DatosCompartidos.setComidaEfecto(String.valueOf((int)comidaEfectoSlider.getValue()));
        DatosCompartidos.setMontanaEfecto(String.valueOf((int)montanaEfectoSlider.getValue()));
        DatosCompartidos.setBibliotecaEfecto(String.valueOf((int)bibliotecaEfectoSlider.getValue()));
        DatosCompartidos.setTesoroEfecto(String.valueOf((int)tesoroEfectoSlider.getValue()));


        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        initializeBindingSliders(aguaEfectoSlider,aguaEfectoText, medidaAguaEfecto);
        initializeBindingSliders(comidaEfectoSlider,comidaEfectoText, medidaComidaEfecto);
        initializeBindingSliders(montanaEfectoSlider,montanaEfectoText, medidaMontanaEfecto);
        initializeBindingSliders(bibliotecaEfectoSlider,bibliotecaEfectoText, medidaBibliotecaEfecto);
        initializeBindingSliders(tesoroEfectoSlider,tesoroEfectoText, medidaTesoroEfecto);
    }

}
