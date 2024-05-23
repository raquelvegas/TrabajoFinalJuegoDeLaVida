package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.NewInterfaz.Individuos.Individuo;
import com.google.gson.annotations.Expose;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Square extends StackPane {

    @Expose
    private Integer x, y;

    @Expose
    private boolean occupied;

    @Expose
    private int ID;

    @Expose
    private ListaSimple<Celda> celdas = new ListaSimple<>(6);

    @Expose
    private ListaSimple<Individuo> individuos;

    @Expose
    private ListaSimple<Recurso> recursos;
    GridPane gridPane;

    public Square(int x, int y) {
        this.individuos = new ListaSimple<>(3);
        this.recursos = new ListaSimple<>(3);
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
                celdas.add(celda);
            }
        }
        // Agrega el GridPane al StackPane (Square)
        getChildren().add(gridPane);
    }

    @Override
    public String toString() {
        String status;
        if (this.occupied) status = "Occupied";
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
        Celda celda = null;
        for (int i = 0; i < 6; i++){
            if (celdas.getDato(i).getID() == id){
                celda = celdas.getDato(i);
            }
        }
        return celda;
    }


}
