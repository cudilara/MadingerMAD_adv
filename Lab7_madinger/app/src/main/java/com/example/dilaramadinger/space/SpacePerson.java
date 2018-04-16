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
            new SpacePerson("Austronauts", new ArrayList<String>(Arrays.asList("Name", "Another Name"))),
            new SpacePerson("Cosmonauts", new ArrayList<String>(Arrays.asList("Imya", "Drugoe Imya")))
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
