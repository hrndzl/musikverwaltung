package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Interpret;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CreateInterpretScreen {
    public VBox vBox = new VBox();
    public HBoxWithLabelAndTextField nameField = new HBoxWithLabelAndTextField("Name des Interpreten");
    public HBoxWithLabelAndTextField biografieField = new HBoxWithLabelAndTextField("Biografie des Interpreten");
    public Button createInterpret = new Button("Interpret erstellen");

    public CreateInterpretScreen() {
        createInterpret.setOnAction(event -> actionEventForCreateInterpretButton());
        vBox.getChildren().addAll(nameField.gethBox(), biografieField.gethBox(), createInterpret);
    }

    private void actionEventForCreateInterpretButton() {
        if (nameField.getText().isBlank()) {
            //TODO create an alert here
        } else {
            Interpret interpret = new Interpret(nameField.getText(), biografieField.getText());
            MusikVerwaltung.getInterpretenListe().add(interpret);
        }
    }
}
