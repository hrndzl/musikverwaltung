module com.example.musikverwaltung_mit_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hrndzl.musikverwaltung_mit_gui to javafx.fxml;
    exports com.hrndzl.musikverwaltung_mit_gui;
}