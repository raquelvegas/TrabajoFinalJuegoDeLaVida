package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane {

    private int x,y;
    private boolean occupied;
    private int ID;
    private ListaSimple<Individuo> individuos;
    private ListaSimple<Recurso> recursos;
    GridPane gridPane;

    public Square(int x, int y){
        this.individuos = new ListaSimple<>();
        this.recursos = new ListaSimple<>();
        this.x = x;
        this.y = y;
        this.occupied = false;
        /////////////////////////////////////////////
        // Crear el GridPane
        this.gridPane = new GridPane();

        // Crear celdas del GridPane
        Integer identificador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                Celda celda = new Celda(i, j);
                celda.setPrefHeight(100);
                celda.setPrefWidth(100);
                celda.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                celda.setColor(Color.TRANSPARENT);
                celda.setID(identificador);
                identificador++;
                gridPane.add(celda, i, j);
            }
        }
        // Agrega el GridPane al StackPane (Square)
        getChildren().add(gridPane);
    }

    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
        return "Square" + this.x + this.y;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public ListaSimple<Individuo> getIndividuos() {
        return individuos;
    }

    public ListaSimple<Recurso> getRecursos() {
        return recursos;
    }

    public void setIndividuos(ListaSimple<Individuo> individuos) {
        this.individuos = individuos;
    }

    public void setRecursos(ListaSimple<Recurso> recursos) {
        this.recursos = recursos;
    }

    public Celda getCelda(int id) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Celda) {
                Celda celda = (Celda) node;
                if (celda.getID() == id) {
                    return celda;
                }
            }
        }
        return null; // Si no se encuentra ninguna celda con el ID especificado
    }

}
