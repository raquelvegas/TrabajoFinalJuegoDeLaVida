package com.example.NewInterfaz;

import com.example.ElJuegoDeLaVida.GameOfLife;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.concurrent.atomic.AtomicReference;

public class ControllerGridPane {
    @FXML
    private ImageView logo, comoJugar, packman, cuadroTexto;
    @FXML
    private GridPane gridPaneBase;

    private void preserveRadio(ImageView image){
        AtomicReference<Double> maxWidth = new AtomicReference<>(Double.MAX_VALUE); // Establecemos el máximo inicialmente al máximo valor posible
        AtomicReference<Double> maxHeight = new AtomicReference<>(Double.MAX_VALUE); // Establecemos el máximo inicialmente al máximo valor posible

        // Escucha el cambio en el tamaño de la celda y ajusta el tamaño máximo
        gridPaneBase.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue() - 10; // Restamos un valor para dejar espacio para el borde, margen, etc.
            maxWidth.set(Math.min(maxWidth.get(), newWidth));
            image.setFitWidth(Math.min(image.getFitWidth(), maxWidth.get()));
        });

        gridPaneBase.heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue() - 10; // Restamos un valor para dejar espacio para el borde, margen, etc.
            maxHeight.set(Math.min(maxHeight.get(), newHeight));
            image.setFitHeight(Math.min(image.getFitHeight(), maxHeight.get()));
        });

        // Mantenemos la relación de aspecto
        image.setPreserveRatio(true);
    }

    private void setImage (String path, ImageView imageView){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        imageView.setImage(image);
    }

    public void initialize() {
        setImage("IconLifeGame.png",logo);
        setImage("ComoJugar.png",comoJugar);
        setImage("pacman.png",packman);
        setImage("Cuadro texto.png",cuadroTexto);
    }
}
