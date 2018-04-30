package com.example.dilaramadinger.nativenations;


public class Language {
    private String language;

    private Language(String lang){
        this.language = lang;
    }

    public static final Language[] langList = {
            new Language("Black Foot"),
            new Language("Navajo")
    };

    public String getLanguage() {
        return language;
    }

    public String toString(){
        return this.language;
    }
}
