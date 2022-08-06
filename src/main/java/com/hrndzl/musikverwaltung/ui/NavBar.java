package com.hrndzl.musikverwaltung.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavBar {
    private VBox navBar = new VBox(8);
    private Button albumAnlegen = new Button("Album anlegen");
    private Button interpretErstellen = new Button("Interpret erstellen");
    private Button songErstellen = new Button("Song erstellen und zum Album hinzufügen");
    private Button albumAnzeigen = new Button("Album anzeigen");
    private Button interpretAnzeigen = new Button("Interpret anzeigen");
    private Button eintragBearbeiten = new Button("Eintrag bearbeiten");
    private Button biografieAnzeigen = new Button("Biografie Anzeigen");

    public NavBar() {
        this.navBar.getChildren().addAll(albumAnlegen, interpretErstellen, songErstellen, albumAnzeigen, interpretAnzeigen, eintragBearbeiten, biografieAnzeigen);
        this.assignFunctionalityToButtons();
    }

    public VBox getNavBar() {
        return navBar;
    }

    public void assignFunctionalityToButtons() {
        albumAnlegen.setOnAction(event -> Main.root.setCenter(new CreateAlbumScreen().vBox));
        interpretErstellen.setOnAction(event -> Main.root.setCenter(new CreateInterpretScreen().vBox));
        biografieAnzeigen.setOnAction(event -> Main.root.setCenter(new ShowBiographyScreen().getvBox()));
        songErstellen.setOnAction(event -> Main.root.setCenter(new CreateSongScreen().getSongMenu()));
        interpretAnzeigen.setOnAction(event -> Main.root.setCenter(new ShowInterpretScreen().getScrollPane()));
        albumAnzeigen.setOnAction(event -> Main.root.setCenter(new ShowAlbumScreen().getScrollPane()));
        eintragBearbeiten.setOnAction(event -> Main.root.setCenter(new EditEntryScreen().getvBox()));
    }
}
