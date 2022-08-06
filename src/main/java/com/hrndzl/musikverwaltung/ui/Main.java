package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.*;
import com.hrndzl.musikverwaltung.ui.HBoxWithLabelAndTextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {
    public static NavBar navBar = new NavBar();
    public static BorderPane root;
    public static boolean vonAlbumAnlegenAufInterpretErstellenZugewiesen = false;

    public static void rechtsUndMitteLeeren(){
        root.setCenter(null);
        root.setRight(null);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = new BorderPane();
        stage.setTitle("Musikverwaltung");
        stage.setScene(new Scene(root, 960, 480));
        root.setLeft(navBar.getNavBar());
        root.setBottom(new SongSearchField().gethBox());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}