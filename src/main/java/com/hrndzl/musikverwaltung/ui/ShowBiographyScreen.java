package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Interpret;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ShowBiographyScreen {
    private VBox vBox = new VBox(8);

    public ShowBiographyScreen() {
        createButtonForEveryInterpretAndAddActionEvent();
    }

    public VBox getvBox() {
        return vBox;
    }

    private void createButtonForEveryInterpretAndAddActionEvent() {
        for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
            Button buttonForInterpret = new Button(interpret.getName());
            buttonForInterpret.setOnAction(event -> eventForPressingAnInterpretsButton(interpret));
            vBox.getChildren().add(buttonForInterpret);
        }
    }

    private void eventForPressingAnInterpretsButton(Interpret interpret) {
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(new Text("Biografie des Interpreten " + interpret.getName() + ":"), new Text(System.lineSeparator()), new Text(interpret.getBiografie()));
        Main.root.setRight(textFlow);
    }
}
