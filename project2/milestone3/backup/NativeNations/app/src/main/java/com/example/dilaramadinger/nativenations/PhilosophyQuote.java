package com.example.dilaramadinger.nativenations;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PhilosophyQuote extends RealmObject {
    @Required
    @PrimaryKey
    private String id;
    private String quote;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String toString(){
        return this.quote;
    }
}


//public class PhilosophyQuote implements RealmModel {
//    private String quote;
//
//    private PhilosophyQuote(String quote){
//        this.quote = quote;
//    }
//
//    public static final PhilosophyQuote[] quotes = {
//            new PhilosophyQuote("one quote"),
//            new PhilosophyQuote("another quote")
//    };
//
//    public String getQuote() {
//        return quote;
//    }
//
//    public String toString(){
//        return this.quote;
//    }
//
//    public String getActivity_name(){
//        return quote;
//    }
//}
