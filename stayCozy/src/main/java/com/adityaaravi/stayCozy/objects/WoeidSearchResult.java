package com.adityaaravi.stayCozy.objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WoeidSearchResult {
    // distance
    private int distance;

    // woeid
    private String woeid;

    // getter and setter methods for each field above
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

}
