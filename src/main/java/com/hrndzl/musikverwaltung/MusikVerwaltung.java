package com.hrndzl.musikverwaltung;

import java.util.ArrayList;

public class MusikVerwaltung {
    private static final ArrayList<Album> albenListe = new ArrayList<>();
    private static final ArrayList<Interpret> interpretenListe = new ArrayList<>();
    public static ArrayList<Album> getAlbenListe() {
        return albenListe;
    }
    public static ArrayList<Interpret> getInterpretenListe() {
        return interpretenListe;
    }

    public static Album inWhichAlbumIsTheSong(String nameOfTheSong) {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            for(Song song : album.getInhalt()) {
                if(nameOfTheSong.equalsIgnoreCase(song.getName())) {
                    return album;
                }
            }
        }
        return null;
    }

    public static boolean songIsAlreadyCreated(String albumName, String songName) {
        for(Album album : MusikVerwaltung.getAlbenListe()) {
            if(album.getName().equals(albumName)) {
                for(Song song : album.getInhalt()) {
                    if(songName.equalsIgnoreCase(song.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}