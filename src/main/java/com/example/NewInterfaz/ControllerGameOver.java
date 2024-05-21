package com.example.NewInterfaz;

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
    private Text longevo, masAgua, masClonaciones, masClonaciones1, masReproducciones, totalClonaciones,
            totalIndividuos, totalReproducciones;
    private Stage primaryStage; // Referencia al Stage principal


    @FXML
    void arbolGenealogico(MouseEvent event) throws IOException {
        Stage stage = new Stage();

        TreeItem<String> root = new TreeItem<>("Hijo");

        TreeView<String> treeView = new TreeView<>(root);
        treeView.setShowRoot(true);

        TreeItem<String> branch1 = new TreeItem<>("Padre");
        TreeItem<String> branch2 = new TreeItem<>("Madre");
        TreeItem<String> branch3 = new TreeItem<>("Padre");
        TreeItem<String> branch4 = new TreeItem<>("Madre");
        TreeItem<String> branch5 = new TreeItem<>("Padre");
        TreeItem<String> branch6 = new TreeItem<>("Madre");
        TreeItem<String> branch7 = new TreeItem<>("Padre");
        TreeItem<String> branch8 = new TreeItem<>("Madre");
        TreeItem<String> branch9 = new TreeItem<>("Padre");
        TreeItem<String> branch10 = new TreeItem<>("Madre");
        TreeItem<String> branch11 = new TreeItem<>("Padre");
        TreeItem<String> branch12 = new TreeItem<>("Madre");
        TreeItem<String> branch13 = new TreeItem<>("Padre");
        TreeItem<String> branch14 = new TreeItem<>("Madre");
        TreeItem<String> branch15 = new TreeItem<>("Padre");
        TreeItem<String> branch16 = new TreeItem<>("Madre");

        TreeItem<String> leaf1 = new TreeItem<>("Abuelo paterno");
        TreeItem<String> leaf2 = new TreeItem<>("Abuela paterna");
        TreeItem<String> leaf3 = new TreeItem<>("Abuelo materno");
        TreeItem<String> leaf4 = new TreeItem<>("Abuela materna");

        root.getChildren().addAll(branch1, branch2, branch3, branch4, branch5, branch6, branch7, branch8
                , branch9, branch10, branch11, branch12, branch13, branch14, branch15, branch16);
        branch1.getChildren().addAll(leaf1, leaf2);
        branch2.getChildren().addAll(leaf3, leaf4);

        Button volverButton = new Button("Volver");
        volverButton.setOnAction(e -> stage.close());

        Region spacerUp = new Region();
        spacerUp.setPrefHeight(5); // Espacio entre el TreeView y el botón
        Region spacerDown = new Region();
        spacerDown.setPrefHeight(10); // Espacio entre el botón y el final del stage

        VBox layout = new VBox(10, treeView, spacerUp, volverButton, spacerDown); // 10 es el espaciado entre los elementos del VBox
        layout.setAlignment(Pos.CENTER); // Centra el contenido del VBox

        Scene scene = new Scene(layout, 400, 400);
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
}
