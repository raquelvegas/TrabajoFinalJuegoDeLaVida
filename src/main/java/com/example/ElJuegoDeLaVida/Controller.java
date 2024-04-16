package com.example.ElJuegoDeLaVida;

import com.example.ElJuegoDeLaVida.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    GridPane chessBoard;

    public void initialize(){

        // Themes are Coral, Dusk, Wheat, Marine, Emerald, Sandcastle

        Game game = new Game(chessBoard, "Coral");


    }
}
