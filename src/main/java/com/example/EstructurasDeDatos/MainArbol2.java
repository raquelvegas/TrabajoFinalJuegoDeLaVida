package com.example.EstructurasDeDatos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.ElementoArbol;

public class MainArbol2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Inicializa un ArbolBinario con algunos nodos de ejemplo
        ArbolBinario<Integer> arbol = new ArbolBinario<>(1);
        ElementoArbol<Integer> padre = new ElementoArbol<>(2);
        ElementoArbol<Integer> madre = new ElementoArbol<>(3);
        arbol.getRaiz().setNodoIzq(madre);
        arbol.getRaiz().setNodoDch(padre);
        padre.setNodoIzq(new ElementoArbol<>(4));
        padre.setNodoDch(new ElementoArbol<>(5));

        //Creamos el Pane y pintamos el Árbol
        Pane pane = new Pane();
        drawTree(pane, arbol.getRaiz(), 400, 50, 400);

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Árbol Genealógico");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree(Pane pane, ElementoArbol<Integer> root, double x, double y, double width) {
        if (root != null) {
            // Dibujar el nodo como un círculo y texto
            Circle circle = new Circle(x, y, 20);
            circle.setFill(Color.LIGHTBLUE);
            circle.setStroke(Color.BLACK);
            pane.getChildren().add(circle);

            Text text = new Text(root.getDato().toString());
            double textWidth = text.getLayoutBounds().getWidth();
            double textHeight = text.getLayoutBounds().getHeight();

            // Centrar el texto dentro del círculo
            text.setX(x - textWidth / 2);
            text.setY(y + textHeight / 4);
            pane.getChildren().add(text);

            // Calcular posiciones para los hijos
            double childY = y + 50; // Distancia fija hacia abajo
            double horizontalGap = width / 2; // Espacio horizontal entre los nodos hijos
            double circleRadius = 20; // Radio del círculo del nodo padre

            if (root.getNodoIzq() != null) {
                double childX = x - horizontalGap; // Posición a la izquierda
                double angle = Math.atan2(childY - y, childX - x);
                double startX = x + circleRadius * Math.cos(angle);
                double startY = y + circleRadius * Math.sin(angle);

                //Dibuja la línea
                Line line = new Line(startX, startY, childX, childY);
                pane.getChildren().add(line);

                //Recursividad
                drawTree(pane, root.getNodoIzq(), childX, childY, width / 2 - 10);
            }

            if (root.getNodoDch() != null) {
                double childX = x + horizontalGap; // Posición a la derecha
                double angle = Math.atan2(childY - y, childX - x);
                double startX = x + circleRadius * Math.cos(angle);
                double startY = y + circleRadius * Math.sin(angle);

                //Dibuja la línea
                Line line = new Line(startX, startY, childX, childY);
                pane.getChildren().add(line);

                //Recursividad
                drawTree(pane, root.getNodoDch(), childX, childY, width / 2 - 10);
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
