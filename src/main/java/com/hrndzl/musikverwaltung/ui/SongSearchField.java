package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import com.hrndzl.musikverwaltung.Song;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SongSearchField {
    private HBox hBox = new HBox(8);
    private TextField textField = new TextField();
    private Button searchSong = new Button("Song suchen");

    public SongSearchField() {
        searchSong.setOnAction(event -> eventForSearchSong());
        hBox.getChildren().addAll(textField, searchSong);
    }

    public HBox gethBox() {
        return hBox;
    }

    private void eventForSearchSong() {
        String nameDesAlbums = MusikVerwaltung.inWhichAlbumIsTheSong(textField.getText()).getName();

        if (nameDesAlbums != null) {
            Main.root.setRight(new Text("Der Song wurde im Album " + nameDesAlbums + " gefunden."));
        } else {
            Main.root.setRight(new Text("Der von Ihnen gesuchte Song konnte nicht gefunden werden."));
        }
    }
}
