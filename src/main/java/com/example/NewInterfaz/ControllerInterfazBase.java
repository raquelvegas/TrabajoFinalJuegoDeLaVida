package com.example.NewInterfaz;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


import java.io.File;

public class ControllerInterfazBase {

    protected IntegerProperty medida = new SimpleIntegerProperty(0);

    //Imágenes y Media
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
    private ImageView imageViewCuadroAjustes;
    MediaPlayer mediaPlayer;
    @FXML
    private Text volText;

    //TableroMenu
    @FXML
    private ToggleGroup TemaTablero;
    @FXML
    private Slider altoSlider;
    @FXML
    private Text altoText;
    @FXML
    private Slider anchoSlider;
    @FXML
    private Text anchoText;
    @FXML
    private RadioButton buttonAgua;
    @FXML
    private RadioButton buttonAire;
    @FXML
    private RadioButton buttonFuego;
    @FXML
    private RadioButton buttonTierra;


    @FXML
    void playPause(MouseEvent event) {
        if(DatosCompartidos.isPlayreproductor()){
            mediaPlayer.pause();
            DatosCompartidos.setPlayreproductor(false);
            buttonViewSonidoOF.setVisible(true);
            volText.setText("Vol. OFF");
        } else {
            //mediaPlayer.play();
            DatosCompartidos.setPlayreproductor(true);
            buttonViewSonidoOF.setVisible(false);
            volText.setText("Vol. ON");
        }

    }

    @FXML
    void getAltoSlider(MouseEvent event) {
        String alto = String.valueOf((int)altoSlider.getValue());
        altoText.setText(alto);
    }

    @FXML
    void getAnchoSlider(MouseEvent event) {
        String ancho = String.valueOf((int)anchoSlider.getValue());
        anchoText.setText(ancho);
    }

    @FXML
    void updateTheme(MouseEvent event) {

    }

    protected void insertImage(ImageView imageView, String resourceName) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(resourceName));
        imageView.setImage(image);
    }


    public void initialize() {
        insertImage(imageViewLogo, "IconLifeGame.png");
        insertImage(imageViewPackman, "pacman.png");
        insertImage(imageViewVivos, "vivos.png");
        insertImage(imageViewMuertos, "muertos.png");
        insertImage(imageViewFondo, "fondo.png");
        insertImage(buttonViewVelocidad, "Velocidad.png");
        insertImage(buttonViewPlay, "Play.png");
        insertImage(buttonViewPause, "Pausa.png");
        insertImage(buttonviewStop, "Stop.png");
        insertImage(buttonViewSonidoON, "VolumenON.png");
        insertImage(buttonViewSonidoOF, "VolumenOF.png");
        insertImage(imageViewCuadroTexto, "Cuadro texto.png");
        insertImage(imageViewCuadroAjustes, "CuadroAjustes3.png");

        ///music
        String path = "C:\\Users\\Raquel\\OneDrive - Universidad de Alcala\\Escritorio\\TrabajoFinal\\TrabajoFinalJuegoDeLaVida\\src\\main\\java\\com\\example\\NewInterfaz\\audio\\LaBambaReduced.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.15);
        //mediaPlayer.play();


        altoSlider.valueProperty().bindBidirectional(medida);
        altoText.textProperty().bind(medida.asString());

    }

}
