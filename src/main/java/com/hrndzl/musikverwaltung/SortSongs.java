package com.hrndzl.musikverwaltung;

import java.util.Comparator;

public class SortSongs implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return (o1.getNummer() - o2.getNummer());
    }
}
