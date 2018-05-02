package com.example.dilaramadinger.nativenations;


public class Language {
    private String language;
    private String website;

    private Language(String lang, String website){
        this.language = lang;
        this.website = website;
    }

    public static final Language[] langList = {
            new Language("Black Foot", "http://www.native-languages.org/blackfoot.htm"),
            new Language("Navajo", "http://www.native-languages.org/navajo.htm")
    };

    public String getLanguage() {
        return language;
    }

    public String getWebsite() {
        return website;
    }

    public String toString(){
        return this.language;
    }
}
