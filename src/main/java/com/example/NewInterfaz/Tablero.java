package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
                color = Color.web("#adf7b6");
            }
            case "Tierra" -> {
                color = Color.web("#fcf5c7");
            }
        }
        square.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public ListaSimple<Square> getSquares() {
        return squares;
    }

    public Square getSquare(int id) {
        for (Node node : tableroJuego.getChildren()) {
            if (node instanceof Square) {
                Square square = (Square) node;
                if (square.getID() == id) {
                    return square;
                }
            }
        }
        return null; // Si no se encuentra ninguna celda con el ID especificado
    }
}
