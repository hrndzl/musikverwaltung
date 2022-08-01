package com.hrndzl.musikverwaltung_mit_gui;

public class Song {
    public Song() {}

    public Song(String name, int dauerInSekunden, int nummer) {
        this.name = name;
        this.dauerInSekunden = dauerInSekunden;
        this.nummer = nummer;
    }

    private String name;
    private int dauerInSekunden;
    private int nummer;
    public Album inAlbum;

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

    public String getDauerString() {
        return String.valueOf(this.dauerInSekunden / 60);
    }
}
