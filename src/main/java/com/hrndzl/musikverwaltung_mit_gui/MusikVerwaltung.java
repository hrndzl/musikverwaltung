package com.hrndzl.musikverwaltung_mit_gui;

import java.util.ArrayList;
import java.util.Scanner;

public class MusikVerwaltung {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> albenListe = new ArrayList<Album>();
    private static ArrayList<Interpret> interpretenListe = new ArrayList<Interpret>();
    private static final String FEHLER_MELDUNG = "Es ist ein Fehler aufgetreten. Versuche bitte noch einmal!";

    public static ArrayList<Album> getAlbenListe() {
        return albenListe;
    }

    public static void setAlbenListe(ArrayList<Album> albenListe) {
        MusikVerwaltung.albenListe = albenListe;
    }

    public static ArrayList<Interpret> getInterpretenListe() {
        return interpretenListe;
    }

    public static void setInterpretenListe(ArrayList<Interpret> interpretenListe) {
        MusikVerwaltung.interpretenListe = interpretenListe;
    }

    public static Album neuenAlbumErstellenUndNamenZuweisen() {
        Album album = new Album();
        album.setName(stringAnfordern("Gebe den Namen für den Album ein: "));
        return album;
    }

    public static void aktualisiereInterpretenListe() {
        interpretenListe.clear();
        for(Album alben : albenListe) {
            interpretenListe.add(alben.getInterpret());
        }
    }

    public static Interpret interpretErstellenOderVerwenden() throws InterruptedException {
        aktualisiereInterpretenListe();
        Interpret interpret = null;
        System.out.println("Möchtest du einen neuen Interpreten erstellen oder einen bereits erstellten Interpreten verwenden?");
        Thread.sleep(100);
        System.out.println("Gebe \"erstellen\" oder \"verwenden\" ein.");
        switch(scanner.nextLine()) {
            case "erstellen": {
                interpret = new Interpret();
                interpret.setName(stringAnfordern("Gebe den Namen des Interpreten ein: "));
                for (Interpret tempInterpret : interpretenListe) {
                    if (tempInterpret.getName().equalsIgnoreCase(interpret.getName())) {
                        System.out.println("Einen Interpreten mit diesen Namen gibt es bereits!");
                        interpret = tempInterpret;
                        break;
                    }
                }
                interpretenListe.add(interpret);
                interpret.setBiografie(stringAnfordern("Gebe jetzt die Biografie des Interpreten ein: "));
                break;
            }

            case "verwenden": {
                System.out.println("Aktuell gespeicherte Interpreten sind: ");
                for (Album album : albenListe) {
                    System.out.println(album.getInterpret().getName());
                }
                String nameDesInterpreten = stringAnfordern("Gebe den vollen Namen des Interpreten ein, de für diesen Album eintragen möchten:");
                for (Interpret tempInterpret : interpretenListe) {
                    if (tempInterpret.getName().equalsIgnoreCase(nameDesInterpreten)) {
                        interpret = tempInterpret;
                        break;
                    }
                }
                break;
            }

            default: System.out.println(FEHLER_MELDUNG); break;
        }
        return interpret;
    }

    public static double doubleAnfordern(String msg) {
        System.out.println(msg);
        double laenge = 0;
        try {
            laenge = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println(FEHLER_MELDUNG);
            scanner = new Scanner(System.in);
        }
        return laenge;
    }

    public static int intAnfordern(String msg) {
        System.out.println(msg);
        int anzahl = 0;
        try {
            anzahl = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(FEHLER_MELDUNG);
            scanner = new Scanner(System.in);
        }
        return anzahl;
    }

