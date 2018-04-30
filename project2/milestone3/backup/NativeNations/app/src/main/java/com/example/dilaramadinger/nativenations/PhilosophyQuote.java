package com.example.dilaramadinger.nativenations;


public class PhilosophyQuote {
    private String quote;

    private PhilosophyQuote(String quote){
        this.quote = quote;
    }

    public static final PhilosophyQuote[] quotes = {
            new PhilosophyQuote("the bull goes towards the storm"),
            new PhilosophyQuote("another quote")
    };

    public String getQuote() {
        return quote;
    }

    public String toString(){
        return this.quote;
    }
}
