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

//        makeBoard(this.tableroJuego, theme);
    }

    void makeBoard(GridPane tableroJuego, String theme) {
        int alto = Integer.parseInt(DatosCompartidos.getAltoMatriz());
        int ancho = Integer.parseInt(DatosCompartidos.getAnchoMatriz());
        int identificador=1;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Square square = new Square(i, j);
                square.setID(identificador);
                square.setPrefHeight(100);
                square.setPrefWidth(100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                // Llama a setTheme() con los parámetros apropiados
                setTheme(square, theme, i, j);
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

    private void setTheme(Square square, String theme, int i, int j) {
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

        if ((i + j) % 2 == 0) {
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    public void updateTheme(String newTheme) {
        this.theme = newTheme;
        int contador = 0;
        while (contador < squares.getNumeroElementos()){
            int i= squares.getDato(contador).getX();
            int j = squares.getDato(contador).getY();
            setTheme(squares.getDato(contador),newTheme,i,j);
            contador++;
        }
    }

    public ListaSimple<Square> getSquares() {
        return squares;
    }
}
