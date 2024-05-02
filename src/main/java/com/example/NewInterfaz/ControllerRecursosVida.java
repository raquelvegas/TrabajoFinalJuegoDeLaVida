package com.example.NewInterfaz;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerRecursosVida {

    @FXML
    private Slider aguaVidaSlider, bibliotecaVidaSlider, comidaVidaSlider, montanaVidaSlider,
            pozoVidaSlider, tesoroVidaSlider;

    @FXML
    private Text aguaVidaText, bibliotecaVidaText, comidaVidaText, montanaVidaText,
            pozoVidaText, tesoroVidaText;

    @FXML
    private Button buttonSiguiente;

    @FXML
    void next(MouseEvent event) {
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

//        // Cargar y mostrar la ventana de configuración de propiedades del tablero
//        URL fxmlUrl = getClass().getResource("InterfazIndividuoPropiedades.fxml");
//        Parent root = FXMLLoader.load(fxmlUrl);
//
//        Stage configStage = new Stage();
//        configStage.setScene(new Scene(root));
//        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
//        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
//        configStage.initOwner(primaryStage);
//
//        configStage.initStyle(StageStyle.UNDECORATED);
//        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");
//
//        configStage.show();
    }

}
