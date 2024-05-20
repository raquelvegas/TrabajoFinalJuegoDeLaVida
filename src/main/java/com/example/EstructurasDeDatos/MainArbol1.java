package com.example.EstructurasDeDatos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainArbol1 extends Application {
    @Override
    public void start(Stage stage) {
        TreeItem<String> root = new TreeItem<>("Hijo");

        TreeView<String> treeView = new TreeView<>(root);
        treeView.setShowRoot(true);

        TreeItem<String> branch1 = new TreeItem<>("Padre");
        TreeItem<String> branch2 = new TreeItem<>("Madre");

        TreeItem<String> leaf1 = new TreeItem<>("Abuelo paterno");
        TreeItem<String> leaf2 = new TreeItem<>("Abuela paterna");
        TreeItem<String> leaf3 = new TreeItem<>("Abuelo materno");
        TreeItem<String> leaf4 = new TreeItem<>("Abuela materna");

        root.getChildren().addAll(branch1, branch2);
        branch1.getChildren().addAll(leaf1, leaf2);
        branch2.getChildren().addAll(leaf3, leaf4);

        VBox layout = new VBox(treeView);
        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Árbol de prueba");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
