package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Interpret;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EditInterpretScreen {
    private VBox vBoxInterpretenListe = new VBox(8);

    public EditInterpretScreen() {
        createButtonForEveryInterpretToBeEdited();
    }

    public VBox getvBoxInterpretenListe() {
        return vBoxInterpretenListe;
    }

    private void createButtonForEveryInterpretToBeEdited() {
        for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
            Button button = new Button(interpret.getName());
            button.setOnAction(onClick -> {
                VBox vBoxInterpretBearbeiten = new VBox(8);

                HBoxWithLabelAndTextField nameField = new HBoxWithLabelAndTextField("Name des Interpreten: ");
                nameField.getTextField().setText(interpret.getName());
                HBoxWithLabelAndTextField biografieField = new HBoxWithLabelAndTextField("Biografie des Interpreten: ");
                biografieField.getTextField().setText(interpret.getBiografie());

                Button eintraegeAendern = new Button("Einträge ändern");

                eintraegeAendern.setOnAction(onClick1 -> {
                    interpret.setName(nameField.getText());
                    interpret.setBiografie(biografieField.getText());
                });

                vBoxInterpretBearbeiten.getChildren().addAll(nameField.gethBox(), biografieField.gethBox(), eintraegeAendern);

                Main.root.setCenter(vBoxInterpretBearbeiten);
            });
            vBoxInterpretenListe.getChildren().add(button);
        }
    }
}
