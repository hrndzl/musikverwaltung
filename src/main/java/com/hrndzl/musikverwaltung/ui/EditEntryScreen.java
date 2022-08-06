package com.hrndzl.musikverwaltung.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EditEntryScreen {
    private VBox vBox = new VBox(8);
    private Button buttonInterpretBearbeiten = new Button("Einträge eines Interpreten bearbeiten");
    private Button buttonSongBearbeiten = new Button("Song bearbeiten");
    private Button buttonAlbumBearbeiten = new Button("Album bearbeiten");

    public EditEntryScreen() {
        buttonInterpretBearbeiten.setOnAction(onClick -> Main.root.setCenter(new EditInterpretScreen().getvBoxInterpretenListe()));
        buttonSongBearbeiten.setOnAction(onClick -> Main.root.setCenter(new EditSongScreen().getvBoxAlbenListe()));
        buttonAlbumBearbeiten.setOnAction(onClick -> Main.root.setCenter(new EditAlbumScreen().getvBoxAlbumListe()));
        vBox.getChildren().addAll(buttonInterpretBearbeiten, buttonSongBearbeiten, buttonAlbumBearbeiten);
    }

    public VBox getvBox() {
        return vBox;
    }
}
