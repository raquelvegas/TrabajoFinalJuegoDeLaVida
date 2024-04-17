package com.example.ElJuegoDeLaVida;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class ControllerMenuInicial {

    @FXML
    private Button buttonCargar;

    @FXML
    private Button button;

    @FXML
    void goNewPlay(MouseEvent event) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        URL fxmlUrl = getClass().getResource("MenuParametrosTablero.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), button.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(actionEvent -> {
            stage.setScene(new Scene(root));
            stage.setFullScreen(false);
            stage.setFullScreenExitHint("");
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }
}
