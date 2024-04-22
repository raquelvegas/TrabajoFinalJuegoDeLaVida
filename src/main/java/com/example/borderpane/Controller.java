package com.example.borderpane;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private ImageView buttonComoJugar;

    @FXML
    private ImageView imageViewLogo;

    @FXML
    private ImageView imageViewLogo1;

    @FXML
    private ImageView imageViewLogo2;

    @FXML
    private void initialize() {
        // Listener para cambiar el tamaño de las imágenes cuando cambia el tamaño del contenedor padre
        imageViewLogo.fitWidthProperty().bind(buttonComoJugar.fitWidthProperty());
        imageViewLogo.fitHeightProperty().bind(buttonComoJugar.fitHeightProperty());

        imageViewLogo1.fitWidthProperty().bind(buttonComoJugar.fitWidthProperty());
        imageViewLogo1.fitHeightProperty().bind(buttonComoJugar.fitHeightProperty());

        imageViewLogo2.fitWidthProperty().bind(buttonComoJugar.fitWidthProperty());
        imageViewLogo2.fitHeightProperty().bind(buttonComoJugar.fitHeightProperty());
    }
}
