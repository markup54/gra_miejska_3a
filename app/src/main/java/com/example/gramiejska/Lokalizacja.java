package com.example.gramiejska;

public class Lokalizacja {
    private int idZdjecia;
    private double dlugosc;
    private double szerokosc;

    public Lokalizacja(int idZdjecia, double dlugosc, double szerokosc) {
        this.idZdjecia = idZdjecia;
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
    }

    public int getIdZdjecia() {
        return idZdjecia;
    }

    public void setIdZdjecia(int idZdjecia) {
        this.idZdjecia = idZdjecia;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(double szerokosc) {
        this.szerokosc = szerokosc;
    }
}
