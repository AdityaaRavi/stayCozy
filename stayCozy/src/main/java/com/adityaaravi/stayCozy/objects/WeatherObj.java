package com.adityaaravi.stayCozy.objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This class corresponds to the json objects used by meta weather to provide weather data.
// note that the most recent json object in the meta weather api contains the most recent weather prediction.

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherObj {
    // unique id
    private long id;

    // weather condition
    private String weather_state_name;

    // applicable date
    private String applicable_date;

    // min temperature
    private double min_temp;

    // max temperature
    private double max_temp;

    // wind speed
    private double wind_speed;

    // create getter and setter methods for each field above
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    // empty constructor as the jackson library will automatically fill in the fields
    public WeatherObj() {
    }

}
