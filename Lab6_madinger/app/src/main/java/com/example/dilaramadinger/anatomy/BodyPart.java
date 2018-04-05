package com.example.dilaramadinger.anatomy;


public class BodyPart {
    private String name;
    private int imageResourceID;

    private BodyPart(String newname, int newID){
        this.name = newname;
        this.imageResourceID = newID;
    }

    public static final BodyPart[] head = {
            new BodyPart("Skull", R.drawable.skull),
            new BodyPart("Jaw", R.drawable.jaw),
            new BodyPart("Eye", R.drawable.eye)
    };

    public static final BodyPart[] torso = {
            new BodyPart("Heart", R.drawable.heart),
            new BodyPart("Stomach", R.drawable.stomach),
            new BodyPart("Lungs", R.drawable.lungs)
    };

    public static final BodyPart[] extremities = {
            new BodyPart("Wrist", R.drawable.wrist),
            new BodyPart("Arm", R.drawable.arm),
            new BodyPart("Leg", R.drawable.leg)
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


