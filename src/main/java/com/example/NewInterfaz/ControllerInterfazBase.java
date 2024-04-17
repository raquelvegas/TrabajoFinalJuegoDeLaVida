package com.example.NewInterfaz;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerInterfazBase {

    @FXML
    private ImageView imageViewFondo;
    @FXML
    private ImageView imageViewPackman;
    @FXML
    private ImageView imageViewLogo;
    @FXML
    private ImageView imageViewVivos;
    @FXML
    private ImageView imageViewMuertos;

    public void initialize() {
        Image imageLogo = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Icon3_preview_rev_1.png");
        imageViewLogo.setImage(imageLogo);
        Image imagePacman = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/pacman.png");
        imageViewPackman.setImage(imagePacman);
        Image imageVivos = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/vivos.png");
        imageViewVivos.setImage(imageVivos);
        Image imageMuertos = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/muertos (2).png");
        imageViewMuertos.setImage(imageMuertos);
        Image imageFondo = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/fondo.png");
        imageViewFondo.setImage(imageFondo);
    }

}
