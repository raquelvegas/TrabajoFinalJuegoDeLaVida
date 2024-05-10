package com.example.NewInterfaz;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerComoJugar {

    @FXML
    private Button buttonVolver;

    @FXML
    void volver(MouseEvent event) {
        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
