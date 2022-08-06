package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.Interpret;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class EditAlbumScreen {
    private VBox vBoxAlbumListe = new VBox(8);

    public EditAlbumScreen() {
        createButtonForEveryAlbum();
    }

    public VBox getvBoxAlbumListe() {
        return vBoxAlbumListe;
    }

    public void createButtonForEveryAlbum() {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            Button buttonAlbum = new Button(album.getName());
            buttonAlbum.setOnAction(actionEvent2 -> {
                VBox vBoxAlbumDaten = new VBox(8);

                HBoxWithLabelAndTextField nameField = new HBoxWithLabelAndTextField("Name :");
                nameField.getTextField().setText(album.getName());

                ComboBox<String> comboBox = new ComboBox<>();
                for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                    comboBox.getItems().add(interpret.getName());
                    if(interpret.getName().equals(album.getInterpret().getName())) {
                        comboBox.getSelectionModel().select(MusikVerwaltung.getInterpretenListe().indexOf(interpret));
                    }
                }

                Button eintraegeAendern = new Button("Einträge ändern");
                eintraegeAendern.setOnAction(actionEvent3 -> {
                    album.setName(nameField.getText());
                    if(!album.getInterpret().getName().equals(comboBox.getValue())) {
                        for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                            if(interpret.getName().equals(comboBox.getValue())) {
                                interpret.getAlben().add(album);
                                for(Interpret loopInterpret : MusikVerwaltung.getInterpretenListe()) {
                                    if(loopInterpret.getName().equals(interpret.getName())) {
                                        loopInterpret.getAlben().remove(album);
                                    }
                                }
                                album.setInterpret(interpret);
                            }
                        }
                    }
                });
                vBoxAlbumDaten.getChildren().addAll(nameField.gethBox(), comboBox, eintraegeAendern);
                Main.root.setCenter(vBoxAlbumDaten);
            });
            vBoxAlbumListe.getChildren().add(buttonAlbum);
        }
    }
}
