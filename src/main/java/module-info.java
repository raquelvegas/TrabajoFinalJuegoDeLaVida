module com.example.eljuegodelavida {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;


    exports com.example.NewInterfaz;
    opens com.example.NewInterfaz to javafx.fxml;
}
