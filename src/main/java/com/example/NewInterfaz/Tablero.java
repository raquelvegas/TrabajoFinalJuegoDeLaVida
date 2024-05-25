package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.google.gson.annotations.Expose;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Tablero {
    GridPane tableroJuego;

    @Expose
    String theme;

    public ListaSimple<Square> squares = new ListaSimple<>(400);

    public Tablero(GridPane tableroJuego, String theme) {
        this.tableroJuego = tableroJuego;
        this.theme = theme;
    }

    void makeBoard(GridPane tableroJuego, String theme) {
        int alto = Integer.parseInt(DatosCompartidos.getAltoMatriz());
        int ancho = Integer.parseInt(DatosCompartidos.getAnchoMatriz());
        int identificador = 0;
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
        System.out.println("Alto= " + DatosCompartidos.getAltoMatriz());
        System.out.println("Ancho= " + DatosCompartidos.getAnchoMatriz());
    }

    public void setTheme(Square square, String theme) {
        Color color = null;
        switch (theme) {
            case "Fuego" -> {
                color = Color.web("#ffc09f");
            }
            case "Agua" -> {
                color = Color.web("#add8e6ff");
            }
            case "Natura" -> {
                //color = Color.web("#a2e4b8");
                color = Color.web("#c0e6b2ff");
            }
            case "Tierra" -> {
                color = Color.web("#fcf5c7");
            }
        }
        square.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public String getTheme() {
        return theme;
    }

    public void setNewTheme(String theme) {
        this.theme = theme;
    }

    public ListaSimple<Square> getSquares() {
        return squares;
    }

    public Square getSquare(int id){
        return squares.getDato(id);
    }

    public Square getSquare(int x, int y) {
        int contador = 0;
        int numElem = squares.getNumeroElementos();
        while (contador < numElem) {
            Square actual = squares.getDato(contador);
            if ((actual.getX() == x) && (actual.getY() == y)) {
                return actual;
            } else {
                contador++;
            }
        }
        return null;
    }
}
