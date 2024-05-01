    package com.example.NewInterfaz;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Group;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;

    import java.io.IOException;
    import java.net.URL;
    import java.util.Optional;

    public class MainInterfazBase extends Application {

        @Override
        public void start(Stage primaryStage) throws IOException {
            //URL fxmlUrl1 = getClass().getResource("InterfazBase.fxml");
            //URL fxmlUrl1 = getClass().getResource("InterfazGridPane.fxml");
            URL fxmlUrl1 = getClass().getResource("InterfazPrueba.fxml");
            Parent root1 = FXMLLoader.load(fxmlUrl1);

            // Crear un ImageView con la imagen de fondo
            Image backgroundImage = new Image("fondo.png"); // Ruta de la imagen de fondo
            ImageView backgroundImageView = new ImageView(backgroundImage);

            // Ajustar la escala de la imagen para que cubra toda la ventana
            backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
            backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());

            Group rootGroup = new Group(backgroundImageView, root1);

            primaryStage.setTitle("¡Bienvenido!");
            primaryStage.setScene(new Scene(rootGroup));
            //primaryStage.setMaximized(true); // Pantalla completa
            primaryStage.setFullScreen(true);
            primaryStage.setResizable(false); // Evitar que la ventana sea redimensionable


//            URL fxmlUrl2 = getClass().getResource("InterfazInicioJuego.fxml");
//            Parent root2 = FXMLLoader.load(fxmlUrl2);
//            Stage optionStage = new Stage();
//            optionStage.setScene(new Scene(root2));
//            optionStage.setResizable(true); // Evitar que la ventana sea redimensionable
//            optionStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
//            optionStage.initOwner(primaryStage);
//
//            optionStage.initStyle(StageStyle.UNDECORATED);
//            optionStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");



            primaryStage.show();
//            optionStage.show();


            primaryStage.setOnCloseRequest(event -> {
                event.consume(); // Consumir el evento para evitar que la ventana se cierre directamente

                // Mostrar alerta
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar salida");
                alert.setHeaderText("Estás a punto de abandonar «Life Game». ¿Estás seguro?");
                alert.setContentText("Si sales, perderás todos los cambios no guardados.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    primaryStage.close(); //si se acepta se cierra
                }
            });
            primaryStage.show(); //Sino se muestra otra vez el Stage principal
    }


        public static void main(String[] args) {
            launch();
        }
    }