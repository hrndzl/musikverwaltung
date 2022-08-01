package com.hrndzl.musikverwaltung_mit_gui;

import java.util.ArrayList;
import java.util.Collections;

public class Album {
    private String name;
    private Interpret interpret;
    private int albumLaengeInSekunden = 0;
    private int titelAnzahl = 0;
    private ArrayList<Song> inhalt = new ArrayList<Song>();

    public Album() {}

    public Album(String name, Interpret interpret) {
        this.name = name;
        this.interpret = interpret;
    }

    public ArrayList<Song> getInhalt() {
        return inhalt;
    }

    public void setInhalt(ArrayList<Song> inhalt) {
        this.inhalt = inhalt;
    }

    public Interpret getInterpret() {
        return interpret;
    }

    public void setInterpret(Interpret interpret) {
        this.interpret = interpret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbumLaengeInSekunden() {
        return albumLaengeInSekunden;
    }

    public void setAlbumLaengeInSekunden(int albumLaengeInSekunden) {
        this.albumLaengeInSekunden = albumLaengeInSekunden;
    }

    public int getTitelAnzahl() {
        return titelAnzahl;
    }

    public void setTitelAnzahl(int titelAnzahl) {
        this.titelAnzahl = titelAnzahl;
    }

    public void addSong(Song song) {
        inhalt.add(song);
        song.inAlbum = this;
        Collections.sort(this.inhalt, new SortSongs());
        this.setAlbumLaengeInSekunden(this.getAlbumLaengeInSekunden() + song.getDauerInSekunden());
        this.setTitelAnzahl(inhalt.size());
    }

    public String gesamtDauerString() {
        return String.valueOf(this.getAlbumLaengeInSekunden() / 60) + " Minuten, " + String.valueOf(this.getAlbumLaengeInSekunden() % 60) + " Sekunden";
    }

    public void sortSongs() {
        Collections.sort(inhalt, new SortSongs());
    }

    public void ausgeben() {
        System.out.println("Name: " + this.getName());
        System.out.println("Interpret: " + this.getInterpret().getName());
        for(Song song : inhalt) {
            System.out.println(song.getNummer() + ": " + song.getName());
        }
        System.out.println("Länge: " + this.getAlbumLaengeInSekunden());
    }
}
