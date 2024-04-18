package com.example.NewInterfaz;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Raquel\\OneDrive - Universidad de Alcala\\Escritorio\\TrabajoFinal\\TrabajoFinalJuegoDeLaVida\\com\\example\\NewInterfaz\\audio\\LaBambaReduced.mp3\n";
        Media media = new Media(new File(filePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
