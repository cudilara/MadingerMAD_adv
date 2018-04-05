package com.example.dilaramadinger.anatomy;


public class BodyPart {
    private String name;
    private int imageResourceID;

    private BodyPart(String newname, int newID){
        this.name = newname;
        this.imageResourceID = newID;
    }

    public static final BodyPart[] head = {
            new BodyPart("Nose", R.drawable.apeldoorn),
            new BodyPart("Nose", R.drawable.apeldoorn),
            new BodyPart("Nose", R.drawable.apeldoorn)
    };

    public String getName(){
        return name;
    }

    public int getImageResourceID(){
        return imageResourceID;
    }

    public String toString(){
        return this.name;
    }
}


