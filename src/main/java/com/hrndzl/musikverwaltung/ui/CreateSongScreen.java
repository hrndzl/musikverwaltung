package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import com.hrndzl.musikverwaltung.Song;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateSongScreen {
    private HBoxWithLabelAndTextField nameField = new HBoxWithLabelAndTextField("Name des Songs: ");
    private HBoxWithLabelAndTextField dauerField = new HBoxWithLabelAndTextField("Dauer des Songs in Sekunden: ");
    private HBoxWithLabelAndTextField numberField = new HBoxWithLabelAndTextField("Nummer des Songs im Album: ");
    private HBoxWithLabelAndTextArea songTextField = new HBoxWithLabelAndTextArea("Songtext: ");
    private VBox songMenu = new VBox(8);
    private Label labelSongAlbum = new Label("Album");
    private ComboBox<String> comboBoxAlben = new ComboBox<>();
    private HBox hBoxSongAlbum = new HBox(8);
    private Button songErstellen = new Button("Song erstellen und zum Album hinzufügen");

    public CreateSongScreen() {
        songErstellen.setOnAction(actionEvent -> actionEventForCreateSongButton());
        fillComboBoxWithAlbumNames();
        hBoxSongAlbum.getChildren().addAll(labelSongAlbum, comboBoxAlben);
        songMenu.getChildren().addAll(nameField.gethBox(), numberField.gethBox(), dauerField.gethBox(), songTextField.gethBox(), hBoxSongAlbum, songErstellen);
    }

    public VBox getSongMenu() {
        return songMenu;
    }

    private void fillComboBoxWithAlbumNames() {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            comboBoxAlben.getItems().add(album.getName());
        }
    }

    public void actionEventForCreateSongButton() {
        if (nameField.getText().isBlank() || numberField.getText().isBlank() || dauerField.getText().isBlank()) {
            //TODO create an alert here
        }
        else if(MusikVerwaltung.songIsAlreadyCreated(comboBoxAlben.getValue(), nameField.getText())) {
            //todo create an alert here
        }
        else {
            Song song = new Song(nameField.getText(), Integer.parseInt(dauerField.getText()), Integer.parseInt(numberField.getText()), songTextField.getText());
            for(Album album : MusikVerwaltung.getAlbenListe()) {
                if(album.getName().equals(comboBoxAlben.getValue())) {
                    album.addSong(song);
                    break;
                }
            }
        }
    }
}
