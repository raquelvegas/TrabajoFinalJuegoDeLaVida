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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerRecursosAparicion {

    @FXML
    private Slider aguaAparicionSlider, bibliotecaAparicionSlider, comidaAparicionSlider,
            montanaAparicionSlider, pozoAparicionSlider, tesoroAparicionSlider;
    @FXML
    private Text aguaAparicionText, bibliotecaAparicionText, comidaAparicionText,
            montanaAparicionText, pozoAparicionText, tesoroAparicionText;
    @FXML
    private Button buttonSiguiente;

    protected IntegerProperty medidaAguaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoAparicion = new SimpleIntegerProperty(0);

    private static final Logger log = LogManager.getLogger(ControllerRecursosAparicion.class);


    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida) {
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }

    @FXML
    void next(MouseEvent event) {
        DatosCompartidos.setAguaAparicion(String.valueOf((int) aguaAparicionSlider.getValue()));
        DatosCompartidos.setComidaAparicion(String.valueOf((int) comidaAparicionSlider.getValue()));
        DatosCompartidos.setMontanaAparicion(String.valueOf((int) montanaAparicionSlider.getValue()));
        DatosCompartidos.setBibliotecaAparicion(String.valueOf((int) bibliotecaAparicionSlider.getValue()));
        DatosCompartidos.setTesoroAparicion(String.valueOf((int) tesoroAparicionSlider.getValue()));
        DatosCompartidos.setPozoAparicion(String.valueOf((int) pozoAparicionSlider.getValue()));

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        ControllerMainStage.initializeAudio();

        log.info("Parametrización de la probabilidad de aparición de los recursos correcta");
    }

    @FXML
    public void initialize(){
        initializeBindingSliders(aguaAparicionSlider,aguaAparicionText, medidaAguaAparicion);
        initializeBindingSliders(comidaAparicionSlider,comidaAparicionText, medidaComidaAparicion);
        initializeBindingSliders(montanaAparicionSlider,montanaAparicionText, medidaMontanaAparicion);
        initializeBindingSliders(bibliotecaAparicionSlider,bibliotecaAparicionText, medidaBibliotecaAparicion);
        initializeBindingSliders(tesoroAparicionSlider,tesoroAparicionText, medidaTesoroAparicion);
        initializeBindingSliders(pozoAparicionSlider,pozoAparicionText, medidaPozoAparicion);
    }

}
