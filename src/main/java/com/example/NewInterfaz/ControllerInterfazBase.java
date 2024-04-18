package com.example.NewInterfaz;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ControllerInterfazBase {

    @FXML
    private ImageView imageViewFondo;
    @FXML
    private ImageView imageViewPackman;
    @FXML
    private ImageView imageViewCuadroTexto;
    @FXML
    private ImageView imageViewLogo;
    @FXML
    private ImageView imageViewVivos;
    @FXML
    private ImageView imageViewMuertos;
    @FXML
    private ImageView buttonViewPause;
    @FXML
    private ImageView buttonViewPlay;
    @FXML
    private ImageView buttonViewSonidoON;
    @FXML
    private ImageView buttonViewSonidoOF;
    @FXML
    private ImageView buttonviewStop;
    @FXML
    private ImageView buttonViewVelocidad;


    @FXML
    void playPause(MouseEvent event) {
        String fileName = "file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/La Bamba.mp3";
        String path = getClass().getResource(fileName).getPath();
        Media media = new Media(new File(path).toURI().toString());
        buttonViewSonidoON = new MediaPlayer(media);
        buttonViewSonidoON.setCycleCount(MediaPlayer.INDEFINITE);
        buttonViewSonidoON.play();

    }
    }

    public void initialize() {
        Image imageLogo = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/IconLifeGame.png");
        imageViewLogo.setImage(imageLogo);
        Image imagePacman = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/pacman.png");
        imageViewPackman.setImage(imagePacman);
        Image imageVivos = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/vivos.png");
        imageViewVivos.setImage(imageVivos);
        Image imageMuertos = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/muertos.png");
        imageViewMuertos.setImage(imageMuertos);
        Image imageFondo = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/fondo.png");
        imageViewFondo.setImage(imageFondo);
        Image buttonVelocidad = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/Velocidad.png");
        buttonViewVelocidad.setImage(buttonVelocidad);
        Image buttonPlay = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/Play.png");
        buttonViewPlay.setImage(buttonPlay);
        Image buttonPause = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/Pausa.png");
        buttonViewPause.setImage(buttonPause);
        Image buttonStop = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/Stop.png");
        buttonviewStop.setImage(buttonStop);
        Image buttonSoundON = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/VolumenON.png");
        buttonViewSonidoON.setImage(buttonSoundON);
        Image cuadroTexto = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/Cuadro texto.png");
        imageViewCuadroTexto.setImage(cuadroTexto);
        Image buttonSoundOF = new Image("file:///C:/Users/Raquel/OneDrive%20-%20Universidad%20de%20Alcala/Escritorio/TrabajoFinal/TrabajoFinalJuegoDeLaVida/src/main/java/com/example/NewInterfaz/Images/VolumenOF.png");
        buttonViewSonidoOF.setImage(buttonSoundOF);

    }

}
