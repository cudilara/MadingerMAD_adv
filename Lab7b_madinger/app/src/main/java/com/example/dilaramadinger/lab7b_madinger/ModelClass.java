package com.example.dilaramadinger.lab7b_madinger;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ModelClass extends RealmObject {
    @Required
    @PrimaryKey
    private String id;
    private String activity_name;
    private String activity_detail;
    private boolean done;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getActivity_name(){
        return activity_name;
    }

    public void setActivity_name(String activity){
        this.activity_name = activity;
    }

    public String getActivity_detail(){
        return activity_detail;
    }

    public void setActivity_detail(String detail){
        this.activity_detail = detail;
    }

    public boolean done(){
        return done;
    }

    public void setDone(boolean done){
        this.done = done;
    }
}