    public static String stringAnfordern(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    public static Album albumErstellenUndInArrayListHinzufuegen() throws InterruptedException {
        Album album = neuenAlbumErstellenUndNamenZuweisen();
        Interpret interpret = interpretErstellenOderVerwenden();
        album.setInterpret(interpret);
        albenListe.add(album);
        interpret.getAlben().add(album);
        System.out.println("Dein Album wurde jetzt erstellt und gespeichert!");
        return album;
    }

    public static void songErstellenUndImAlbumHinzufuegen() throws InterruptedException {
        if(albenListe.isEmpty()) {
            System.out.println("Du hast noch keine Alben erstellt, erstelle bitte zuerst Alben!");
            return;
        }
        Song song = new Song(null, 0, 0);
        String songName = stringAnfordern("Gebe den Namen des Songs ein: ");
        int dauerDesSongsInSekunden = intAnfordern("Gebe die Dauer des Songs in Sekunden ein: ");
        int nummerDesSongs = intAnfordern("Gebe die Nummer des Songs ein: ");
        scanner = new Scanner(System.in);
        String operator = stringAnfordern("Möchtest du diesem Song auch einen Songtext zuweisen? Tippe \"ja\" oder \"nein\": ");
        switch(operator) {
            case "ja": {
                String songText = stringAnfordern("Gebe bitte den Text zum Song ein: ");
                song = new SongMitText(songName, dauerDesSongsInSekunden, nummerDesSongs, songText);
                break;
            }
            case "nein": {
                song = new Song(songName, dauerDesSongsInSekunden, nummerDesSongs);
                break;
            }
            default: System.out.println(FEHLER_MELDUNG);
        }
        System.out.println("Song erstellt!");
        Thread.sleep(100);
        System.out.println("In welchem Album möchtest du diesen Song hinzufügen?");
        for (Album album : albenListe) {
            System.out.println((albenListe.indexOf(album) + 1) + ": " + album.getName());
        }
        int index = (intAnfordern("Gebe die Nummer des Albums ein, dem du diesen Song hinzufügen möchten: ") - 1);
        albenListe.get(index).addSong(song);
    }

    public static void albenAusgeben() throws InterruptedException {
        for(Album album : albenListe) {
            System.out.println("Album Nummer " + (albenListe.indexOf(album) + 1));
            album.ausgeben();
            Thread.sleep(1000);
            System.out.println();
        }
    }

    public static Interpret interpretAnzeigen() {
        System.out.println("Hier ist die Liste der Alben, die im Programm gespeichert sind: ");
        for(Album album : albenListe) {
            System.out.println((albenListe.indexOf(album) + 1) + ": " + album.getName());
        }
        int index = (intAnfordern("Gebe die Nummer des Albums ein, dessen Interpret du erfahren möchten: ") - 1);
        System.out.println("Der Interpret des Albums " + albenListe.get(index).getName() + " ist " + albenListe.get(index).getInterpret().getName() + ".");
        return albenListe.get(index).getInterpret();
    }

    public static Song songSuchen() {
        String nameGesuchterSong = stringAnfordern("Gebe bitte den vollen Namen des Songs ein: ");
        Song gesuchterSong = null;
        boolean songGefunden = false;
        outerloop:
        for(Album album : albenListe) {
            for(Song song : album.getInhalt()) {
                if (song.getName().equalsIgnoreCase(nameGesuchterSong)) {
                    System.out.println("Song gefunden im Album " + album.getName());
                    songGefunden = true;
                    gesuchterSong = song;
                    break outerloop;
                }
            }
        }
        if(!songGefunden) {
            System.out.println("Der von Ihnen gesuchte Song ist leider nicht gespeichert!");
        }
        return gesuchterSong;
    }

    public static void eintragVonInterpretAendern() {
        System.out.println("Im Programm sind folgende Interpreten gespeichert: ");
        for(Interpret interpret: interpretenListe) {
            System.out.println((interpretenListe.indexOf(interpret) + 1) + ": " + interpret.getName());
        }
        int index = (intAnfordern("Geben Sie die Nummer von dem Interpreten ein, dessen Eintrag Sie bearbiten möchten: ") - 1);
        scanner = new Scanner(System.in);
        String operation = stringAnfordern("Was möchten Sie an diesem Interpreten verändern? Mögliche Eingaben sind \"name\", \"biografie\"");
        switch(operation) {
            case "name": {
                interpretenListe.get(index).setName(stringAnfordern("Geben Sie den neuen Namen für diesen Interpreten ein: "));
                break;
            }
            case "biografie": {
                interpretenListe.get(index).setBiografie(stringAnfordern("Geben Sie die neue Biografie für diesen Interpreten ein: "));
                break;
            }
            default: System.out.println(FEHLER_MELDUNG); break;
        }
    }

    public static void eintragVonSongAendern() {
        Song song = songSuchen();
        String operation = stringAnfordern("Was möchten Sie an diesem Song verändern? Mögliche Eingaben sind \"name\", \"dauer\", \"nummer\":");
        switch(operation) {
            case "name": {
                song.setName(stringAnfordern("Geben Sie den neuen Namen für diesen Song ein: "));
                break;
            }
            case "dauer": {
                song.setDauerInSekunden(intAnfordern("Geben Sie die neue Dauer für diesen Song ein: "));
                break;
            }
            case "nummer": {
                song.setNummer(intAnfordern("Geben Sie die neue Nummer für diesen song ein: "));
                break;
            }
            default: System.out.println(FEHLER_MELDUNG); break;
        }
    }

    public static void eintragVonAlbumAendern() throws InterruptedException {
        System.out.println("Im Programm sind folgende Alben gespeichert: ");
        for(Album album: albenListe) {
            System.out.println((albenListe.indexOf(album) + 1) + ": " + album.getName());
        }
        int index = (intAnfordern("Geben Sie die Nummer von dem Album ein, dessen Eintrag Sie bearbiten möchten: ") - 1);
        String operation = stringAnfordern("Was möchten Sie an diesem Album verändern? Mögliche Eingaben sind \"name\", \"interpret\"");
        switch(operation) {
            case "name": {
                albenListe.get(index).setName(stringAnfordern("Geben Sie den neuen Namen für diesen Album ein: "));
                break;
            }
            case "interpret": {
                albenListe.get(index).setInterpret(interpretErstellenOderVerwenden());
                break;
            }
            default: System.out.println(FEHLER_MELDUNG); break;
        }
    }

    public static void eintragBearbeiten() throws InterruptedException {
        System.out.println("Von was möchten Sie einen Eintrag bearbeiten?");
        String operation = stringAnfordern("Mögliche Eingaben: \"interpret\", \"song\", \"album\"");
        switch(operation) {
            case "interpret": eintragVonInterpretAendern(); break;
            case "song": eintragVonSongAendern(); break;
            case "album": eintragVonAlbumAendern(); break;
            default: System.out.println(FEHLER_MELDUNG); break;
        }
        System.out.println("Eintrag erfolgreich geändert!");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hallo, mithilfe dieses Programmes kannst du Alben anlegen und anzeigen lassen. ");
        System.out.println("Wenn du ein Album anlegen möchtest, gebe \"anlegen\" ein.");
        System.out.println("Wenn du ein Album anzeigen möchtest, gebe \"anzeigen\" ein.");
        System.out.println("Wenn du ein Song erstellen und es in einen Album hinzufügen möchtest, gebe \"song\" ein.");
        System.out.println("Wenn du einen erstellten Song suchen möchtest, gebe \"suchen\" ein.");
        System.out.println("Wenn du den Interpreten eines Albums erfahren möchtest, gebe \"interpret\" ein.");
        System.out.println("Wenn du vorher eingegebene Daten bearbeiten möchtest, gebe \"bearbeiten\" ein.");
        System.out.println("Zum Beenden des Programmes gebe einfach \"beenden\" ein.");
        while(true) {
            switch(scanner.nextLine()) {
                case "anlegen": albumErstellenUndInArrayListHinzufuegen(); break;
                case "anzeigen": albenAusgeben(); break;
                case "song": songErstellenUndImAlbumHinzufuegen(); break;
                case "suchen": songSuchen(); break;
                case "interpret": interpretAnzeigen(); break;
                case "bearbeiten": eintragBearbeiten(); break;
                case "beenden": System.exit(0); break;
                default: System.out.println(FEHLER_MELDUNG); break;
            }
            System.out.println("Warte auf die nächste Eingabe.");
            scanner = new Scanner(System.in);
        }
    }
}