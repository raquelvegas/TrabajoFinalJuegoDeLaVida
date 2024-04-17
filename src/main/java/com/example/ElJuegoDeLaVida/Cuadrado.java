package com.example.ElJuegoDeLaVida;

import javafx.scene.layout.StackPane;

public class Cuadrado extends StackPane {
    int x;
    int y;
    boolean ocupado;
    String nombre;

    public Cuadrado(int x, int y) {
        this.x = x;
        this.y = y;
        this.ocupado = false;
    }

    @Override
    public String toString(){
        String status;
        if(this.ocupado) status="Ocupado";
        else status="Libre";
        return "Cuadrado" + this.x + this.y + ": " + status;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
