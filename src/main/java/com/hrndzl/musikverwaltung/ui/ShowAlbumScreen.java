package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import com.hrndzl.musikverwaltung.Song;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ShowAlbumScreen {
    private ScrollPane scrollPane = new ScrollPane();
    private VBox vBox = new VBox();
    private TextFlow textFlow = new TextFlow();

    public ShowAlbumScreen() {
        createButtonForEveryAlbum();
        scrollPane.setContent(vBox);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    private void createButtonForEveryAlbum() {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            Button button = new Button(album.getName());
            button.setOnAction(onClick -> {
                textFlow.getChildren().add(new Text("Album Nummer: " + String.valueOf((MusikVerwaltung.getAlbenListe().indexOf(album) + 1))));
                textFlow.getChildren().add(new Text(System.lineSeparator()));
                textFlow.getChildren().add(new Text("Name: " + album.getName()));
                textFlow.getChildren().add(new Text(System.lineSeparator()));
                textFlow.getChildren().add(new Text("Interpret: " + album.getInterpret().getName()));
                textFlow.getChildren().add(new Text(System.lineSeparator()));
                textFlow.getChildren().add(new Text("Dauer: " + album.gesamtDauerString()));
                textFlow.getChildren().add(new Text(System.lineSeparator()));
                textFlow.getChildren().add(new Text("Titelanzahl: " + String.valueOf(album.getTitelAnzahl())));
                textFlow.getChildren().add(new Text(System.lineSeparator()));
                for(Song song : album.getInhalt()) {
                    textFlow.getChildren().add(new Text("Titel " + song.getNummer() + ": " + song.getName()));
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                }
                textFlow.getChildren().add(new Text(System.lineSeparator()));
                Main.root.setRight(textFlow);
            });
            vBox.getChildren().add(button);
        }

    }
}
