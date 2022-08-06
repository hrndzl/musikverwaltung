module com.example.musikverwaltung_mit_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hrndzl.musikverwaltung to javafx.fxml;
    exports com.hrndzl.musikverwaltung;
    exports com.hrndzl.musikverwaltung.ui;
    opens com.hrndzl.musikverwaltung.ui to javafx.fxml;
}