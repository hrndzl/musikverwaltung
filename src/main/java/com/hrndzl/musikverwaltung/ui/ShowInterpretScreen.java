package com.hrndzl.musikverwaltung.ui;

import com.hrndzl.musikverwaltung.Album;
import com.hrndzl.musikverwaltung.MusikVerwaltung;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ShowInterpretScreen {
    ScrollPane scrollPane = new ScrollPane();
    TextFlow textFlow = new TextFlow();
    VBox vBox = new VBox(8);

    public ShowInterpretScreen() {
        createButtonForEveryAlbum();
        textFlow.getChildren().add(new Text("Klicken Sie auf die Taste des Albums, von dem Sie den Interpreten erfahren möchten!"));
        vBox.getChildren().add(textFlow);
        scrollPane.setContent(vBox);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    private void createButtonForEveryAlbum() {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            Button button = new Button(album.getName());
            button.setOnAction(onClick -> {
                Main.root.setRight(new Text("Der Interpret des Albums " + album.getName() + " ist " + album.getInterpret().getName()));
            });
            vBox.getChildren().add(button);
        }
    }
}
