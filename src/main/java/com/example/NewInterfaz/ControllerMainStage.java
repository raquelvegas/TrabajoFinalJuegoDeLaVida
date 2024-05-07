package com.example.NewInterfaz;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerMainStage {
    @FXML
    private Text vivosText, muertosText, playText, pauseText, //Información juego
            vidaUserText, probReproduccionText, probClonacionText, //Ajustes User
            aguaVidaText, bibliotecaVidaText, comidaVidaText, montanaVidaText, pozoVidaText, tesoroVidaText, // Vida Recursos
            aguaEfectoText, bibliotecaEfectoText, comidaEfectoText, montanaEfectoText, tesoroEfectoText, // Efecto Recursos
            aguaAparicionText, bibliotecaAparicionText, comidaAparicionText, montanaAparicionText, pozoAparicionText, tesoroAparicionText; // Aparicion Recursos
    @FXML
    private Slider vidaUserSlider, probReproduccionSlider, probClonacionSlider, //Ajustes User
            aguaVidaSlider, bibliotecaVidaSlider, comidaVidaSlider, montanaVidaSlider, pozoVidaSlider, tesoroVidaSlider, //Vida Recursos
            aguaEfectoSlider, bibliotecaEfectoSlider, comidaEfectoSlider, montanaEfectoSlider, tesoroEfectoSlider, //Efecto Recursos
            aguaAparicionSlider, bibliotecaAparicionSlider, comidaAparicionSlider, montanaAparicionSlider, pozoAparicionSlider, tesoroAparicionSlider; // Aparicion Recursos
    @FXML
    private Button buttonVelocidad, buttonPlay, buttonPause, buttonStop, clearTablero; //Botones tablero
    @FXML
    private RadioButton radioIndividuo, radioAgua, radioComida, radioMontana, radioBiblioteca, radioTesoro, radioPozo; //Añadir RadioButtons
    @FXML
    private ToggleGroup anadir;
    @FXML
    static MediaPlayer mediaPlayer;
    @FXML
    private BorderPane basePane;
    @FXML
    private GridPane centralGridPane, tableroJuego;
    @FXML
    private TabPane tabPaneParametros;
    @FXML
    private VBox infoVBox;
    @FXML
    private Stage stage;
    protected boolean gameStopped = true;
    @FXML
    private Tab pauseTab, individuoTab, recursosParametrosTab, aparicionTab, anadirTab;

    private Game game=null;

    private static final Logger log = LogManager.getLogger(ControllerMainStage.class);

    ///////////////////////////////////BindingSliders////////////////////////////////////////////////////////////////////////////////
    protected IntegerProperty medidaVidaUser = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaReproduccion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaClonacion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAguaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAguaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroEfecto = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAguaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoAparicion = new SimpleIntegerProperty(0);

    /////////////////////////////////////MouseEvents////////////////////////////////////////////////////

    @FXML
    void speedGame(MouseEvent event) {}
    @FXML
    void playGame(MouseEvent event) {
        DatosCompartidos.setGameIniciado(true);
        if (gameStopped) {
            gameStopped = false;

            tabPaneParametros.getSelectionModel().select(pauseTab);
            this.individuoTab.setDisable(true);
            this.recursosParametrosTab.setDisable(true);
            this.aparicionTab.setDisable(true);
            this.anadirTab.setDisable(true);
            this.pauseTab.setDisable(false);
            this.pauseText.setVisible(true);
            this.playText.setVisible(false);
            log.info("Reanudar el juego");
        }
    }
    @FXML
    void pauseGame(MouseEvent event) {
        if (!gameStopped) {
            gameStopped = true;

            this.individuoTab.setDisable(false);
            this.recursosParametrosTab.setDisable(false);
            this.aparicionTab.setDisable(false);
            this.anadirTab.setDisable(false);
            this.pauseTab.setDisable(true);
            this.pauseText.setVisible(false);
            this.playText.setVisible(true);
            log.info("Pausar el juego");
        }
    }
    @FXML
    void stopGame(MouseEvent event) {}
    @FXML
    void aplicarUser(MouseEvent event){
        setSlidersValue(DatosCompartidos::setVidaInicial, vidaUserSlider);
        setSlidersValue(DatosCompartidos::setProbReproduccion, probReproduccionSlider);
        setSlidersValue(DatosCompartidos::setProbClonacion, probClonacionSlider);
    }
    @FXML
    void resetUser(MouseEvent event) {
        getDatosCompartidosValueSlider(DatosCompartidos::getVidaInicial, vidaUserSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbReproduccion, probReproduccionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbClonacion, probClonacionSlider);
    }
    @FXML
    void aplicarRecursosVida(MouseEvent event){
        setSlidersValue(DatosCompartidos::setAguaVida, aguaVidaSlider);
        setSlidersValue(DatosCompartidos::setComidaVida, comidaVidaSlider);
        setSlidersValue(DatosCompartidos::setMontanaVida, montanaVidaSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaVida, bibliotecaVidaSlider);
        setSlidersValue(DatosCompartidos::setTesoroVida, tesoroVidaSlider);
        setSlidersValue(DatosCompartidos::setPozoVida, pozoVidaSlider);
        setSlidersValue(DatosCompartidos::setAguaEfecto, aguaEfectoSlider);
        setSlidersValue(DatosCompartidos::setComidaEfecto, comidaEfectoSlider);
        setSlidersValue(DatosCompartidos::setMontanaEfecto, montanaEfectoSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaEfecto, bibliotecaEfectoSlider);
        setSlidersValue(DatosCompartidos::setTesoroEfecto, tesoroEfectoSlider);
    }
    @FXML
    void resetRecursosVida(MouseEvent event){
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaVida, aguaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaVida, comidaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaVida, montanaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaVida, bibliotecaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroVida, tesoroVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoVida, pozoVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaEfecto, aguaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaEfecto, comidaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaEfecto, montanaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaEfecto, bibliotecaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroEfecto, tesoroEfectoSlider);
    }
    @FXML
    void aplicarAparicionRecursos(MouseEvent event) {
        setSlidersValue(DatosCompartidos::setAguaAparicion, aguaAparicionSlider);
        setSlidersValue(DatosCompartidos::setComidaAparicion, comidaAparicionSlider);
        setSlidersValue(DatosCompartidos::setMontanaAparicion, montanaAparicionSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaAparicion, bibliotecaAparicionSlider);
        setSlidersValue(DatosCompartidos::setTesoroAparicion, tesoroAparicionSlider);
        setSlidersValue(DatosCompartidos::setPozoAparicion, pozoAparicionSlider);
    }
    @FXML
    void resetAparicionRecursos(MouseEvent event) {
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaAparicion, aguaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaAparicion, comidaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaAparicion, montanaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaAparicion, bibliotecaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroAparicion, tesoroAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoAparicion, pozoAparicionSlider);
    }

    @FXML
    void setEstiloFuego(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #ffc09f;");
        infoVBox.setStyle("-fx-background-color: #ffc09f;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Fuego");
        }
        log.info("Cambio de color de visualización: Fuego");
    }
    @FXML
    void setEstiloAgua(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #add8e6ff;");
        infoVBox.setStyle("-fx-background-color: #add8e6ff;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Agua");
        }
        log.info("Cambio de color de visualización: Agua");

    }
    @FXML
    void setEstiloNatura(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #adf7b6;");
        infoVBox.setStyle("-fx-background-color: #adf7b6;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Natura");
        }
        log.info("Cambio de color de visualización: Natura");

    }

    @FXML
    void setEstiloTierra(ActionEvent event) {
        tabPaneParametros.setStyle("-fx-background-color: #fcf5c7;");
        infoVBox.setStyle("-fx-background-color: #fcf5c7;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Tierra");
        }
        log.info("Cambio de color de visualización: Tierra");

    }

    @FXML
    void anadirElemento(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();
        ToggleGroup group = selectedRadioButton.getToggleGroup();

        if (group.getSelectedToggle() != null) {
            int valor = 0;

            if (selectedRadioButton.equals(radioIndividuo)) {
                valor = 1;
            } else if (selectedRadioButton.equals(radioAgua)) {
                valor = 2;
            } else if (selectedRadioButton.equals(radioComida)) {
                valor = 3;
            } else if (selectedRadioButton.equals(radioMontana)) {
                valor = 4;
            } else if (selectedRadioButton.equals(radioBiblioteca)) {
                valor = 5;
            } else if (selectedRadioButton.equals(radioTesoro)) {
                valor = 6;
            } else if (selectedRadioButton.equals(radioPozo)) {
                valor = 7;
            }

            DatosCompartidos.setAnadir(valor);
        }
    }

    @FXML
    void clear(MouseEvent event) {
        game.clearTablero(game.getTablero());
        game.actualizarTablero(game.getTablero());
    }

    @FXML
    void play(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void pause(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void eraseUnaVezLaVida(ActionEvent event) {
        mediaPlayer.stop();
        insertSong("EraseUnaVezLaVida.mp3");
    }

    @FXML
    void laBamba(ActionEvent event) {
        mediaPlayer.stop();
        insertSong("LaBamba.mp3");
    }

    ///////////////////////////////////Métodos de apoyo///////////////////////////////////////////
    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }
    protected void setSlidersValue(Consumer<String> setter, Slider slider){
        setter.accept(String.valueOf((int) slider.getValue()));
    }
    private void getDatosCompartidosValueSlider(Supplier<String> getter, Slider slider) {
        slider.setValue(Integer.parseInt(getter.get()));
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private void setImage (String path, ImageView imageView, GridPane gridPane){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        imageView.setImage(image);
        preserveRadio(imageView, gridPane);
    }
    protected void initializeMedia(){
//        insertImage(imageViewLogo, "IconLifeGame.png");
    }
    protected void insertImage(ImageView imageView, String resourceName) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(resourceName));
        imageView.setImage(image);
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
    protected static void initializeAudio(){
        insertSong("EraseUnaVezLaVida.mp3");
    }
    protected static void insertSong(String resourceName) {
        String path = ControllerMainStage.class.getClassLoader().getResource(resourceName).toExternalForm();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
    public void updateAllSliders (){
        getDatosCompartidosValueSlider(DatosCompartidos::getVidaInicial, vidaUserSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbReproduccion, probReproduccionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getProbClonacion, probClonacionSlider);

        getDatosCompartidosValueSlider(DatosCompartidos::getAguaVida, aguaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaVida, comidaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaVida, montanaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaVida, bibliotecaVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroVida, tesoroVidaSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoVida, pozoVidaSlider);

        getDatosCompartidosValueSlider(DatosCompartidos::getAguaEfecto, aguaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaEfecto, comidaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaEfecto, montanaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaEfecto, bibliotecaEfectoSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroEfecto, tesoroEfectoSlider);

        getDatosCompartidosValueSlider(DatosCompartidos::getAguaAparicion, aguaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaAparicion, comidaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaAparicion, montanaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaAparicion, bibliotecaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroAparicion, tesoroAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoAparicion, pozoAparicionSlider);
    }

    ////////////////////////////////////////Initialize////////////////////////////////////////////
    @FXML
    public void initialize() {
        game = new Game(tableroJuego);

        pauseTab.setDisable(true);
        this.pauseText.setVisible(false);
        this.playText.setVisible(true);

        initializeBindingSliders(vidaUserSlider,vidaUserText, medidaVidaUser);
        initializeBindingSliders(probReproduccionSlider,probReproduccionText, medidaReproduccion);
        initializeBindingSliders(probClonacionSlider,probClonacionText, medidaClonacion);
        initializeBindingSliders(aguaVidaSlider, aguaVidaText, medidaAguaVida);
        initializeBindingSliders(comidaVidaSlider, comidaVidaText, medidaComidaVida);
        initializeBindingSliders(montanaVidaSlider, montanaVidaText, medidaMontanaVida);
        initializeBindingSliders(bibliotecaVidaSlider, bibliotecaVidaText, medidaBibliotecaVida);
        initializeBindingSliders(tesoroVidaSlider, tesoroVidaText, medidaTesoroVida);
        initializeBindingSliders(pozoVidaSlider, pozoVidaText, medidaPozoVida);
        initializeBindingSliders(aguaEfectoSlider, aguaEfectoText, medidaAguaEfecto);
        initializeBindingSliders(comidaEfectoSlider, comidaEfectoText, medidaComidaEfecto);
        initializeBindingSliders(montanaEfectoSlider, montanaEfectoText, medidaMontanaEfecto);
        initializeBindingSliders(bibliotecaEfectoSlider, bibliotecaEfectoText, medidaBibliotecaEfecto);
        initializeBindingSliders(tesoroEfectoSlider, tesoroEfectoText, medidaTesoroEfecto);
        initializeBindingSliders(aguaAparicionSlider, aguaAparicionText, medidaAguaAparicion);
        initializeBindingSliders(comidaAparicionSlider, comidaAparicionText, medidaComidaAparicion);
        initializeBindingSliders(montanaAparicionSlider, montanaAparicionText, medidaMontanaAparicion);
        initializeBindingSliders(bibliotecaAparicionSlider, bibliotecaAparicionText, medidaBibliotecaAparicion);
        initializeBindingSliders(tesoroAparicionSlider, tesoroAparicionText, medidaTesoroAparicion);
        initializeBindingSliders(pozoAparicionSlider, pozoAparicionText, medidaPozoAparicion);

        tabPaneParametros.getStylesheets().add(getClass().getResource("/EstiloFondo/estiloAgua.css").toExternalForm());
        infoVBox.getStylesheets().add(getClass().getResource("/EstiloFondo/estiloAgua.css").toExternalForm());

        tabPaneParametros.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                // Actualizar los sliders al cambiar de pestaña
                updateAllSliders();
                if (newTab == anadirTab) {
                    // Si es la pestaña de añadir, establecer el valor de DatosCompartidos como true
                    DatosCompartidos.setAnadirTab(true);
                } else {
                    // Si es cualquier otra pestaña, establecer el valor de DatosCompartidos como false
                    DatosCompartidos.setAnadirTab(false);
                    radioIndividuo.setSelected(false);
                    radioAgua.setSelected(false);
                    radioComida.setSelected(false);
                    radioMontana.setSelected(false);
                    radioBiblioteca.setSelected(false);
                    radioTesoro.setSelected(false);
                    radioPozo.setSelected(false);
                    DatosCompartidos.setAnadir(0);
                }
            }
        });


    }

}
