package com.adityaaravi.stayCozy.objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipData {
    // latitude
    private String latitude;

    // longitude
    private String longitude;

    // city name
    private String city;

    // getter and setter methods for each field above
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // toString method
    @Override
    public String toString() {
        return "ZipData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                '}';
    }
}
