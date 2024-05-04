package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane {

    private int x,y;
    private boolean occupied;
    private int ID;
    private ListaSimple<Individuo> individuos;
    private ListaSimple<Recurso> recursos;
    private Celda[][] celdas;
    private Rectangle[][] rectangles;

    public Square(int x, int y){
        this.individuos = new ListaSimple<>();
        this.recursos = new ListaSimple<>();
        this.x = x;
        this.y = y;
        this.occupied = false;
        /////////////////////////////////////////////
        this.celdas = new Celda[3][2];
        this.rectangles = new Rectangle[3][2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                celdas[i][j] = new Celda();
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.RED);
                rectangles[i][j] = rectangle;
                getChildren().add(rectangle);
                StackPane.setAlignment(rectangle, javafx.geometry.Pos.CENTER);
            }
        }
    }

    public void actualizarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                Rectangle rectangle = rectangles[i][j];
                Celda celda = celdas[i][j];
                if (celda.isOcupado()) {
                    switch (celda.getTipo()) {
                        case "Individuo":
                            rectangle.setFill(Color.BLACK);
                            break;
                        case "Agua":
                            rectangle.setFill(Color.BLUE);
                            break;
                        case "Comida":
                            rectangle.setFill(Color.GREEN);
                            break;
                        case "Montaña":
                            rectangle.setFill(Color.ORANGE);
                            break;
                        case "Biblioteca":
                            rectangle.setFill(Color.BEIGE);
                            break;
                        case "Tesoro":
                            rectangle.setFill(Color.GOLD);
                            break;
                        case "Pozo":
                            rectangle.setFill(Color.RED);
                            break;
                    }
                } else {
                    rectangle.setFill(Color.TRANSPARENT);
                }
            }
        }
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


}
