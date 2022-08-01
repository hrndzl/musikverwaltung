package com.hrndzl.musikverwaltung_mit_gui;

import java.util.ArrayList;

public class Interpret {
    private String name;
    private String biografie;
    private ArrayList<Album> alben = new ArrayList<Album>();

    public Interpret() {
    }

    public Interpret(String name, String biografie) {
        this.name = name;
        this.biografie = biografie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiografie() {
        return biografie;
    }

    public void setBiografie(String biografie) {
        this.biografie = biografie;
    }

    public ArrayList<Album> getAlben() {
        return alben;
    }

    public void setAlben(ArrayList<Album> alben) {
        this.alben = alben;
    }
}
