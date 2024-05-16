    package com.example.NewInterfaz;

    import javafx.animation.KeyFrame;
    import javafx.animation.KeyValue;
    import javafx.animation.Timeline;
    import javafx.application.Application;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Group;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.ProgressBar;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.StackPane;
    import javafx.scene.layout.VBox;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import javafx.util.Duration;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;

    import java.io.IOException;
    import java.net.URL;
    import java.util.Optional;

    public class MainInterfazBase extends Application {

        private static final Logger log = LogManager.getLogger(MainInterfazBase.class);

        @Override
        public void start(Stage primaryStage) throws IOException {
            log.info("Inicio de la ejecución");

            ImageView logoImageView = new ImageView(new Image("IconLifeGame.png"));
            logoImageView.setFitWidth(400);
            logoImageView.setFitHeight(400);
            logoImageView.setOpacity(0); // Empieza invisible

            ProgressBar progressBar = new ProgressBar(0);
            progressBar.setPrefWidth(400);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(logoImageView, progressBar);
            vBox.setAlignment(javafx.geometry.Pos.CENTER);

            StackPane root = new StackPane();
            root.getChildren().add(vBox);
            root.setStyle("-fx-background-color: black;");

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cargando...");
            primaryStage.setResizable(false);
            primaryStage.setMaximized(true);
            primaryStage.setOnCloseRequest(event -> event.consume());


            URL fxmlUrl1 = getClass().getResource("InterfazBase.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlUrl1);
            Parent root1 = loader.load();

            // Crear un ImageView con la imagen de fondo
            Image backgroundImage = new Image("fondo.png"); // Ruta de la imagen de fondo
            ImageView backgroundImageView = new ImageView(backgroundImage);

            // Ajustar la escala de la imagen para que cubra toda la ventana
            backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
            backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());

            Group rootGroup = new Group(backgroundImageView, root1);
            Stage mainStage = new Stage();

            mainStage.setScene(new Scene(rootGroup));
            mainStage.setMaximized(true); // Pantalla completa
            //primaryStage.setFullScreen(true);
            mainStage.setResizable(false); // Evitar que la ventana sea redimensionable

            //Para el EXIT
            ControllerMainStage controller = loader.getController();
            controller.setStage(mainStage);


            URL fxmlUrl2 = getClass().getResource("InterfazInicioJuego.fxml");
            Parent root2 = FXMLLoader.load(fxmlUrl2);

            Stage optionStage = new Stage();
            optionStage.setScene(new Scene(root2));
            optionStage.setResizable(true); // Evitar que la ventana sea redimensionable
            optionStage.initModality(Modality.APPLICATION_MODAL); // Impide la interacción con la ventana principal
            optionStage.initOwner(primaryStage);

            optionStage.initStyle(StageStyle.UNDECORATED);
            optionStage.getScene().getRoot().setStyle("-fx-border-width: 3px; -fx-border-color: black;");


            mainStage.setOnCloseRequest(event -> {
                // Mostrar alerta
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar salida");
                alert.setHeaderText("Estás a punto de abandonar «Life Game». ¿Estás seguro?");
                alert.setContentText("Si sales, perderás todos los cambios no guardados.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    mainStage.close(); //si se acepta se cierra
                } else {
                    // Si el usuario cancela, se consume el evento para evitar que la ventana se cierre
                    event.consume();
                }
                log.info("Fin de la ejecución");
            });


            mainStage.show();
            optionStage.show();
            primaryStage.show();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(logoImageView.opacityProperty(), 0)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(logoImageView.opacityProperty(), 1)),
                    new KeyFrame(Duration.seconds(3), new KeyValue(progressBar.progressProperty(), 1)),
                    new KeyFrame(Duration.seconds(3), event -> {
                        primaryStage.close();

                    })
            );
            timeline.play();
    }


        public static void main(String[] args) {
            launch();
        }
    }