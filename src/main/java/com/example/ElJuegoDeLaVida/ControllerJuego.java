package com.example.ElJuegoDeLaVida;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class ControllerJuego {
    @FXML
    GridPane tableroJuego;

    public void initialize(){
        Juego game = new Juego(tableroJuego, "Coral");
    }
}
