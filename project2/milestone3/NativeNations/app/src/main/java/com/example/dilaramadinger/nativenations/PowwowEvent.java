package com.example.dilaramadinger.nativenations;

public class PowwowEvent {
    private String location;
    private String website;

    private PowwowEvent(String loc, String site){
        this.location = loc;
        this.website = site;
    }

    public static final PowwowEvent[] locations = {
            new PowwowEvent("Denver", "http://www.denvermarchpowwow.org"),
            new PowwowEvent("Fort Collins", "http://ncipa.weebly.com")
    };

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public String toString(){
        return this.location;
    }
}
