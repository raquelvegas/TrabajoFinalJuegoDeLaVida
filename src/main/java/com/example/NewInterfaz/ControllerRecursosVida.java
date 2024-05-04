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
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class ControllerRecursosVida {

    @FXML
    private Slider aguaVidaSlider, bibliotecaVidaSlider, comidaVidaSlider, montanaVidaSlider,
            pozoVidaSlider, tesoroVidaSlider;
    @FXML
    private Text aguaVidaText, bibliotecaVidaText, comidaVidaText, montanaVidaText,
            pozoVidaText, tesoroVidaText;
    @FXML
    private Button buttonSiguiente;
    private Stage primaryStage; // Referencia al Stage principal

    protected IntegerProperty medidaAguaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoVida = new SimpleIntegerProperty(0);

    private static final Logger log = LogManager.getLogger(ControllerRecursosVida.class);


    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }


    @FXML
    void next(MouseEvent event) throws IOException {
        DatosCompartidos.setAguaVida(String.valueOf((int)aguaVidaSlider.getValue()));
        DatosCompartidos.setComidaVida(String.valueOf((int)comidaVidaSlider.getValue()));
        DatosCompartidos.setMontanaVida(String.valueOf((int)montanaVidaSlider.getValue()));
        DatosCompartidos.setBibliotecaVida(String.valueOf((int)bibliotecaVidaSlider.getValue()));
        DatosCompartidos.setTesoroVida(String.valueOf((int)tesoroVidaSlider.getValue()));
        DatosCompartidos.setPozoVida(String.valueOf((int)pozoVidaSlider.getValue()));

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazRecursosEfectos.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();

        log.info("Parametrización del tiempo de vida de los recursos correcta");
    }

    @FXML
    public void initialize(){
        initializeBindingSliders(aguaVidaSlider,aguaVidaText, medidaAguaVida);
        initializeBindingSliders(comidaVidaSlider,comidaVidaText, medidaComidaVida);
        initializeBindingSliders(montanaVidaSlider,montanaVidaText, medidaMontanaVida);
        initializeBindingSliders(bibliotecaVidaSlider,bibliotecaVidaText, medidaBibliotecaVida);
        initializeBindingSliders(tesoroVidaSlider,tesoroVidaText, medidaTesoroVida);
        initializeBindingSliders(pozoVidaSlider,pozoVidaText, medidaPozoVida);
    }


}
