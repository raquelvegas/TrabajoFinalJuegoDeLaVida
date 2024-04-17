package com.example.ElJuegoDeLaVida;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TableroJuego {
    GridPane tableroJuego;
    String temaTablero;
    public ArrayList<Cuadrado> cuadrados = new ArrayList<Cuadrado>();

    public TableroJuego(GridPane tableroJuego, String temaTablero) {
        this.tableroJuego = tableroJuego;
        this.temaTablero = temaTablero;

        makeTablero(this.tableroJuego, temaTablero);
    }

    private void makeTablero(GridPane tableroJuego, String temaTablero){
        //int ancho = Integer.parseInt(DatosCompartidos.getAnchoMatriz());
        //int alto = Integer.parseInt(DatosCompartidos.getAltoMatriz());
        for(int i=0; i<25; i++){
            for(int j=0; j<25; j++){
                Cuadrado cuadrado = new Cuadrado(i,j);
                cuadrado.setNombre("Cuadrado" + i + j);
                cuadrado.setPrefHeight(10);
                cuadrado.setPrefWidth(10);
                cuadrado.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTema(cuadrado, temaTablero, i, j);
                tableroJuego.add(cuadrado,i,j,1,1);
                cuadrados.add(cuadrado);
            }
        }
        addIndividuos();
    }

    private void setTema(Cuadrado cuadrado, String temaTablero, int i, int j){
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (temaTablero){
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
        }

        if((i+j)%2==0){
            cuadrado.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            cuadrado.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void addIndividuo(){}

    private void addIndividuos(){}

}
