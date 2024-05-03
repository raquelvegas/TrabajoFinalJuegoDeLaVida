package com.example.NewInterfaz;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerPrueba {
    @FXML
    private Text vivosText, muertosText, playText, pauseText, //Información juego
            vidaUserText, probReproduccionText, probClonacionText, //Ajustes User
            aguaVidaText, bibliotecaVidaText, comidaVidaText, montanaVidaText, pozoVidaText, tesoroVidaText, // Vida Recursos
            aguaEfectoText, bibliotecaEfectoText, comidaEfectoText, montanaEfectoText, pozoEfectoText, tesoroEfectoText; // Efecto Recursos
    @FXML
    private Slider vidaUserSlider, probReproduccionSlider, probClonacionSlider, //Ajustes User
            aguaVidaSlider, bibliotecaVidaSlider, comidaVidaSlider, montanaVidaSlider, pozoVidaSlider, tesoroVidaSlider, //Vida Recursos
            aguaEfectoSlider, bibliotecaEfectoSlider, comidaEfectoSlider, montanaEfectoSlider, tesoroEfectoSlider; //Efecto Recursos
    @FXML
    private Button buttonVelocidad, buttonPlay, buttonPause, buttonStop; //Botones tablero
    @FXML
    private GridPane baseGridPane, centralGridPane, tableroJuego;
    @FXML
    private TabPane tabPaneParametros;
    @FXML
    private VBox infoVBox;
    @FXML
    private Stage stage;
    protected boolean gameStopped = true;
    @FXML
    private Tab pauseTab, individuoTab, recursosParametrosTab, anadirTab;

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

    /////////////////////////////////////MouseEvents////////////////////////////////////////////////////
    @FXML
    void speedGame(MouseEvent event) {

    }
    @FXML
    void playGame(MouseEvent event) {
        if (gameStopped) {
            gameStopped = false;

            tabPaneParametros.getSelectionModel().select(pauseTab);
            this.individuoTab.setDisable(true);
            this.recursosParametrosTab.setDisable(true);
            this.anadirTab.setDisable(true);
            this.pauseTab.setDisable(false);
            this.pauseText.setVisible(true);
            this.playText.setVisible(false);
        }
    }
    @FXML
    void pauseGame(MouseEvent event) {
        if (!gameStopped) {
            gameStopped = true;

            this.individuoTab.setDisable(false);
            this.recursosParametrosTab.setDisable(false);
            this.anadirTab.setDisable(false);
            this.pauseTab.setDisable(true);
            this.pauseText.setVisible(false);
            this.playText.setVisible(true);
        }
    }
    @FXML
    void stopGame(MouseEvent event) {

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
        setSlidersValue(DatosCompartidos::setAguaEfecto, aguaEfectoSlider);
        setSlidersValue(DatosCompartidos::setComidaEfecto, comidaEfectoSlider);
        setSlidersValue(DatosCompartidos::setMontanaEfecto, montanaEfectoSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaEfecto, bibliotecaEfectoSlider);
        setSlidersValue(DatosCompartidos::setTesoroEfecto, tesoroEfectoSlider);
    }

    @FXML
    void resetRecursosVida(MouseEvent event){
        getDatosCompartidosValue(DatosCompartidos::getAguaVida, aguaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getComidaVida, comidaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getMontanaVida, montanaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getBibliotecaVida, bibliotecaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getTesoroVida, tesoroVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getPozoVida, pozoVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getAguaEfecto, aguaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getComidaEfecto, comidaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getMontanaEfecto, montanaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getBibliotecaEfecto, bibliotecaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getTesoroEfecto, tesoroEfectoSlider);
    }

//    @FXML
//    void cambiarTema(MenuItem menuItem){
//        String newTheme = menuItem.getText();
//        tableroJuego.updateTheme(newTheme);
//    }

    ///////////////////////////////////Métodos de apoyo///////////////////////////////////////////
    protected void initializeBindingSliders(Slider slider, Text text, IntegerProperty medida){
        slider.valueProperty().bindBidirectional(medida);
        text.textProperty().bind(medida.asString());
    }
    protected void setSlidersValue(Consumer<String> setter, Slider slider){
        setter.accept(String.valueOf((int) slider.getValue()));
    }
    private void getDatosCompartidosValue (Supplier<String> getter, Slider slider) {
        slider.setValue(Integer.parseInt(getter.get()));
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void updateAllSliders (){
        getDatosCompartidosValue(DatosCompartidos::getVidaInicial, vidaUserSlider);
        getDatosCompartidosValue(DatosCompartidos::getProbReproduccion, probReproduccionSlider);
        getDatosCompartidosValue(DatosCompartidos::getProbClonacion, probClonacionSlider);

        getDatosCompartidosValue(DatosCompartidos::getAguaVida, aguaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getComidaVida, comidaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getMontanaVida, montanaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getBibliotecaVida, bibliotecaVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getTesoroVida, tesoroVidaSlider);
        getDatosCompartidosValue(DatosCompartidos::getPozoVida, pozoVidaSlider);

        getDatosCompartidosValue(DatosCompartidos::getAguaEfecto, aguaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getComidaEfecto, comidaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getMontanaEfecto, montanaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getBibliotecaEfecto, bibliotecaEfectoSlider);
        getDatosCompartidosValue(DatosCompartidos::getTesoroEfecto, tesoroEfectoSlider);
    }

    ////////////////////////////////////////Initialize////////////////////////////////////////////
    @FXML
    public void initialize() {
        Game game = new Game(tableroJuego, "Coral");

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


        tabPaneParametros.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        infoVBox.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        tabPaneParametros.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                // Actualizar los sliders al cambiar de pestaña
                updateAllSliders();
            }
        });
    }

}
