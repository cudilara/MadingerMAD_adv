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
            new PowwowEvent("Fort Collins", "http://ncipa.weebly.com"),
            new PowwowEvent("Colorado Springs","http://www.coloradospringspowwow.org"),
            new PowwowEvent("Albuquerque, NM", "http://www.gatheringofnations.com"),
            new PowwowEvent("Northglenn", "https://calendar.powwows.com/events/4th-annual-healing-hoop-pow-wow-2018/"),
            new PowwowEvent("Morrison","https://calendar.powwows.com/events/18th-annual-indian-market-powwow-2018/"),
            new PowwowEvent("Sedalia", "https://calendar.powwows.com/events/dance-to-your-future-pow-wow/"),
            new PowwowEvent("Ignacio", "http://www.southernute-nsn.gov/culture/"),
            new PowwowEvent("Towaoc", "http://utemountaincasino.com/events.php")

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
