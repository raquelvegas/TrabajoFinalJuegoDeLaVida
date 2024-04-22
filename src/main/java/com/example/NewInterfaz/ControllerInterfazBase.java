package com.example.NewInterfaz;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.lang.constant.ConstantDesc;
import java.lang.reflect.Constructor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerInterfazBase {

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
            vidaUserText, probReproduccionText, probClonacionText, //Ajustes User
            aguaVidaText, bibliotecaVidaText, comidaVidaText, montanaVidaText, pozoVidaText, tesoroVidaText; // Vida Recursos
    @FXML
    private ToggleGroup TemaTablero;
    @FXML
    private Slider altoSlider, anchoSlider, //Ajustes Tablero
            vidaUserSlider, probReproduccionSlider, probClonacionSlider, //Ajustes User
            aguaVidaSlider, bibliotecaVidaSlider, comidaVidaSlider, montanaVidaSlider, pozoVidaSlider, tesoroVidaSlider; //Vida Recursos
    @FXML
    private RadioButton buttonAgua, buttonAire, buttonFuego, buttonTierra; //Ajustes Tablero
    @FXML
    private AnchorPane tableroPane, initPane, playingPane, userPane, recursosPane, newCargarPane;
    @FXML
    private Rectangle opacacityRectangle;
    @FXML
    private Button buttonAplicarRecursosVida, buttonResetRecursosVida, buttonAplicarUser, buttonResetUser, buttonAplicarTablero, buttonResetTablero;

    ///////////////////////////////////Variables/////////////////////////////////////////////////////

    private boolean gameOn = false;
    private boolean gameStopped = true;

    ///////////////////////////////////BindingSliders/////////////////////////////////////////////////
    protected IntegerProperty medidaAlto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAncho = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaVidaUser = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaReproduccion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaClonacion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAguaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoVida = new SimpleIntegerProperty(0);

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
    void getRecursosPane(MouseEvent event) {
        if(gameStopped) {
            clearPanes();
            recursosPane.setVisible(true);
        }
    }

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

    @FXML
    void aplicarRecursosVida(MouseEvent event){
        setSlidersValue(DatosCompartidos::setAguaVida, aguaVidaSlider);
        setSlidersValue(DatosCompartidos::setComidaVida, comidaVidaSlider);
        setSlidersValue(DatosCompartidos::setMontanaVida, montanaVidaSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaVida, bibliotecaVidaSlider);
        setSlidersValue(DatosCompartidos::setTesoroVida, tesoroVidaSlider);
        setSlidersValue(DatosCompartidos::setPozoVida, pozoVidaSlider);
    }

    @FXML
    void resetRecursosVida(MouseEvent event){
        getDatosCompartidosValue(DatosCompartidos::getAguaVida, aguaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getComidaVida, comidaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getMontanaVida, montanaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getBibliotecaVida, bibliotecaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getTesoroVida, tesoroVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getPozoVida, pozoVidaSlider);
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
        recursosPane.setVisible(false);
    }

    protected void setSlidersValue(Consumer<String> setter, Slider slider){
        setter.accept(String.valueOf((int) slider.getValue()));
    }

    private void getDatosCompartidosValue (Supplier<String> getter, Slider slider) {
        slider.setValue(Integer.parseInt(getter.get()));
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
        initializeBindingSliders(aguaVidaSlider, aguaVidaText, medidaAguaVida);
        initializeBindingSliders(comidaVidaSlider, comidaVidaText, medidaComidaVida);
        initializeBindingSliders(montanaVidaSlider, montanaVidaText, medidaMontanaVida);
        initializeBindingSliders(bibliotecaVidaSlider, bibliotecaVidaText, medidaBibliotecaVida);
        initializeBindingSliders(tesoroVidaSlider, tesoroVidaText, medidaTesoroVida);
        initializeBindingSliders(pozoVidaSlider, pozoVidaText, medidaPozoVida);
    }

}
