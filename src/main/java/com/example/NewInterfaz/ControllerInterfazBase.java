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
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.io.File;

public class ControllerInterfazBase {

    protected IntegerProperty medida = new SimpleIntegerProperty(0);

    //Imágenes y Media
    @FXML
    private ImageView buttonNuevaPartida, buttonCargarPartida, //Nueva partida o Cargar
            imageViewFondo, imageViewPackman, imageViewCuadroTexto, imageViewLogo, imageViewCuadroAjustes, buttonComoJugar, //Aspecto General
            imageViewVivos, imageViewMuertos, //Recuento Vivos y Muertos
            buttonViewPause, buttonViewPlay, buttonViewSonidoON, buttonViewSonidoOF, buttonviewStop, buttonViewVelocidad, //Controles Juego
            imageViewRecursosPropiedades, imageViewTableroPropiedades, imageViewUserPropiedades; //Propiedades
    @FXML
    MediaPlayer mediaPlayer;
    @FXML
    private Text volText, //Controles Juego
            altoText, anchoText, //Ajustes Tablero
            vidaUserText, probReproduccionText, probClonacionText; //Ajustes User
    @FXML
    private ToggleGroup TemaTablero;
    @FXML
    private Slider altoSlider, anchoSlider, //Ajustes Tablero
            vidaUserSlider, probReproduccionSlider, probClonacionSlider; //Ajustes User
    @FXML
    private RadioButton buttonAgua, buttonAire, buttonFuego, buttonTierra; //Ajustes Tablero
    @FXML
    private AnchorPane tableroPane, initPane, playingPane, userPane, newCargarPane;
    @FXML
    private Rectangle opacacityRectangle;

    @FXML
    void iniciarNuevaPartida(MouseEvent event) {
        opacacityRectangle.setVisible(false);
        newCargarPane.setVisible(false);
        initializeAudio();
        // Cargar JSON con valores predeterminados
    }

    @FXML
    void cargarPartida(MouseEvent event) {
        opacacityRectangle.setVisible(false);
        newCargarPane.setVisible(false);
        initializeAudio();
        // Cargar JSON con partida anterior
    }

    @FXML
    void getRecursosPane(MouseEvent event) {

    }

    @FXML
    void getTableroPane(MouseEvent event) {
        clearPanes();
        tableroPane.setVisible(true);
    }

    @FXML
    void getUserPane(MouseEvent event) {
        clearPanes();
        userPane.setVisible(true);
    }

    @FXML
    void musicPause(MouseEvent event) {
        if(DatosCompartidos.isPlayreproductor()){
            mediaPlayer.pause();
            DatosCompartidos.setPlayreproductor(false);
            buttonViewSonidoOF.setVisible(true);
            volText.setText("Vol. OFF");
        } else {
            mediaPlayer.play();
            DatosCompartidos.setPlayreproductor(true);
            buttonViewSonidoOF.setVisible(false);
            volText.setText("Vol. ON");
        }

    }

    @FXML
    void playGame(MouseEvent event) {
        clearPanes();
        playingPane.setVisible(true);
    }

    @FXML
    void playPause(MouseEvent event){
        clearPanes();
        initPane.setVisible(true);
    }

    protected void insertImage(ImageView imageView, String resourceName) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(resourceName));
        imageView.setImage(image);
    }

    protected void insertSong(String resourceName) {
        String path = getClass().getClassLoader().getResource(resourceName).toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }

    protected void initializeMedia(){
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
        insertImage(imageViewTableroPropiedades,"tablero.png");
        insertImage(imageViewUserPropiedades,"user.png");
        insertImage(imageViewRecursosPropiedades,"recursos.png");
        insertImage(buttonComoJugar,"ComoJugar.png");
        insertImage(buttonCargarPartida, "cargarPartida.png");
        insertImage(buttonNuevaPartida,"nuevaPartida.png");
    }

    protected void initializeAudio(){
        insertSong("LaBamba.mp3");
    }

    protected void initializeBindingSliders(Slider slider, Text text){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }

    protected void clearPanes(){
        initPane.setVisible(false);
        playingPane.setVisible(false);
        tableroPane.setVisible(false);
        userPane.setVisible(false);
    }


    public void initialize() {
        opacacityRectangle.setVisible(true);
        newCargarPane.setVisible(true);
        clearPanes();
        initPane.setVisible(true);
        initializeMedia();
        initializeBindingSliders(altoSlider,altoText);
        initializeBindingSliders(anchoSlider,anchoText);
        initializeBindingSliders(vidaUserSlider,vidaUserText);
        initializeBindingSliders(probReproduccionSlider,probReproduccionText);
        initializeBindingSliders(probClonacionSlider,probClonacionText);
    }

}
