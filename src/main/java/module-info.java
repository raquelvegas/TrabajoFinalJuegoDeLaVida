module com.example.eljuegodelavida {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.ElJuegoDeLaVida to javafx.fxml;
    exports com.example.ElJuegoDeLaVida;
    exports com.example.NewInterfaz;
    opens com.example.NewInterfaz to javafx.fxml;
}
