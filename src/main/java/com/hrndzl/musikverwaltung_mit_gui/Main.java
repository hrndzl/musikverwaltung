package com.hrndzl.musikverwaltung_mit_gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {
    private static Button albumAnlegen = new Button("Album anlegen");
    private static Button interpretErstellen = new Button("Interpret erstellen");
    private static Button songErstellen = new Button("Song erstellen und zum Album hinzufügen");
    private static Button albumAnzeigen = new Button("Album anzeigen");
    private static Button interpretAnzeigen = new Button("Interpret anzeigen");
    private static Button eintragBearbeiten = new Button("Eintrag bearbeiten");
    private static Button biografieAnzeigen = new Button("Biografie Anzeigen");
    private static BorderPane root;
    private static boolean vonAlbumAnlegenAufInterpretErstellenZugewiesen = false;

    public static void rechtsUndMitteLeeren(){
        root.setCenter(null);
        root.setRight(null);
    }

    public static void funktionalitaetFuerAlbumAnlegen() {
        albumAnlegen.setOnAction(actionEvent -> {
            rechtsUndMitteLeeren();
            if(MusikVerwaltung.getInterpretenListe().isEmpty()) {
                vonAlbumAnlegenAufInterpretErstellenZugewiesen = true;
                root.setRight(new Label("Bevor Sie einen Album erstellen können, müssen Sie einen Interpreten erstellen!"));
                interpretErstellen.fire();
                return;
            }
            VBox vBox = new VBox(8);
            Label labelName = new Label("Name");
            TextField textFieldName = new TextField();
            HBox hBoxName = new HBox();
            hBoxName.getChildren().addAll(labelName, textFieldName);
            ComboBox<String> comboBox = new ComboBox<String>();
            for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                comboBox.getItems().add(interpret.getName());
            }
            Button button = new Button("Album anlegen");
            button.setOnAction(actionEvent1 -> {
                for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                    if(comboBox.getValue().equals(interpret.getName())) {
                        Album album = new Album(textFieldName.getText(), interpret);
                        interpret.getAlben().add(album);
                        MusikVerwaltung.getAlbenListe().add(album);
                    }
                }
            });
            vBox.getChildren().addAll(hBoxName, comboBox, button);
            root.setCenter(vBox);
        });
    }

    public static void funktionalitaetFuerInterpretErstellen() {
        interpretErstellen.setOnAction(actionEvent -> {
            if(!vonAlbumAnlegenAufInterpretErstellenZugewiesen) {
                rechtsUndMitteLeeren();
            }
            VBox vBoxInterpret = new VBox(8);

            Label labelInterpretName = new Label("Name des Interpreten");
            TextField textFieldInterpretName = new TextField();
            HBox hBoxInterpretName = new HBox(8);
            hBoxInterpretName.getChildren().addAll(labelInterpretName, textFieldInterpretName);

            Label labelInterpretBiografie = new Label("Biografie des Interpreten");
            TextArea textAreaInterpretBiografie = new TextArea();
            HBox hBoxInterpretBiografie = new HBox(8);
            hBoxInterpretBiografie.getChildren().addAll(labelInterpretBiografie, textAreaInterpretBiografie);

            Button interpretErstellen = new Button("Interpret erstellen");

            vBoxInterpret.getChildren().addAll(hBoxInterpretName, hBoxInterpretBiografie, interpretErstellen);

            root.setCenter(vBoxInterpret);
            interpretErstellen.setOnAction(actionEvent1 -> {
                Interpret interpret = new Interpret(textFieldInterpretName.getText(), textAreaInterpretBiografie.getText());
                if(interpret.getName().isBlank()) {
                    return;
                }
                vonAlbumAnlegenAufInterpretErstellenZugewiesen = false;
                MusikVerwaltung.getInterpretenListe().add(interpret);
            });
        });
    }

    public static void funktionalitaetFuerSongErstellen() {
        songErstellen.setOnAction(actionEvent -> {
            if(MusikVerwaltung.getAlbenListe().isEmpty()) {
                albumAnlegen.fire();
            }
            VBox songMenue = new VBox(8);
            Label labelSongName = new Label("Name des Songs");
            TextField textFieldSongName = new TextField();
            HBox hBoxSongName = new HBox(8);
            hBoxSongName.getChildren().addAll(labelSongName, textFieldSongName);

            Label labelSongDauer = new Label("Dauer des Songs in Sekunden");
            TextField textFieldSongDauer = new TextField();
            HBox hBoxSongDauer = new HBox(8);
            hBoxSongDauer.getChildren().addAll(labelSongDauer, textFieldSongDauer);

            Label labelSongNummer = new Label("Nummer des Songs im Album");
            TextField textFieldSongNummer = new TextField();
            HBox hBoxSongNummer = new HBox(8);
            hBoxSongNummer.getChildren().addAll(labelSongNummer, textFieldSongNummer);

            Label labelSongText = new Label("Songtext");
            TextArea textAreaSongText = new TextArea();
            HBox hBoxSongText = new HBox(8);
            hBoxSongText.getChildren().addAll(labelSongText, textAreaSongText);

            Label labelSongAlbum = new Label("Album");
            ComboBox<String> comboBoxAlben = new ComboBox<String>();
            for(Album album : MusikVerwaltung.getAlbenListe()) {
                comboBoxAlben.getItems().add(album.getName());
            }
            HBox hBoxSongAlbum = new HBox(8);
            hBoxSongAlbum.getChildren().addAll(labelSongAlbum, comboBoxAlben);

            Button erstellen = new Button("Song erstellen und zum Album hinzufügen");
            erstellen.setOnAction(actionEvent1 -> {
                Song song = new Song();
                if (textFieldSongName.getText().isBlank() || textFieldSongNummer.getText().isBlank() || textFieldSongDauer.getText().isBlank()) {
                    return;
                }
                else if(textAreaSongText.getText().isBlank()) {
                    song = new Song(textFieldSongName.getText(), Integer.valueOf(textFieldSongDauer.getText()), Integer.valueOf(textFieldSongNummer.getText()));
                }
                else if (!textAreaSongText.getText().isBlank()) {
                    song = new SongMitText(textFieldSongName.getText(), Integer.valueOf(textFieldSongDauer.getText()), Integer.valueOf(textFieldSongNummer.getText()), textAreaSongText.getText());
                }

                for(Album album : MusikVerwaltung.getAlbenListe()) {
                    if(album.getName().equals(comboBoxAlben.getValue())) {
                        for(Song loopSong : album.getInhalt()) {
                            if(song.getName().equalsIgnoreCase(loopSong.getName())) {
                                return;
                            }
                        }
                    }
                }

                for(Album album : MusikVerwaltung.getAlbenListe()) {
                    if(album.getName().equals(comboBoxAlben.getValue())) {
                        album.addSong(song);
                        break;
                    }
                }
            });
            songMenue.getChildren().addAll(hBoxSongName, hBoxSongNummer, hBoxSongDauer, hBoxSongText, hBoxSongAlbum, erstellen);
            root.setCenter(songMenue);
        });
    }

    public static void funktionalitaetFuerAlbumAnzeigen() {
        albumAnzeigen.setOnAction(actionEvent -> {
            rechtsUndMitteLeeren();
            ScrollPane scrollPane = new ScrollPane();
            VBox vBox = new VBox();
            TextFlow textFlow = new TextFlow();
            scrollPane.setContent(textFlow);
            for(Album album : MusikVerwaltung.getAlbenListe()) {
                Button button = new Button(album.getName());
                button.setOnAction(actionEvent1 -> {
                    String nummer = String.valueOf((MusikVerwaltung.getAlbenListe().indexOf(album) + 1));
                    String name = album.getName();
                    String interpret = album.getInterpret().getName();
                    String dauer = album.gesamtDauerString();
                    String titelAnzahl = String.valueOf(album.getTitelAnzahl());
                    textFlow.getChildren().add(new Text("Album Nummer: " + nummer));
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                    textFlow.getChildren().add(new Text("Name: " + name));
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                    textFlow.getChildren().add(new Text("Interpret: " + interpret));
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                    textFlow.getChildren().add(new Text("Dauer: " + dauer));
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                    textFlow.getChildren().add(new Text("Titelanzahl: " + titelAnzahl));
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                    for(Song song : album.getInhalt()) {
                        textFlow.getChildren().add(new Text("Titel " + song.getNummer() + ": " + song.getName()));
                        textFlow.getChildren().add(new Text(System.lineSeparator()));
                    }
                    textFlow.getChildren().add(new Text(System.lineSeparator()));
                    root.setRight(textFlow);
                });
                vBox.getChildren().add(button);
            }
            scrollPane.setContent(vBox);
            root.setCenter(scrollPane);
        });
    }

    public static void funktionalitaetFuerInterpretAnzeigen() {
        interpretAnzeigen.setOnAction(actionEvent -> {
            rechtsUndMitteLeeren();
            ScrollPane scrollPane = new ScrollPane();
            TextFlow textFlow = new TextFlow();
            textFlow.getChildren().add(new Text("Klicken Sie auf die Taste des Albums, von dem Sie den Interpreten erfahren möchten!"));
            VBox vBox = new VBox(8);
            vBox.getChildren().add(textFlow);
            for(Album album : MusikVerwaltung.getAlbenListe()) {
                Button button = new Button(album.getName());
                button.setOnAction(actionEvent1 -> {
                    root.setRight(new Text("Der Interpret des Albums " + album.getName() + " ist " + album.getInterpret().getName()));
                });
                vBox.getChildren().add(button);
            }
            scrollPane.setContent(vBox);
            root.setCenter(scrollPane);
        });
    }

    public static HBox songSuche() {
        HBox hBox = new HBox(8);
        TextField textField = new TextField();
        Button button = new Button("Song suchen");
        hBox.getChildren().addAll(textField, button);
        button.setOnAction(actionEvent -> {
            boolean gefunden = false;
            String nameDesAlbums = null;
            String nameGesuchterSong = textField.getText();
            outerloop:
            for(Album album : MusikVerwaltung.getAlbenListe()) {
                for(Song song : album.getInhalt()) {
                    if(nameGesuchterSong.equalsIgnoreCase(song.getName())) {
                        gefunden = true;
                        nameDesAlbums = album.getName();
                        break outerloop;
                    }
                }
            }
            if (gefunden) {
                root.setRight(new Text("Der Song wurde im Album " + nameDesAlbums + " gefunden."));
            } else {
                root.setRight(new Text("Der von Ihnen gesuchte Song konnte nicht gefunden werden."));
            }
        });
        root.setBottom(hBox);
        return hBox;
    }

    public static void funktionalitaetFuerEintragBearbeiten() {
        eintragBearbeiten.setOnAction(actionEvent -> {
            VBox vBox = new VBox(8);

            Button buttonInterpretBearbeiten = new Button("Einträge eines Interpreten bearbeiten");

            buttonInterpretBearbeiten.setOnAction(actionEvent1 -> {
                VBox vBoxInterpretenListe = new VBox(8);
                for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                    Button button = new Button(interpret.getName());
                    button.setOnAction(actionEvent2 -> {
                        VBox vBoxInterpretBearbeiten = new VBox(8);

                        Label labelInterpretName = new Label("Name des Interpreten");
                        TextField textFieldInterpretName = new TextField();
                        textFieldInterpretName.setText(interpret.getName());
                        HBox hBoxInterpretName = new HBox(8);
                        hBoxInterpretName.getChildren().addAll(labelInterpretName, textFieldInterpretName);

                        Label labelInterpretBiografie = new Label("Biografie des Interpreten");
                        TextArea textAreaInterpretBiografie = new TextArea();
                        textAreaInterpretBiografie.setText(interpret.getBiografie());
                        HBox hBoxInterpretBiografie = new HBox(8);
                        hBoxInterpretBiografie.getChildren().addAll(labelInterpretBiografie, textAreaInterpretBiografie);

                        Button eintraegeAendern = new Button("Einträge ändern");

                        eintraegeAendern.setOnAction(actionEvent3 -> {
                            interpret.setName(textFieldInterpretName.getText());
                            interpret.setBiografie(textAreaInterpretBiografie.getText());
                        });

                        vBoxInterpretBearbeiten.getChildren().addAll(hBoxInterpretName, hBoxInterpretBiografie, eintraegeAendern);

                        root.setCenter(vBoxInterpretBearbeiten);
                    });
                    vBoxInterpretenListe.getChildren().add(button);
                    root.setCenter(vBoxInterpretenListe);
                }
            });

            Button buttonSongBearbeiten = new Button("Song bearbeiten");

            buttonSongBearbeiten.setOnAction(actionEvent1 -> {
                VBox vBoxAlbenListe = new VBox(8);

                for(Album album : MusikVerwaltung.getAlbenListe()) {
                    Button buttonAlbum = new Button(album.getName());
                    buttonAlbum.setOnAction(actionEvent2 -> {
                        for(Song songImAlbum : album.getInhalt()) {
                            VBox vBoxButtonSong = new VBox(8);
                            Button buttonSong = new Button(songImAlbum.getName());
                            buttonSong.setOnAction(actionEvent3 -> {
                                VBox songMenue = new VBox(8);
                                Label labelSongName = new Label("Name des Songs");
                                TextField textFieldSongName = new TextField();
                                textFieldSongName.setText(songImAlbum.getName());
                                HBox hBoxSongName = new HBox(8);
                                hBoxSongName.getChildren().addAll(labelSongName, textFieldSongName);

                                Label labelSongDauer = new Label("Dauer des Songs in Sekunden");
                                TextField textFieldSongDauer = new TextField();
                                textFieldSongDauer.setText(String.valueOf(songImAlbum.getDauerInSekunden()));
                                HBox hBoxSongDauer = new HBox(8);
                                hBoxSongDauer.getChildren().addAll(labelSongDauer, textFieldSongDauer);

                                Label labelSongNummer = new Label("Nummer des Songs im Album");
                                TextField textFieldSongNummer = new TextField();
                                textFieldSongNummer.setText(String.valueOf(songImAlbum.getNummer()));
                                HBox hBoxSongNummer = new HBox(8);
                                hBoxSongNummer.getChildren().addAll(labelSongNummer, textFieldSongNummer);

                                ComboBox<String> comboBoxAlbum = new ComboBox<String>();
                                for(Album loopAlbum : MusikVerwaltung.getAlbenListe()) {
                                    comboBoxAlbum.getItems().add(loopAlbum.getName());
                                }

                                Label labelSongText = new Label("Songtext");
                                TextArea textAreaSongText = new TextArea();
                                HBox hBoxSongText = new HBox(8);

                                if(songImAlbum instanceof SongMitText) {
                                    textAreaSongText.setText(((SongMitText) songImAlbum).getSongText());
                                    hBoxSongText.getChildren().addAll(labelSongText, textAreaSongText);
                                }

                                Button buttonEintraeageBearbeiten = new Button("Einträge bearbeiten");
                                buttonEintraeageBearbeiten.setOnAction(actionEvent4 -> {
                                    songImAlbum.setName(textFieldSongName.getText());
                                    songImAlbum.setNummer(Integer.valueOf(textFieldSongNummer.getText()));
                                    songImAlbum.setDauerInSekunden(Integer.valueOf(textFieldSongDauer.getText()));
                                    if(songImAlbum instanceof SongMitText) {
                                        ((SongMitText) songImAlbum).setSongText(textAreaSongText.getText());
                                    }
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
                                if(songImAlbum instanceof SongMitText) {
                                    songMenue.getChildren().addAll(hBoxSongName, hBoxSongNummer, hBoxSongDauer, comboBoxAlbum, hBoxSongText, buttonEintraeageBearbeiten);
                                } else {
                                    songMenue.getChildren().addAll(hBoxSongName, hBoxSongNummer, hBoxSongDauer, comboBoxAlbum, buttonEintraeageBearbeiten);
                                }
                                root.setCenter(songMenue);
                            });
                            vBoxButtonSong.getChildren().add(buttonSong);
                            root.setCenter(vBoxButtonSong);
                        }
                    });
                    vBoxAlbenListe.getChildren().add(buttonAlbum);
                    root.setCenter(vBoxAlbenListe);
                }
            });

            Button buttonAlbumBearbeiten = new Button("Album bearbeiten");
            buttonAlbumBearbeiten.setOnAction(actionEvent1 -> {
                VBox vBoxAlbumListe = new VBox(8);
                for(Album album : MusikVerwaltung.getAlbenListe()) {
                    Button buttonAlbum = new Button(album.getName());
                    buttonAlbum.setOnAction(actionEvent2 -> {
                        VBox vBoxAlbumDaten = new VBox(8);

                        Label labelAlbumName = new Label("Name");
                        TextField textFieldAlbumName = new TextField();
                        textFieldAlbumName.setText(album.getName());
                        HBox hBoxAlbumName = new HBox();
                        hBoxAlbumName.getChildren().addAll(labelAlbumName, textFieldAlbumName);

                        ComboBox<String> comboBox = new ComboBox<String>();
                        for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                            comboBox.getItems().add(interpret.getName());
                            if(interpret.getName().equals(album.getInterpret().getName())) {
                                comboBox.getSelectionModel().select(MusikVerwaltung.getInterpretenListe().indexOf(interpret));
                            }
                        }

                        Button eintraegeAendern = new Button("Einträge ändern");
                        eintraegeAendern.setOnAction(actionEvent3 -> {
                            album.setName(textFieldAlbumName.getText());
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
                        vBoxAlbumDaten.getChildren().addAll(hBoxAlbumName, comboBox, eintraegeAendern);
                        root.setCenter(vBoxAlbumDaten);
                    });
                    vBoxAlbumListe.getChildren().add(buttonAlbum);
                }
                root.setCenter(vBoxAlbumListe);
            });

            vBox.getChildren().addAll(buttonInterpretBearbeiten, buttonSongBearbeiten, buttonAlbumBearbeiten);
            root.setCenter(vBox);
        });
    }

    public static void funktionalitaetFuerBiografieAnzeigen() {
        biografieAnzeigen.setOnAction(actionEvent -> {
            VBox vBox = new VBox(8);
            for(Interpret interpret : MusikVerwaltung.getInterpretenListe()) {
                Button button = new Button(interpret.getName());
                button.setOnAction(actionEvent1 -> {
                    TextFlow textFlow = new TextFlow();
                    textFlow.getChildren().addAll(new Text("Biografie des Interpreten " + interpret.getName() + ":"), new Text(System.lineSeparator()), new Text(interpret.getBiografie()));
                    root.setRight(textFlow);
                });
                vBox.getChildren().add(button);
            }
            root.setCenter(vBox);
        });
    }

    public static void funktionalitaetDerButtonsZuweisen() {
        funktionalitaetFuerInterpretErstellen();
        funktionalitaetFuerAlbumAnzeigen();
        funktionalitaetFuerSongErstellen();
        funktionalitaetFuerInterpretAnzeigen();
        funktionalitaetFuerAlbumAnlegen();
        funktionalitaetFuerEintragBearbeiten();
        funktionalitaetFuerBiografieAnzeigen();
    }

    public static VBox navigationsMenu() {
        VBox vBox = new VBox(8);
        vBox.getChildren().addAll(albumAnlegen, interpretErstellen, songErstellen, albumAnzeigen, interpretAnzeigen, eintragBearbeiten, biografieAnzeigen);
        return vBox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        funktionalitaetDerButtonsZuweisen();
        root = new BorderPane();
        stage.setTitle("Musikverwaltung");
        stage.setScene(new Scene(root, 960, 480));
        root.setLeft(navigationsMenu());
        root.setBottom(songSuche());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}