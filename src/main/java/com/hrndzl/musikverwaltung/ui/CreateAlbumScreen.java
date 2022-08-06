package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.Interpret;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class CreateAlbumScreen {
    public VBox vBox = new VBox(8);
    public HBoxWithLabelAndTextField nameField = new HBoxWithLabelAndTextField("Name");
    public ComboBox<String> interpreten = new ComboBox<>();
    public Button createAlbum = new Button("Album anlegen");

    public CreateAlbumScreen() {
        fillComboBoxWithInterpretNames();
        createAlbum.setOnAction(event -> actionEventForCreateAlbumButton());
        vBox.getChildren().addAll(nameField.gethBox(), interpreten, createAlbum);
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public HBoxWithLabelAndTextField getNameField() {
        return nameField;
    }

    public void setNameField(HBoxWithLabelAndTextField nameField) {
        this.nameField = nameField;
    }

    public ComboBox<String> getAlben() {
        return interpreten;
    }

    public void setAlben(ComboBox<String> interpreten) {
        this.interpreten = interpreten;
    }

    public Button getCreateAlbum() {
        return createAlbum;
    }

    public void setCreateAlbum(Button createAlbum) {
        this.createAlbum = createAlbum;
    }

    private void fillComboBoxWithInterpretNames() {
        for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
            interpreten.getItems().add(interpret.getName());
        }
    }

    private void actionEventForCreateAlbumButton() {
        for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
            Album album = new Album(nameField.getText(), interpret);
            interpret.getAlben().add(album);
            MusikVerwaltung.getAlbenListe().add(album);
        }
    }
}
