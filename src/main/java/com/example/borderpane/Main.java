package com.example.borderpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear una imagen y un ImageView
        Image image = new Image("IconLifeGame.png"); // Reemplaza "sample.jpg" con la ruta de tu imagen
        ImageView imageView = new ImageView(image);

        // Crear un GridPane
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: lightgray;"); // Color de fondo opcional para visualizar el GridPane

        // Ajustar la imagen al tamaño de la celda sin deformarla
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(gridPane.widthProperty());
        imageView.fitHeightProperty().bind(gridPane.heightProperty());

        // Añadir la imagen al GridPane
        gridPane.add(imageView, 0, 0);

        // Crear la escena y mostrarla
        Scene scene = new Scene(gridPane, 400, 300); // Tamaño inicial de la ventana
        primaryStage.setScene(scene);
        primaryStage.setTitle("Imagen en GridPane");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


