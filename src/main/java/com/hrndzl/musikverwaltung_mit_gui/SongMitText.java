package com.hrndzl.musikverwaltung_mit_gui;

public class SongMitText extends Song {

    public SongMitText(String name, int dauerInSekunden, int nummer, String songText) {
        super(name, dauerInSekunden, nummer);
        this.songText = songText;
    }
    private String songText;

    public String getSongText() {
        return songText;
    }

    public void setSongText(String songText) {
        this.songText = songText;
    }
}
