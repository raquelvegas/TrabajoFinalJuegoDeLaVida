package com.example.NewInterfaz;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tablero {
    GridPane tableroJuego;
    String theme;
    public ArrayList<Square> squares = new ArrayList<>();

    public Tablero(GridPane tableroJuego, String theme){
        this.tableroJuego=tableroJuego;
        this.theme=theme;

        makeBoard(this.tableroJuego, theme);
    }

    private void makeBoard(GridPane tableroJuego, String theme){
        for(int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Square square = new Square(i, j);
                square.setName("Square" + i + j);
                square.setPrefHeight(100);
                square.setPrefWidth(100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                // Llama a setTheme() con los parámetros apropiados
                setTheme(square, theme, i, j);
                // Modifica la llamada al método add para agregar el tablero en la celda (0,2)
                tableroJuego.add(square, i, j); // Aquí solo agrega el square, no necesitas especificar el rowspan y colspan
                squares.add(square);
            }
        }
    }

    private void setTheme(Square square, String theme, int i, int j){
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (theme) {
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
            case "Wheat" -> {
                color1 = Color.web("#eaefce");
                color2 = Color.web("#bbbe65");
            }
            case "Marine" -> {
                color1 = Color.web("#9dacff");
                color2 = Color.web("#6f74d2");
            }
            case "Emerald" -> {
                color1 = Color.web("#adbd90");
                color2 = Color.web("#6e8f72");
            }
            case "Sandcastle" -> {
                color1 = Color.web("#e4c16f");
                color2 = Color.web("#b88b4a");
            }
        }

        if((i+j)%2==0){
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }
}
