package com.example.NewInterfaz;

import com.google.gson.annotations.Expose;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Celda extends StackPane {

    @Expose
    private int x, y;

    @Expose
    private double tipo = 0;

    @Expose
    private int id;

    @Expose
    private boolean ocupado;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.ocupado = false;
        setPickOnBounds(false);
    }



    public void setTipo(Double tipo) {
        this.tipo = tipo;
    }

    public Double getTipo() {
        return tipo;
    }

    public void setID(int newId) {
        this.id = newId;
    }

    public int getID() {
        return id;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setColor(Color color) {
        setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}