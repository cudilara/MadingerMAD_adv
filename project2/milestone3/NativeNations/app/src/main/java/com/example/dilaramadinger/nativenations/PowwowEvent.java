package com.example.dilaramadinger.nativenations;

public class PowwowEvent {
    private String location;

    private PowwowEvent(String loc){
        this.location = loc;
    }

    public static final PowwowEvent[] locations = {
            new PowwowEvent("Denver"),
            new PowwowEvent("Fort Collins")
    };

    public String getLocation() {
        return location;
    }

    public String toString(){
        return this.location;
    }
}
