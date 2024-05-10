package com.example.NewInterfaz;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Celda extends StackPane {
    private int x, y;
    private Integer tipo = 0;
    private int id;
    private boolean ocupado;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.ocupado = false;
        setPickOnBounds(false);
    }



    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
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