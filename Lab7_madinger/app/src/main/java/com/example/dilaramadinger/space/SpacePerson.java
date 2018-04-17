package com.example.dilaramadinger.space;

import java.util.Arrays;
import java.util.ArrayList;

public class SpacePerson {
    private String category;
    private ArrayList<String> spacepersons = new ArrayList<>();

    private SpacePerson(String cat, ArrayList<String> spacepeople){
        this.category = cat;
        this.spacepersons = new ArrayList<String>(spacepeople);
    }

    public static final SpacePerson[] spacepeople = {
            new SpacePerson("Astronauts", new ArrayList<String>(Arrays.asList("Armstrong", "Aldrin", "Ride", "Kelly", "Whitson", "Sharman", "Glenn"))),
            new SpacePerson("Cosmonauts", new ArrayList<String>(Arrays.asList("Gagarin", "Tereshkova", "Leonov", "Titov", "Savitskaya")))
    };

    public String getCategory(){
        return category;
    }

    public ArrayList<String> getSpacepersons() {
        return spacepersons;
    }

    public String toString(){
        return this.category;
    }
}
