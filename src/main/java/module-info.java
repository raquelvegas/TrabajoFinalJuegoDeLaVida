module com.example.eljuegodelavida {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ElJuegoDeLaVida to javafx.fxml;
    exports com.example.ElJuegoDeLaVida;
}