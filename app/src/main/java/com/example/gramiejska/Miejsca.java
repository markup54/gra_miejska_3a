package com.example.gramiejska;

import java.util.ArrayList;

public class Miejsca {
    public ArrayList<Lokalizacja> lokalizacje = new ArrayList<>();

    public Miejsca() {
        this.lokalizacje.add(new Lokalizacja(R.drawable.zs10,18.7812902,50.3344451));
        this.lokalizacje.add(new Lokalizacja(R.drawable.basen,18.7728,50.3240));
        this.lokalizacje.add(new Lokalizacja(R.drawable.lesne,18.7411,50.3232));
    }
}
