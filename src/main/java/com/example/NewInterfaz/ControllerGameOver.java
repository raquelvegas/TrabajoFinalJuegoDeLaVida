package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.NewInterfaz.Grafo_Conocimiento.GrafoConocimiento;
import com.example.NewInterfaz.Individuos.Individuo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ControllerGameOver {

    @FXML
    private ImageView arbolesGenealogicos, cerrarJuego, volverJugar;
    @FXML
    private Text longevo, masAgua, masClonaciones, masVida, masReproducciones, totalClonaciones,
            totalIndividuos, totalReproducciones;
    private Stage primaryStage; // Referencia al Stage principal


    @FXML
    void arbolGenealogico(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-padding: 10;");

        VBox treesLayout = new VBox(10);
        treesLayout.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(treesLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);  // Puedes ajustar esta altura según tus necesidades

        ListaEnlazada<Individuo> listaIndividuos = DatosCompartidos.getListaIndividuos();

        for (int i = 0; i < listaIndividuos.getNumeroElementos(); i++) {
            Individuo individuo = listaIndividuos.getElemento(i).getData();
            TreeItem<String> root = new TreeItem<>("Ind #"+String.valueOf(individuo.getID()));
            buildTree(root, individuo.getArbolGenealogico());

            TreeView<String> treeView = new TreeView<>(root);
            treeView.setShowRoot(true);
            treeView.setMinHeight(Region.USE_PREF_SIZE);

            VBox treeContainer = new VBox(treeView);
            treeContainer.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: white;");
            treeContainer.setAlignment(Pos.CENTER);

            treesLayout.getChildren().add(treeContainer);
        }

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> stage.close());

        Region spacerUp = new Region();
        spacerUp.setPrefHeight(5); // Espacio entre el TreeView y el botón
        Region spacerDown = new Region();
        spacerDown.setPrefHeight(10); // Espacio entre el botón y el final del stage

        mainLayout.getChildren().addAll(scrollPane, spacerUp, volverButton, spacerDown);

        Scene scene = new Scene(mainLayout, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Árbol genealógico general");

        Node source = (Node) event.getSource();
        Stage stageGameOver = (Stage) source.getScene().getWindow();
        stage.initOwner(stageGameOver);

        stage.initStyle(StageStyle.UNDECORATED);
        // Establecer el fondo de la escena a blanco
        scene.getRoot().setStyle("-fx-background-color: white; -fx-border-width: 3px; -fx-border-color: black;");

        stage.show();
    }

    @FXML
    void volverJugar(MouseEvent event) throws IOException {
        // Cerrar todas las ventanas abiertas
        for (Window window : Stage.getWindows()) {
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        }
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Cargar la nueva interfaz
        URL fxmlUrl1 = getClass().getResource("InterfazBase.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl1);
        Parent root1 = loader.load();

        // Crear un ImageView con la imagen de fondo
        Image backgroundImage = new Image("fondo.png"); // Ruta de la imagen de fondo
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Group rootGroup = new Group(backgroundImageView, root1);
        Stage mainStage = new Stage();

        mainStage.setScene(new Scene(rootGroup));
        mainStage.setMaximized(true); // Pantalla completa
        mainStage.setResizable(false); // Evitar que la ventana sea redimensionable

        // Ajustar la escala de la imagen para que cubra toda la ventana
        backgroundImageView.fitWidthProperty().bind(mainStage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(mainStage.heightProperty());


        ControllerMainStage controller = loader.getController();
        controller.setStage(mainStage);

        URL fxmlUrl2 = getClass().getResource("InterfazInicioJuego.fxml");
        Parent root2 = FXMLLoader.load(fxmlUrl2);

        Stage optionStage = new Stage();
        optionStage.setScene(new Scene(root2));
        optionStage.setResizable(true); // Permitir que la ventana sea redimensionable
        optionStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
        optionStage.initOwner(mainStage);

        optionStage.initStyle(StageStyle.UNDECORATED);
        optionStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");

        mainStage.setOnCloseRequest(eventClose -> {
            // Mostrar alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar salida");
            alert.setHeaderText("Estás a punto de abandonar «Life Game». ¿Estás seguro?");
            alert.setContentText("Si sales, perderás todos los cambios no guardados.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                mainStage.close(); // Si se acepta, se cierra
            } else {
                // Si el usuario cancela, se consume el evento para evitar que la ventana se cierre
                eventClose.consume();
            }
        });

        mainStage.show();
        optionStage.show();
    }

    @FXML
    void cerrarJuego(MouseEvent event) {
        System.exit(0);
    }

    private void buildTree(TreeItem<String> treeItem, ArbolBinario<Integer> arbol) {
        if (arbol == null) {
            return;
        }

        if (arbol.getRaiz().getNodoDch() != null) {
            Integer IDPadre = arbol.getRaiz().getNodoDch().getDato();
            TreeItem<String> padre = new TreeItem<>("Ind #" + String.valueOf(IDPadre));
            ListaEnlazada<Individuo> listaIndividuosTotales = DatosCompartidos.getListaIndividuos();
            Individuo indPadre = null;
            Integer contador = 0;
            while (indPadre == null && contador < listaIndividuosTotales.getNumeroElementos()) {
                if (listaIndividuosTotales.getElemento(contador).getData().getID() == IDPadre) {
                    indPadre = listaIndividuosTotales.getElemento(contador).getData();
                }
                contador++;
            }
            if (indPadre != null) {
                buildTree(padre, indPadre.getArbolGenealogico());
                treeItem.getChildren().add(padre);
            }
        }

        if (arbol.getRaiz().getNodoIzq() != null) {
            Integer IDMadre = arbol.getRaiz().getNodoIzq().getDato();
            TreeItem<String> madre = new TreeItem<>("Ind #"+String.valueOf(IDMadre));
            ListaEnlazada<Individuo> listaIndividuosTotales = DatosCompartidos.getListaIndividuos();
            Individuo indMadre = null;
            Integer contador = 0;
            while (indMadre == null && contador < listaIndividuosTotales.getNumeroElementos()) {
                if (listaIndividuosTotales.getElemento(contador).getData().getID() == IDMadre) {
                    indMadre = listaIndividuosTotales.getElemento(contador).getData();
                }
                contador++;
            }
            if (indMadre != null) {
                buildTree(madre, indMadre.getArbolGenealogico());
                treeItem.getChildren().add(madre);
            }
        }
    }


    @FXML
    public void initialize(){
        Integer numeroReproducciones = GrafoConocimiento.getNumeroReproducciones();
        Integer numeroClonaciones = GrafoConocimiento.getNumeroClonaciones();
        Integer idMayorReproducciones = GrafoConocimiento.getIndividuoMayorReproduccion();
        totalIndividuos.setText(String.valueOf(DatosCompartidos.getListaIndividuos().getNumeroElementos()));
        totalReproducciones.setText(String.valueOf(numeroReproducciones));
        totalClonaciones.setText(String.valueOf(numeroClonaciones));
        masReproducciones.setText(String.valueOf(idMayorReproducciones));
    }
}
