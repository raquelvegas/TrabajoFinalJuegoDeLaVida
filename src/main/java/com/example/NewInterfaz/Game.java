package com.example.NewInterfaz;

import javafx.scene.layout.GridPane;

public class Game {
    public static Tablero tablero;
    private boolean game;

    public Game(GridPane tablero, String theme){
        this.tablero = new Tablero(tablero, theme);
        this.game = true;
    }

}
