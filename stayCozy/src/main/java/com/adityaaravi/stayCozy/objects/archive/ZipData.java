package com.adityaaravi.stayCozy.objects.archive;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipData {
    // city name
    private String city;
    
    // latitude
    private String latitude;

    // longitude
    private String longitude;


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
