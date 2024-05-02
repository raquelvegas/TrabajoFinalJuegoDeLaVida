package com.example.NewInterfaz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.concurrent.atomic.AtomicReference;

public class ControllerGridPane {
    @FXML
    private ImageView logo, comoJugar, packman, cuadroTexto,
            imageViewVivos, imageViewMuertos,
            buttonViewVelocidad, buttonViewPlay,buttonViewPause, buttonviewStop, buttonViewSonidoON;
    @FXML
    private GridPane gridPaneBase, gridPaneIzda, gridPaneCentro, tableroJuego;
    @FXML
    private TabPane tabPaneParametros;


    private void preserveRadio(ImageView image, GridPane gridPane) {
        AtomicReference<Double> maxWidth = new AtomicReference<>(Double.MAX_VALUE);
        AtomicReference<Double> maxHeight = new AtomicReference<>(Double.MAX_VALUE);

        // Escucha el cambio en el tamaño del GridPane y ajusta el tamaño máximo de la imagen
        gridPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue() / gridPane.getColumnCount() - 10; // Restamos un valor para dejar espacio para el borde, margen, etc.
            maxWidth.set(Math.min(maxWidth.get(), newWidth));
            image.setFitWidth(maxWidth.get());
        });

        gridPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue() / gridPane.getRowCount() - 10; // Restamos un valor para dejar espacio para el borde, margen, etc.
            maxHeight.set(Math.min(maxHeight.get(), newHeight));
            image.setFitHeight(maxHeight.get());
        });

        // Mantenemos la relación de aspecto
        image.setPreserveRatio(true);
    }

    private void setImage (String path, ImageView imageView, GridPane gridPane){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        imageView.setImage(image);
        preserveRadio(imageView, gridPane);
    }


    @FXML
    public void initialize() {
        setImage("IconLifeGame.png", logo, gridPaneIzda);
        setImage("ComoJugar.png", comoJugar, gridPaneIzda);
        setImage("pacman.png", packman, gridPaneIzda);
        setImage("Cuadro texto.png", cuadroTexto, gridPaneIzda);
        setImage("vivos.png", imageViewVivos, gridPaneCentro);
        setImage("muertos.png", imageViewMuertos, gridPaneCentro);
        setImage("Velocidad.png", buttonViewVelocidad, gridPaneCentro);
        setImage("Play.png", buttonViewPlay, gridPaneCentro);
        setImage("Pausa.png", buttonViewPause, gridPaneCentro);
        setImage("Stop.png", buttonviewStop, gridPaneCentro);
        setImage("VolumenON.png", buttonViewSonidoON, gridPaneCentro);
        tabPaneParametros.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        Game game = new Game(tableroJuego, "Coral");
    }
}
