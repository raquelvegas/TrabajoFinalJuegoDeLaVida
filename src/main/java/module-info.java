module com.example.eljuegodelavida {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires org.apache.logging.log4j;
    requires com.google.gson;


    exports com.example.NewInterfaz;
    opens com.example.NewInterfaz to javafx.fxml, com.google.gson;
    opens com.example.SaveInfo to com.google.gson;
    opens com.example.EstructurasDeDatos.Listas to com.google.gson;
    opens com.example.NewInterfaz.Individuos to com.google.gson;
    opens com.example.NewInterfaz.Grafo_Conocimiento to com.google.gson;

    exports com.example.EstructurasDeDatos to javafx.graphics;
}
