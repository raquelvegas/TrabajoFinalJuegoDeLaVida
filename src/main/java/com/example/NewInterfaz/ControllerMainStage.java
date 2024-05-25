package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.SaveInfo.SaveInfo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ControllerMainStage {
    @FXML
    private Text turnoContador, vivosText, playText, pauseText, //Información juego
            vidaUserText, probReproduccionText, probClonacionText, //Ajustes User
            aguaVidaText, bibliotecaVidaText, comidaVidaText, montanaVidaText, pozoVidaText, tesoroVidaText, // Vida Recursos
            aguaEfectoText, bibliotecaEfectoText, comidaEfectoText, montanaEfectoText, tesoroEfectoText, // Efecto Recursos
            aparicionInicialText, aguaAparicionText, bibliotecaAparicionText, comidaAparicionText, montanaAparicionText, pozoAparicionText, tesoroAparicionText, // Aparicion Recursos
            tipoCelda1, tipoCelda2, tipoCelda3, tipoCelda4, tipoCelda5, tipoCelda6, //contenidoCeldaTab
            vidaCelda1, vidaCelda2, vidaCelda3, vidaCelda4, vidaCelda5, vidaCelda6; //contenidoCeldaTab
    @FXML
    private Slider vidaUserSlider, probReproduccionSlider, probClonacionSlider, //Ajustes User
            aguaVidaSlider, bibliotecaVidaSlider, comidaVidaSlider, montanaVidaSlider, pozoVidaSlider, tesoroVidaSlider, //Vida Recursos
            aguaEfectoSlider, bibliotecaEfectoSlider, comidaEfectoSlider, montanaEfectoSlider, tesoroEfectoSlider, //Efecto Recursos
            aparicionInicialSlider, aguaAparicionSlider, bibliotecaAparicionSlider, comidaAparicionSlider, montanaAparicionSlider, pozoAparicionSlider, tesoroAparicionSlider; // Aparicion Recursos
    @FXML
    private Button buttonVelocidad, buttonPlay, buttonPause, buttonStop, clearTablero; //Botones tablero
    @FXML
    private RadioButton radioIndividuo, radioAgua, radioComida, radioMontana, radioBiblioteca, radioTesoro, radioPozo; //Añadir RadioButtons
    @FXML
    private ToggleGroup anadir;
    @FXML
    private Rectangle celda1, celda2, celda3, celda4, celda5, celda6; // Celdas del squareAumentado
    @FXML
    static MediaPlayer mediaPlayer;
    @FXML
    private BorderPane basePane;
    @FXML
    private GridPane centralGridPane, tableroJuego, squareAumentado;
    @FXML
    private TabPane tabPaneParametros;
    @FXML
    private VBox infoVBox;
    @FXML
    private HBox contenidoTipo1, contenidoTipo2, contenidoTipo3, contenidoTipo4, contenidoTipo5, contenidoTipo6,
            contenidoVida1, contenidoVida2, contenidoVida3, contenidoVida4, contenidoVida5, contenidoVida6;
    @FXML
    private AnchorPane codigoColor;
    @FXML
    private Stage primaryStage, stage;
    @FXML
    private Tab contenidoCeldaTab;
    @FXML
    private Tab pauseTab, individuoTab, recursosParametrosTab, aparicionTab, anadirTab;
    protected boolean gameStopped = true;
    protected boolean gameON = false;
    private Timeline controlLoop;
    private Game game = DatosCompartidos.getGame();
    private static ControllerMainStage instance;

    private static final Logger log = LogManager.getLogger(ControllerMainStage.class);

    public ControllerMainStage() {
        instance = this;
    }

    public static ControllerMainStage getInstance() {
        return instance;
    }

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
    protected IntegerProperty medidaAparicionInicial = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaAguaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaComidaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaMontanaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaBibliotecaAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaTesoroAparicion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaPozoAparicion = new SimpleIntegerProperty(0);
    protected DoubleProperty velocidadJuegoProperty = new SimpleDoubleProperty(1.0);

    /////////////////////////////////////MouseEvents////////////////////////////////////////////////////

    @FXML
    void speedGame(MouseEvent event) {
        double nuevaVelocidad = 1;
        if (velocidadJuegoProperty.get() == 1){
            nuevaVelocidad = 1.5;
        } else if (velocidadJuegoProperty.get() == 1.5){
            nuevaVelocidad = 2;
        }else if (velocidadJuegoProperty.get() == 2){
            nuevaVelocidad = 1;
        }
        velocidadJuegoProperty.set(nuevaVelocidad);
        log.info("Modificación de la velocidad del bucle de control");
    }
    @FXML
    void playGame(MouseEvent event) {
        DatosCompartidos.setGameIniciado(true);
        if (gameStopped) {
            gameStopped = false;
            this.buttonVelocidad.setDisable(false);

            tabPaneParametros.getSelectionModel().select(pauseTab);
            this.individuoTab.setDisable(true);
            this.recursosParametrosTab.setDisable(true);
            this.aparicionTab.setDisable(true);
            this.anadirTab.setDisable(true);
            this.contenidoCeldaTab.setDisable(true);
            this.pauseTab.setDisable(false);
            this.pauseText.setVisible(true);
            this.codigoColor.setVisible(true);
            this.playText.setVisible(false);
            log.info("Reanudar el juego");
        }
    }
    @FXML
    void pauseGame(MouseEvent event) {
        if (!gameStopped) {
            gameStopped = true;
            this.buttonVelocidad.setDisable(true);

            this.individuoTab.setDisable(false);
            this.recursosParametrosTab.setDisable(false);
            this.aparicionTab.setDisable(false);
            this.anadirTab.setDisable(false);
            this.contenidoCeldaTab.setDisable(false);
            this.pauseTab.setDisable(true);
            this.pauseText.setVisible(false);
            this.codigoColor.setVisible(false);
            this.playText.setVisible(true);
            log.info("Pausar el juego");
        }
    }
    @FXML
    void stopGame(MouseEvent event) throws IOException {
        /////////// Creación del grafo y extracción de la información /////////

        controlLoop.pause();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Guardar Partida");
        alert.setHeaderText("¿Quieres guardar tu partida?");
        alert.setContentText("Elige tu opción.");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes){
            // Accion a realizar si el usuario quiere guardar la partida
            SaveInfo informacion = new SaveInfo(DatosCompartidos.getAltoMatriz(), DatosCompartidos.getAnchoMatriz(), DatosCompartidos.getProbReproduccion(), DatosCompartidos.getProbClonacion(), DatosCompartidos.getVidaInicial(), DatosCompartidos.getAguaVida(), DatosCompartidos.getComidaVida(), DatosCompartidos.getMontanaVida(), DatosCompartidos.getTesoroVida(), DatosCompartidos.getBibliotecaVida(), DatosCompartidos.getPozoVida(), DatosCompartidos.getAguaEfecto(), DatosCompartidos.getComidaEfecto(), DatosCompartidos.getMontanaEfecto(), DatosCompartidos.getTesoroEfecto(), DatosCompartidos.getBibliotecaEfecto(), DatosCompartidos.getAparicionInicial(), DatosCompartidos.getAguaAparicion(), DatosCompartidos.getComidaAparicion(), DatosCompartidos.getMontanaAparicion(), DatosCompartidos.getTesoroAparicion(), DatosCompartidos.getBibliotecaAparicion(), DatosCompartidos.getPozoAparicion(), DatosCompartidos.getNumIndividuos(), DatosCompartidos.getTurnoJuego(), DatosCompartidos.isGameIniciado(), DatosCompartidos.getListaRecursos(), DatosCompartidos.getListaIndividuos(), DatosCompartidos.getGame(), DatosCompartidos.getGame().getTablero());
            informacion.guardar("PartidaGuardada.json");
            System.out.println("Partida guardada");
            showGameOverStage();
            controlLoop.stop();
        } else if (result.get() == buttonTypeNo) {
            // Accion a realizar si el usuario no quiere guardar la partida
            System.out.println("Partida no guardada.");
            showGameOverStage();
            controlLoop.stop();
        } else {
            controlLoop.play();
            event.consume();
        }


    }
    @FXML
    void aplicarUser(MouseEvent event){
        setSlidersValue(DatosCompartidos::setVidaInicial, vidaUserSlider);
        setSlidersValue(DatosCompartidos::setProbReproduccion, probReproduccionSlider);
        setSlidersValue(DatosCompartidos::setProbClonacion, probClonacionSlider);
        log.info("Modificación de los parámetros iniciales de los nuevos individuos");
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
        log.info("Modificaciçon de los parámetros iniciales de los nuevos recursos");
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
        setSlidersValue(DatosCompartidos::setAparicionInicial, aparicionInicialSlider);
        setSlidersValue(DatosCompartidos::setAguaAparicion, aguaAparicionSlider);
        setSlidersValue(DatosCompartidos::setComidaAparicion, comidaAparicionSlider);
        setSlidersValue(DatosCompartidos::setMontanaAparicion, montanaAparicionSlider);
        setSlidersValue(DatosCompartidos::setBibliotecaAparicion, bibliotecaAparicionSlider);
        setSlidersValue(DatosCompartidos::setTesoroAparicion, tesoroAparicionSlider);
        setSlidersValue(DatosCompartidos::setPozoAparicion, pozoAparicionSlider);
        log.info("Modificación de los turnos de aparición iniciales de los nuevos recursos");
    }
    @FXML
    void resetAparicionRecursos(MouseEvent event) {
        getDatosCompartidosValueSlider(DatosCompartidos::getAparicionInicial, aparicionInicialSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaAparicion, aguaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaAparicion, comidaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaAparicion, montanaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaAparicion, bibliotecaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroAparicion, tesoroAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoAparicion, pozoAparicionSlider);
    }

    @FXML
    void setEstiloFuego(ActionEvent event) {
        game.getTablero().setNewTheme("Fuego");
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
        game.getTablero().setNewTheme("Agua");
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
        game.getTablero().setNewTheme("Natura");
        tabPaneParametros.setStyle("-fx-background-color: #c0e6b2ff;");
        infoVBox.setStyle("-fx-background-color: #c0e6b2ff;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Natura");
        }
        log.info("Cambio de color de visualización: Natura");

    }
    @FXML
    void setEstiloTierra(ActionEvent event) {
        game.getTablero().setNewTheme("Tierra");
        tabPaneParametros.setStyle("-fx-background-color: #fcf5c7;");
        infoVBox.setStyle("-fx-background-color: #fcf5c7;");
        for (int i = 0; i< Game.getTablero().getSquares().getNumeroElementos(); i++){
            Square square = Game.getTablero().getSquare(i);
            Game.getTablero().setTheme(square,"Tierra");
        }
        log.info("Cambio de color de visualización: Tierra");

    }

    @FXML
    void comoJugar(ActionEvent event) throws IOException {
        URL fxmlUrl = getClass().getResource("InterfazComoJugar.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();
        log.info("Acceso a pantalla 'ComoJugar'");
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
        game.actualizarTablero();
        DatosCompartidos.setGame(game);
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
    private void showGameOverStage() throws IOException {
        mediaPlayer.stop();
        URL fxmlUrl = getClass().getResource("gameOver.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage gameOverStage = new Stage();
        gameOverStage.setScene(new Scene(root));
        gameOverStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        gameOverStage.initOwner(primaryStage);

        gameOverStage.initStyle(StageStyle.UNDECORATED);
        gameOverStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        gameOverStage.show();
    }
    public void actulizarCeldaSeleccionadaTab(){
        //Celda 1
        String tipo1 = traducirTipo(CeldaSeleccionada.getTipo1());
        if (tipo1 == "Vacio") {
            contenidoTipo1.setVisible(false);
            contenidoVida1.setVisible(false);
        } else {
            contenidoTipo1.setVisible(true);
            contenidoVida1.setVisible(true);

            tipoCelda1.setText(tipo1);
            vidaCelda1.setText(String.valueOf(CeldaSeleccionada.getVida1()));
        }

        //Celda 2
        String tipo2 = traducirTipo(CeldaSeleccionada.getTipo2());
        if (tipo2 == "Vacio") {
            contenidoTipo2.setVisible(false);
            contenidoVida2.setVisible(false);
        } else {
            contenidoTipo2.setVisible(true);
            contenidoVida2.setVisible(true);

            tipoCelda2.setText(tipo2);
            vidaCelda2.setText(String.valueOf(CeldaSeleccionada.getVida2()));
        }

        //Celda 3
        String tipo3 = traducirTipo(CeldaSeleccionada.getTipo3());
        if (tipo3 == "Vacio") {
            contenidoTipo3.setVisible(false);
            contenidoVida3.setVisible(false);
        } else {
            contenidoTipo3.setVisible(true);
            contenidoVida3.setVisible(true);

            tipoCelda3.setText(tipo3);
            vidaCelda3.setText(String.valueOf(CeldaSeleccionada.getVida3()));
        }

        //Celda 4
        String tipo4 = traducirTipo(CeldaSeleccionada.getTipo4());
        if (tipo4 == "Vacio") {
            contenidoTipo4.setVisible(false);
            contenidoVida4.setVisible(false);
        } else {
            contenidoTipo4.setVisible(true);
            contenidoVida4.setVisible(true);

            tipoCelda4.setText(tipo4);
            vidaCelda4.setText(String.valueOf(CeldaSeleccionada.getVida4()));
        }

        //Celda 5
        String tipo5 = traducirTipo(CeldaSeleccionada.getTipo5());
        if (tipo5 == "Vacio") {
            contenidoTipo5.setVisible(false);
            contenidoVida5.setVisible(false);
        } else {
            contenidoTipo5.setVisible(true);
            contenidoVida5.setVisible(true);

            tipoCelda5.setText(tipo5);
            vidaCelda5.setText(String.valueOf(CeldaSeleccionada.getVida5()));
        }

        //Celda 6
        String tipo6 = traducirTipo(CeldaSeleccionada.getTipo6());
        if (tipo6 == "Vacio") {
            contenidoTipo6.setVisible(false);
            contenidoVida6.setVisible(false);
        } else {
            contenidoTipo6.setVisible(true);
            contenidoVida6.setVisible(true);

            tipoCelda6.setText(tipo6);
            vidaCelda6.setText(String.valueOf(CeldaSeleccionada.getVida6()));
        }


        celda1.setFill(pintarSquareAumentado(CeldaSeleccionada.getTipo1()));
        celda2.setFill(pintarSquareAumentado(CeldaSeleccionada.getTipo2()));
        celda3.setFill(pintarSquareAumentado(CeldaSeleccionada.getTipo3()));
        celda4.setFill(pintarSquareAumentado(CeldaSeleccionada.getTipo4()));
        celda5.setFill(pintarSquareAumentado(CeldaSeleccionada.getTipo5()));
        celda6.setFill(pintarSquareAumentado(CeldaSeleccionada.getTipo6()));
    }
    public static String traducirTipo(Double tipo){
        String tipoTraducido;
        if(tipo == 1.1){
            tipoTraducido = "Individuo Básico";
        } else if (tipo == 1.2){
            tipoTraducido = "Individuo Normal";
        } else if (tipo == 1.3){
            tipoTraducido = "Individuo Avanzado";
        } else if (tipo == 2.0){
            tipoTraducido = "Agua";
        } else if (tipo == 3.0){
            tipoTraducido = "Comida";
        } else if (tipo == 4.0){
            tipoTraducido = "Montaña";
        } else if (tipo == 5.0){
            tipoTraducido = "Biblioteca";
        } else if (tipo == 6.0){
            tipoTraducido = "Tesoro";
        } else if (tipo == 7.0){
            tipoTraducido = "Pozo";
        } else {
            tipoTraducido = "Vacio";
        }
        return tipoTraducido;
    }
    public Color pintarSquareAumentado (Double tipo){
        int tipoEntero = tipo.intValue();
        int subtipo = (int) ((tipo *10)-10);
        Color color = null;

        switch (tipoEntero) {
            case 1: //Individuo
                switch (subtipo) {
                    case 1: // Tipo 1.1
                        // Código para el tipo 1.1
                        color = Color.web("#212121");
                        break;
                    case 2: // Tipo 1.2
                        // Código para el tipo 1.2
                        color = Color.web("#616161");
                        break;
                    case 3: // Tipo 1.3
                        // Código para el tipo 1.3
                        color = Color.web("#9e9e9e");
                        break;
                }
                break;
            case 2: //Agua - Azul
                color = Color.web("#0404e2");
                break;
            case 3: //Comida - Verde
                color = Color.web("#00AD43");
                break;
            case 4: //Montaña - Marrón
                color = Color.web("#864332");
                break;
            case 5: //Biblioteca - Naranja
                color = Color.web("#fc4b08");
                break;
            case 6: //Tesoro - Amarillo
                color = Color.web("#ffd700");
                break;
            case 7: //Pozo - Rojo
                color = Color.web("#ff0000");
                break;
            default: //Transparente
                color = Color.TRANSPARENT;
        }
        return color;
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
        insertSong("LaBamba.mp3");
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

        getDatosCompartidosValueSlider(DatosCompartidos::getAparicionInicial, aparicionInicialSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getAguaAparicion, aguaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getComidaAparicion, comidaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getMontanaAparicion, montanaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getBibliotecaAparicion, bibliotecaAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getTesoroAparicion, tesoroAparicionSlider);
        getDatosCompartidosValueSlider(DatosCompartidos::getPozoAparicion, pozoAparicionSlider);
    }
    private void actualizarTextVivos(){
        int numIndVivos = DatosCompartidos.getListaIndividuos().getNumeroElementos();
        vivosText.setText(String.valueOf(numIndVivos));
    }

    private void inicializarBucleControl() {
        controlLoop = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            if (DatosCompartidos.isGameIniciado()) {
                if (!gameStopped) {
                    // Lógica para continuar el juego
                    int turno = Integer.parseInt(turnoContador.getText());
                    turno++;
                    DatosCompartidos.setTurnoJuego(turno);
                    turnoContador.setText(String.valueOf(turno));
                    game.turno();
                    actualizarTextVivos();
//                    if (DatosCompartidos.getListaIndividuos().getNumeroElementos() == 1){
//                        DatosCompartidos.setGameIniciado(false);
//                    }
                } else {
                    //Lógica cuando el juego está pausado
                    actualizarTextVivos();
                }
            } else if (DatosCompartidos.getTurnoJuego() == 0) {
                //Lógica para cuando el juego aún no ha iniciado
                actualizarTextVivos();
            } else {
                //Lógica para cuando el juego termina
                controlLoop.stop();
                try {
                    showGameOverStage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        controlLoop.setCycleCount(Animation.INDEFINITE);
        controlLoop.rateProperty().bind(velocidadJuegoProperty);
        controlLoop.play();
    }

    ////////////////////////////////////////Initialize////////////////////////////////////////////
    @FXML
    public void initialize() {
        game = new Game(tableroJuego);
        DatosCompartidos.setGame(game);

        pauseTab.setDisable(true);
        this.pauseText.setVisible(false);
        this.playText.setVisible(true);
        this.codigoColor.setVisible(false);
        this.buttonVelocidad.setDisable(true);

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
        initializeBindingSliders(aparicionInicialSlider, aparicionInicialText, medidaAparicionInicial);
        initializeBindingSliders(aguaAparicionSlider, aguaAparicionText, medidaAguaAparicion);
        initializeBindingSliders(comidaAparicionSlider, comidaAparicionText, medidaComidaAparicion);
        initializeBindingSliders(montanaAparicionSlider, montanaAparicionText, medidaMontanaAparicion);
        initializeBindingSliders(bibliotecaAparicionSlider, bibliotecaAparicionText, medidaBibliotecaAparicion);
        initializeBindingSliders(tesoroAparicionSlider, tesoroAparicionText, medidaTesoroAparicion);
        initializeBindingSliders(pozoAparicionSlider, pozoAparicionText, medidaPozoAparicion);

        tabPaneParametros.getStylesheets().add(getClass().getResource("/EstiloFondo/estiloAgua.css").toExternalForm());
        infoVBox.getStylesheets().add(getClass().getResource("/EstiloFondo/estiloAgua.css").toExternalForm());
        squareAumentado.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        celda1.setFill(Color.TRANSPARENT);
        celda2.setFill(Color.TRANSPARENT);
        celda3.setFill(Color.TRANSPARENT);
        celda4.setFill(Color.TRANSPARENT);
        celda5.setFill(Color.TRANSPARENT);
        celda6.setFill(Color.TRANSPARENT);

        tabPaneParametros.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                // Actualizar los sliders al cambiar de pestaña
                updateAllSliders();
                if (newTab == anadirTab) {
                    // Si es la pestaña de añadir, establecer el valor de DatosCompartidos como true
                    DatosCompartidos.setAnadirTab(true);
                    DatosCompartidos.setContenidoCeldaTab(false);
                } else if (newTab == contenidoCeldaTab) {
                    // Si es la pestaña de añadir, establecer el valor de DatosCompartidos como true
                    DatosCompartidos.setContenidoCeldaTab(true);
                    DatosCompartidos.setAnadirTab(false);
                } else {
                    // Si es cualquier otra pestaña, establecer el valor de DatosCompartidos como false
                    DatosCompartidos.setAnadirTab(false);
                    DatosCompartidos.setContenidoCeldaTab(false);                    radioIndividuo.setSelected(false);
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

        inicializarBucleControl();

    }
}
