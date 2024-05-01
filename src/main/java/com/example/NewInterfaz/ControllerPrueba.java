package com.example.NewInterfaz;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.concurrent.atomic.AtomicReference;

public class ControllerPrueba {
    @FXML
    private GridPane baseGridPane, centralGridPane, tableroJuego;

    @FXML
    public void initialize() {
        Game game = new Game(tableroJuego, "Coral");
    }
}
