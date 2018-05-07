package com.example.dilaramadinger.madingerfinal;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ModelClass extends RealmObject {
    @Required
    @PrimaryKey
    private String id;
    private String exercise_type;
    private String activity_name;
    private String activity_url;

    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
    }

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
        return activity_url;
    }

    public void setActivity_detail(String detail){
        this.activity_url = detail;
    }
}
