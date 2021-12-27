package com.adityaaravi.stayCozy.objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WoeidSearchResult {
    // // distance
    // private Integer distance;
    // city name [title]
    private String title;

    // woeid
    private Integer woeid;

    // getter and setter methods for each field above
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    // toString method
    @Override
    public String toString() {
        return "" + woeid + ',';
    }

}
