package com.example.NewInterfaz;


import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.SaveInfo.SaveInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class ControllerInicioJuego {

    private Stage primaryStage; // Referencia al Stage principal

    private Game game = DatosCompartidos.getGame();

    private GridPane tableroJuego = ControllerMainStage.getInstance().getTableroJuego();

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button buttonBuevaPartida;

    @FXML
    private Button buttonCargarPartida;

    private static final Logger log = LogManager.getLogger(ControllerInicioJuego.class);

    @FXML
    void cargarPartida(MouseEvent event) throws IOException {
        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar la partida desde un archivo JSon
        SaveInfo datosCargados = SaveInfo.cargar("PartidaGuardada.json");
        transladarInfo(datosCargados);
        System.out.println(datosCargados.getCuadradosTablero().getNumeroElementos());
        for(int i=0;i<DatosCompartidos.getListaIndividuos().getNumeroElementos();i++){
            System.out.println(DatosCompartidos.getListaIndividuos().getElemento(i).getData().getArbolGenealogico());
        }
        cargarTablero(this.tableroJuego, "Agua");
    }

    private void transladarInfo(SaveInfo info){
        for (int i = 0; i < info.getGame().getTablero().getSquares().getNumeroElementos(); i++) {
            Square cuadrado = info.getGame().getTablero().getSquares().getDato(i);
            if (!cuadrado.getRecursos().isVacia()) {
                for (int j = 0; j < cuadrado.getRecursos().getNumeroElementos(); j++) {
                    cuadrado.getRecursos().getDato(j).setSquare(cuadrado);
                }
            }
        }
        DatosCompartidos.setAltoMatriz(info.getAltoMatriz());
        DatosCompartidos.setAnchoMatriz(info.getAnchoMatriz());
        DatosCompartidos.setProbReproduccion(info.getProbReproduccion());
        DatosCompartidos.setProbClonacion(info.getProbClonacion());
        DatosCompartidos.setVidaInicial(info.getVidaInicial());
        DatosCompartidos.setAguaVida(info.getAguaVida());
        DatosCompartidos.setComidaVida(info.getComidaVida());
        DatosCompartidos.setMontanaVida(info.getMontanaVida());
        DatosCompartidos.setTesoroVida(info.getTesoroVida());
        DatosCompartidos.setBibliotecaVida(info.getBibliotecaVida());
        DatosCompartidos.setPozoVida(info.getPozoVida());
        DatosCompartidos.setAguaEfecto(info.getAguaEfecto());
        DatosCompartidos.setComidaEfecto(info.getComidaEfecto());
        DatosCompartidos.setMontanaEfecto(info.getMontanaEfecto());
        DatosCompartidos.setTesoroEfecto(info.getTesoroEfecto());
        DatosCompartidos.setBibliotecaEfecto(info.getBibliotecaEfecto());
        DatosCompartidos.setAparicionInicial(info.getAparicionInicial());
        DatosCompartidos.setAguaAparicion(info.getAguaAparicion());
        DatosCompartidos.setComidaAparicion(info.getComidaAparicion());
        DatosCompartidos.setMontanaAparicion(info.getMontanaAparicion());
        DatosCompartidos.setTesoroAparicion(info.getTesoroAparicion());
        DatosCompartidos.setBibliotecaAparicion(info.getBibliotecaAparicion());
        DatosCompartidos.setPozoAparicion(info.getPozoAparicion());
        DatosCompartidos.setAnadir(0);
        DatosCompartidos.setAnadirTab(false);
        DatosCompartidos.setNumIndividuos(info.getNumIndividuos());
        DatosCompartidos.setTurnoJuego(info.getTurnoJuego());
        DatosCompartidos.setGameIniciado(info.isGameIniciado());
        DatosCompartidos.setListaRecursos(info.getListaRecursos());
        DatosCompartidos.setListaIndividuos(info.getListaIndividuos());
        DatosCompartidos.setIndividuosMuertos(info.getIndividuosMuertos());
        DatosCompartidos.setCuadradosTablero(info.getCuadradosTablero());
        DatosCompartidos.setGame(info.getGame());
    }
    @FXML
    void nuevaPartida(MouseEvent event) throws IOException {
        // Cerrar la ventana actual
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar y mostrar la ventana de configuración de propiedades del tablero
        URL fxmlUrl = getClass().getResource("InterfazTableroPropiedades.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Stage configStage = new Stage();
        configStage.setScene(new Scene(root));
        configStage.setResizable(true); // Evitar que la ventana sea redimensionable
        configStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        configStage.initOwner(primaryStage);

        configStage.initStyle(StageStyle.UNDECORATED);
        configStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        configStage.show();
        log.info("Inicio de una nueva partida");
    }

    private void cargarTablero(GridPane tableroJuego, String theme) {
        int alto = Integer.parseInt(DatosCompartidos.getAltoMatriz());
        int ancho = Integer.parseInt(DatosCompartidos.getAnchoMatriz());
        ListaEnlazada<Square> squaresCargar = DatosCompartidos.getCuadradosTablero();
        int identificador = 0;

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Square squareCargar = squaresCargar.getElemento(0).getData();
                Square square = new Square(i, j);
                square.setID(identificador);

                for (int k = 0; k < 6; k++) {
                    Celda celdaCargar = squareCargar.getCelda(k);
                    Celda celda = square.getCelda(k);
                    celda.setTipo(celdaCargar.getTipo());
                    celda.setOcupado(celdaCargar.isOcupado());
                }

                square.setIndividuos(squareCargar.getIndividuos().copiaLista());
                square.setRecursos(squareCargar.getRecursos().copiaLista());
                square.setPrefHeight(100);
                square.setPrefWidth(100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                game.getTablero().setTheme(square, theme);
                tableroJuego.add(square, i, j);
                game.getTablero().getSquares().add(square);
                identificador++;
                squaresCargar.del(0);
            }
        }
        game.actualizarTablero();
    }
}
