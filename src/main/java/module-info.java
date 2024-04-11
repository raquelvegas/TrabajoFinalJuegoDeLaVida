module com.example.eljuegodelavida {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eljuegodelavida to javafx.fxml;
    exports com.example.eljuegodelavida;
}