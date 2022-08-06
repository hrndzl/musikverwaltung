package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import com.hrndzl.musikverwaltung.Song;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class EditSongScreen {
    private VBox vBoxAlbenListe = new VBox(8);

    public EditSongScreen() {
        createButtonForEveryAlbumAndEverySongInIt();
    }

    public VBox getvBoxAlbenListe() {
        return vBoxAlbenListe;
    }

    private void createButtonForEveryAlbumAndEverySongInIt() {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            Button buttonAlbum = new Button(album.getName());
            buttonAlbum.setOnAction(onClick -> {
                for(Song songImAlbum : album.getInhalt()) {
                    VBox vBoxButtonSong = new VBox(8);
                    Button buttonSong = new Button(songImAlbum.getName());
                    buttonSong.setOnAction(actionEvent3 -> {
                        VBox songMenue = new VBox(8);

                        HBoxWithLabelAndTextField nameField = new HBoxWithLabelAndTextField("Name des Songs: ");
                        nameField.getTextField().setText(songImAlbum.getName());
                        HBoxWithLabelAndTextField dauerField = new HBoxWithLabelAndTextField("Dauer des Songs in Sekunden ");
                        dauerField.getTextField().setText(String.valueOf(songImAlbum.getDauerInSekunden()));
                        HBoxWithLabelAndTextField nummerField = new HBoxWithLabelAndTextField("Nummer des Songs im Album: ");
                        nummerField.getTextField().setText(String.valueOf(songImAlbum.getNummer()));
                        ComboBox<String> comboBoxAlbum = new ComboBox<>();

                        for(Album loopAlbum : MusikVerwaltung.getAlbenListe()) {
                            comboBoxAlbum.getItems().add(loopAlbum.getName());
                            if(loopAlbum.getName().equals(album.getInterpret().getName())) {
                                comboBoxAlbum.getSelectionModel().select(MusikVerwaltung.getInterpretenListe().indexOf(loopAlbum));
                            }
                        }

                        HBoxWithLabelAndTextArea songTextArea = new HBoxWithLabelAndTextArea("Songtext: ");
                        songTextArea.getTextArea().setText(songImAlbum.getSongText());

                        Button buttonEintraeageBearbeiten = new Button("Einträge bearbeiten");
                        buttonEintraeageBearbeiten.setOnAction(actionEvent4 -> {
                            songImAlbum.setName(nameField.getText());
                            songImAlbum.setNummer(Integer.valueOf(nummerField.getText()));
                            songImAlbum.setDauerInSekunden(Integer.valueOf(dauerField.getText()));

                            if(!comboBoxAlbum.getValue().equals(songImAlbum.inAlbum.getName())) {
                                for(Album loopAlbum : MusikVerwaltung.getAlbenListe()) {
                                    if(loopAlbum.getName().equals(comboBoxAlbum.getValue())) {
                                        loopAlbum.addSong(songImAlbum);
                                        songImAlbum.inAlbum = loopAlbum;
                                        album.getInhalt().remove(songImAlbum);
                                    }
                                }
                            }
                        });

                        songMenue.getChildren().addAll(nameField.gethBox(), nummerField.gethBox(), dauerField.gethBox(), comboBoxAlbum, songTextArea.gethBox(), buttonEintraeageBearbeiten);
                        Main.root.setCenter(songMenue);
                    });
                    vBoxButtonSong.getChildren().add(buttonSong);
                    Main.root.setCenter(vBoxButtonSong);
                }
            });
            vBoxAlbenListe.getChildren().add(buttonAlbum);
        }
    }
}
