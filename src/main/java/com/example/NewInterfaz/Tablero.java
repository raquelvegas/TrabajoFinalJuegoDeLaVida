package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Tablero {
    GridPane tableroJuego;
    String theme;
    public ListaSimple<Square> squares = new ListaSimple<>();

    public Tablero(GridPane tableroJuego, String theme) {
        this.tableroJuego = tableroJuego;
        this.theme = theme;
    }

    void makeBoard(GridPane tableroJuego, String theme) {
        int alto = Integer.parseInt(DatosCompartidos.getAltoMatriz());
        int ancho = Integer.parseInt(DatosCompartidos.getAnchoMatriz());
        int identificador=0;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Square square = new Square(i, j);
                square.setID(identificador);
                square.setPrefHeight(100);
                square.setPrefWidth(100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                // Llama a setTheme() con los parámetros apropiados
                setTheme(square, theme);
                // Modifica la llamada al método add para agregar el tablero en la celda (0,2)
                tableroJuego.add(square, i, j); // Aquí solo agrega el square, no necesitas especificar el rowspan y colspan
                squares.add(square);
                identificador++;
            }
        }

        System.out.println("Pruebas en la clase Tablero:\n");
        System.out.println(squares.getDato(5).getID());
        System.out.println(squares.getDato(5).getX()+","+squares.getDato(5).getY());
        System.out.println("Alto= "+DatosCompartidos.getAltoMatriz());
        System.out.println("Ancho= "+DatosCompartidos.getAnchoMatriz());
    }

    private void setTheme(Square square, String theme) {
        Color color = null;
        switch (theme) {
            case "Coral" -> {
                color = Color.web("#b1e4b9");
            }
            case "Agua" -> {
                color = Color.LIGHTBLUE;
            }
            case "Wheat" -> {
                color = Color.web("#eaefce");
            }
            case "Marine" -> {
                color = Color.web("#9dacff");
            }
            case "Emerald" -> {
                color = Color.web("#adbd90");
            }
            case "Sandcastle" -> {
                color = Color.web("#e4c16f");
            }
        }
        square.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public void setTheme(String theme){
        Color color = null;
        switch (theme) {
            case "Fuego" -> {
                color = Color.web("#ffc09f");
            }
            case "Agua" -> {
                color = Color.web("#add8e6ff");
            }
            case "Natura" -> {
                color = Color.web("#adf7b6");
            }
            case "Tierra" -> {
                color = Color.web("#fcf5c7");
            }
        }
        Integer contador=0;
        while (contador<squares.getNumeroElementos()){
            squares.getDato(contador).setBackground(new Background(new BackgroundFill(color,CornerRadii.EMPTY, Insets.EMPTY)));
            contador++;
        }
    }

    public void updateTheme(String newTheme) {
        this.theme = newTheme;
        int contador = 0;
        while (contador < squares.getNumeroElementos()){
            int i= squares.getDato(contador).getX();
            int j = squares.getDato(contador).getY();
            setTheme(squares.getDato(contador),newTheme);
            contador++;
        }
    }

    public ListaSimple<Square> getSquares() {
        return squares;
    }
}
