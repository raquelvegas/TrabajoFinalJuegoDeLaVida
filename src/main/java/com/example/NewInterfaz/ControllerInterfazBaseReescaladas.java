package com.example.NewInterfaz;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerInterfazBaseReescaladas {

    ///////////////////////////////Todos los ID's//////////////////////////////////////////////////
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
    private Button buttonAplicarUser, buttonResetUser, buttonAplicarTablero, buttonResetTablero;
    @FXML
    private GridPane gridPane;
    ///////////////////////////////////Variables/////////////////////////////////////////////////////

    private boolean gameOn = false;
    private boolean gameStopped = true;

    ///////////////////////////////////BindingSliders/////////////////////////////////////////////////
    protected IntegerProperty medidaAlto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaVidaUser = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaReproduccion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaClonacion = new SimpleIntegerProperty(0);


    /////////////////////////////////////MouseEvents////////////////////////////////////////////////////

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
    void getRecursosPane(MouseEvent event) {}

    @FXML
    void getTableroPane(MouseEvent event) {
        if(gameStopped) {
            clearPanes();
            tableroPane.setVisible(true);
        }
    }

    @FXML
    void getUserPane(MouseEvent event) {
        if(gameStopped) {
            clearPanes();
            userPane.setVisible(true);
        }
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
        gameOn=true;
        gameStopped=false;
    }

    @FXML
    void playPause(MouseEvent event){
        clearPanes();
        initPane.setVisible(true);
        gameStopped=true;
    }

    @FXML
    void aplicarTablero(MouseEvent event){
        setSlidersValue(DatosCompartidos::setAltoMatriz, altoSlider);
        setSlidersValue(DatosCompartidos::setAnchoMatriz, anchoSlider);
        //Falta tema tablero
    }

    @FXML
    void resetTablero(MouseEvent event){
        getDatosCompartidosValue(DatosCompartidos::getAltoMatriz, altoSlider);
        getDatosCompartidosValue(DatosCompartidos::getAnchoMatriz, anchoSlider);
        //Falta tema tablero
    }

    @FXML
    void aplicarUser(MouseEvent event){
        setSlidersValue(DatosCompartidos::setVidaInicial, vidaUserSlider);
        setSlidersValue(DatosCompartidos::setProbReproduccion, probReproduccionSlider);
        setSlidersValue(DatosCompartidos::setProbClonacion, probClonacionSlider);
    }

    @FXML
    void resetUser(MouseEvent event) {
        getDatosCompartidosValue(DatosCompartidos::getVidaInicial, vidaUserSlider);
        getDatosCompartidosValue(DatosCompartidos::getProbReproduccion, probReproduccionSlider);
        getDatosCompartidosValue(DatosCompartidos::getProbClonacion, probClonacionSlider);
    }

    ///////////////////////////////////Métodos de apoyo///////////////////////////////////////////
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

    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }

    protected void clearPanes(){
        initPane.setVisible(false);
        playingPane.setVisible(false);
        tableroPane.setVisible(false);
        userPane.setVisible(false);
    }

    protected void setSlidersValue(Consumer<String> setter, Slider slider){
        setter.accept(String.valueOf((int) slider.getValue()));
    }

    private void getDatosCompartidosValue (Supplier<String> getter, Slider slider) {
        slider.setValue(Integer.parseInt(getter.get()));
    }

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

    private void setImage (ImageView imageView, String path, GridPane gridPane){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        imageView.setImage(image);
        preserveRadio(imageView, gridPane);
    }



    ///////////////////////////////////Initialize/////////////////////////////////////////////////
    public void initialize() {
        opacacityRectangle.setVisible(true);
        newCargarPane.setVisible(true);
        clearPanes();
        initPane.setVisible(true);
        initializeMedia();
        initializeBindingSliders(altoSlider,altoText, medidaAlto);
        initializeBindingSliders(anchoSlider,anchoText, medidaAncho);
        initializeBindingSliders(vidaUserSlider,vidaUserText, medidaVidaUser);
        initializeBindingSliders(probReproduccionSlider,probReproduccionText, medidaReproduccion);
        initializeBindingSliders(probClonacionSlider,probClonacionText, medidaClonacion);

        setImage(buttonNuevaPartida, "nuevaPartida.png", gridPane);
        setImage(buttonCargarPartida, "cargarpartida.png", gridPane);
        setImage(imageViewFondo, "fondo.png", gridPane);
        setImage(imageViewPackman, "pacman", gridPane);
        setImage(imageViewCuadroTexto, "Cuadro texto.png", gridPane);
        setImage(imageViewLogo, "IconLifeGame.png", gridPane);
        setImage(imageViewCuadroAjustes, "CuadroAjustes3.png", gridPane);
        setImage(buttonComoJugar, "ComoJugar.png", gridPane);
        setImage(imageViewVivos, "vivos.png", gridPane);
        setImage(imageViewMuertos, "muertos", gridPane);
        setImage(buttonViewPause, "Pausa.png", gridPane);
        setImage(buttonViewPlay, "Play.png", gridPane);
        setImage(buttonViewSonidoON, "VolumenON.png", gridPane);
        setImage(buttonViewSonidoOF, "VolumenOF.png", gridPane);
        setImage(buttonviewStop, "Stop.png", gridPane);
        setImage(buttonViewVelocidad, "Velocidad.png", gridPane);
        setImage(imageViewRecursosPropiedades, "recursos.png", gridPane);
        setImage(imageViewTableroPropiedades, "tablero.png", gridPane);
        setImage(imageViewUserPropiedades, "user.png", gridPane);
    }



}
