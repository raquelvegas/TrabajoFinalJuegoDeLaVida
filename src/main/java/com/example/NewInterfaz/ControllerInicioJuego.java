package com.example.NewInterfaz;


import com.example.EstructurasDeDatos.Cola;
import com.example.NewInterfaz.Grafo_Conocimiento.Acción;
import com.example.NewInterfaz.Individuos.Individuo;
import com.example.SaveInfo.SaveInfo;
import com.example.SaveInfo.gsonAdapterAccion;
import com.example.SaveInfo.gsonAdapterCola;
import com.example.SaveInfo.gsonAdapterIndividuo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ControllerInicioJuego {

    private Stage primaryStage; // Referencia al Stage principal

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
        SaveInfo datosCargados = SaveInfo.cargar("PartidaGuardada.json");
        System.out.println("Numero de cuadrados: "+datosCargados.getTablero().getSquares().getNumeroElementos());
        transladarInfo(datosCargados);
        System.out.println("Datos Cargados");
    }

    private void transladarInfo(SaveInfo info){
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
}
