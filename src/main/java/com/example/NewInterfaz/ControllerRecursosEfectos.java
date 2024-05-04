package com.example.NewInterfaz;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class ControllerRecursosEfectos {

    @FXML
    private Slider aguaEfectoSlider, bibliotecaEfectoSlider, comidaEfectoSlider,
            montanaEfectoSlider, tesoroEfectoSlider;
    @FXML
    private Text aguaEfectoText, bibliotecaEfectoText, comidaEfectoText,
            montanaEfectoText, tesoroEfectoText;
    @FXML
    private Button buttonSiguiente;
    private Stage primaryStage; // Referencia al Stage principal

    protected IntegerProperty medidaAguaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroEfecto = new SimpleIntegerProperty(0);

    private static final Logger log = LogManager.getLogger(ControllerRecursosEfectos.class);

    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }



    @FXML
    void next(MouseEvent event) throws IOException {
        DatosCompartidos.setAguaEfecto(String.valueOf((int)aguaEfectoSlider.getValue()));
        DatosCompartidos.setComidaEfecto(String.valueOf((int)comidaEfectoSlider.getValue()));
        DatosCompartidos.setMontanaEfecto(String.valueOf((int)montanaEfectoSlider.getValue()));
        DatosCompartidos.setBibliotecaEfecto(String.valueOf((int)bibliotecaEfectoSlider.getValue()));
        DatosCompartidos.setTesoroEfecto(String.valueOf((int)tesoroEfectoSlider.getValue()));


        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazRecursosAparicion.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();

        log.info("Parametrización de los efectos de los recursos correcta");
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
