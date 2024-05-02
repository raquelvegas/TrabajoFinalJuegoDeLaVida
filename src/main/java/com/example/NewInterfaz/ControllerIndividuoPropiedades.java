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

import java.io.IOException;
import java.net.URL;

public class ControllerIndividuoPropiedades {

    @FXML
    private Button buttonAnterior, buttonSiguiente1;
    @FXML
    private Slider vidaUserSlider, probReproduccionSlider, probClonacionSlider;
    @FXML
    private Text vidaUserText, probReproduccionText, probClonacionText;
    private Stage primaryStage; // Referencia al Stage principal
    protected IntegerProperty medidaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaProbReproduccion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaProbClonacion = new SimpleIntegerProperty(0);

    @FXML
    void next(MouseEvent event) throws IOException {
        DatosCompartidos.setVidaInicial(String.valueOf((int)vidaUserSlider.getValue()));
        DatosCompartidos.setProbReproduccion(String.valueOf((int)probReproduccionSlider.getValue()));
        DatosCompartidos.setProbClonacion(String.valueOf((int)probClonacionSlider.getValue()));

        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazRecursosVida.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();

    }

    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }

    @FXML
    public void initialize(){
        initializeBindingSliders(vidaUserSlider,vidaUserText, medidaVida);
        initializeBindingSliders(probReproduccionSlider,probReproduccionText, medidaProbReproduccion);
        initializeBindingSliders(probClonacionSlider,probClonacionText, medidaProbClonacion);
    }

}
