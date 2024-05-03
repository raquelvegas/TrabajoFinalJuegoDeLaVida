package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.scene.layout.StackPane;

public class Square extends StackPane {

    private int x,y;
    private boolean occupied;
    private int ID;
    private ListaSimple<Individuo> individuos;
    private ListaSimple<Recurso> recursos;

    public Square(int x, int y){
        this.individuos = new ListaSimple<>();
        this.recursos = new ListaSimple<>();
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    @Override
    public String toString() {
        String status;
        if(this.occupied) status = "Occupied";
        else status = "Not occupied";
//        return "Square" + this.x + this.y + " - " + status;
        return "Square";
    }

    public void setID(int ID) {
        this.ID = ID;
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


}
