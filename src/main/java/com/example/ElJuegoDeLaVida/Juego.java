package com.example.ElJuegoDeLaVida;

import javafx.scene.layout.GridPane;

public class Juego {
    public static Individuo currentindividuo;
    public static TableroJuego tablero;
    private boolean game;

    public Juego(GridPane tableroJuego, String tema){
        tablero = new TableroJuego(tableroJuego, tema);
        currentindividuo = null;
        this.game = true;
    }
}
