package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Celda extends StackPane {
    private int x, y;
    private String tipo;
    private int id;
    private boolean ocupado;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.tipo = "";
        this.ocupado = false;
        setPickOnBounds(false);
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
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