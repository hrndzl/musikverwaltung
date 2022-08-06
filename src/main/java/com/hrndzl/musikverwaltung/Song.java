package com.hrndzl.musikverwaltung;

public class Song {
    private String name;
    private int dauerInSekunden;
    private int nummer;
    private String songText;
    public Album inAlbum;

    public Song() {}

    public Song(String name, int dauerInSekunden, int nummer, String songText) {
        this.name = name;
        this.dauerInSekunden = dauerInSekunden;
        this.nummer = nummer;
        this.songText = songText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDauerInSekunden() {
        return dauerInSekunden;
    }

    public void setDauerInSekunden(int dauerInSekunden) {
        this.dauerInSekunden = dauerInSekunden;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getSongText() {
        return songText;
    }

    public void setSongText(String songText) {
        this.songText = songText;
    }
}
