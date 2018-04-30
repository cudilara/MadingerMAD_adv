package com.example.dilaramadinger.nativenations;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

//public class PhilosophyQuote extends RealmObject {
//    @Required
//    @PrimaryKey
//    private String id;
//    private String quote;
//
//    public String getId(){
//        return id;
//    }
//
//    public void setId(String id){
//        this.id = id;
//    }
//
//    public String getActivity_name(){
//        return quote;
//    }
//
//    public void setActivity_name(String activity){
//        this.quote = activity;
//    }
//}


public class PhilosophyQuote implements RealmModel {
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

    public String getActivity_name(){
        return quote;
    }
}
