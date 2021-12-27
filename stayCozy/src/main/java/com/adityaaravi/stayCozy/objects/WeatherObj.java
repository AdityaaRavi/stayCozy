package com.adityaaravi.stayCozy.objects;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// a list of these weather objects is returned by the API --> stored as the object "PredictionList" here 
// This class corresponds to the json objects used by meta weather to provide weather data.
// note that the most recent json object in the meta weather api contains the most recent weather prediction.

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherObj {
    // unique id
    private BigInteger id;

    // weather condition
    private String weather_state_name;

    // applicable date
    private String applicable_date;

    // min temperature
    private Double min_temp;

    // max temperature
    private Double max_temp;

    // wind speed
    private Double wind_speed;

    // create getter and setter methods for each field above
    public BigInteger getId() {
        return id;
    }

    public void setId (BigInteger id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    public Double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(Double min_temp) {
        this.min_temp = min_temp;
    }

    public Double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(Double max_temp) {
        this.max_temp = max_temp;
    }

    public Double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Double wind_speed) {
        this.wind_speed = wind_speed;
    }

    // empty constructor
    public WeatherObj() {
    }

}
