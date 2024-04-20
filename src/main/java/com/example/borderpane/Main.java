package com.example.borderpane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Crear un ImageView y cargar la imagen
        ImageView imageView = new ImageView(new Image("IconLifeGame.png"));

        // Crear un GridPane y agregar el ImageView
        GridPane gridPane = new GridPane();
        gridPane.add(imageView, 0, 0);
        gridPane.setAlignment(Pos.CENTER);

        // Crear la escena y mostrarla
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Listener para cambiar el tamaño de la imagen cuando cambia el tamaño de la ventana
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldWidth, Number newWidth) {
                double ancho = (double) newWidth;
                double factorEscala = ancho / imageView.getImage().getWidth();
                double nuevoAlto = imageView.getImage().getHeight() * factorEscala;
                imageView.setFitWidth(ancho);
                imageView.setFitHeight(nuevoAlto);
            }
        });

        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldHeight, Number newHeight) {
                double alto = (double) newHeight;
                double factorEscala = alto / imageView.getImage().getHeight();
                double nuevoAncho = imageView.getImage().getWidth() * factorEscala;
                imageView.setFitWidth(nuevoAncho);
                imageView.setFitHeight(alto);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
