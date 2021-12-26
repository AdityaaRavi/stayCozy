package com.adityaaravi.stayCozy.objects;

// class to store all the weather predictions for a single day from metaweather
public class PredictionList {
    // list of WeatherObj objects
    private WeatherObj[] predictions;

    // getter and setter methods for the list of WeatherObj objects
    public WeatherObj[] getPredictions() {
        return predictions;
    }

    public void setPredictions(WeatherObj[] predictions) {
        this.predictions = predictions;
    }

    // toString method to print the list of WeatherObj objects
    @Override
    public String toString() {
        return "PredictionList [predictions=" + predictions + "]";
    }

    // empty constructor b/c the tutorial says to
    public PredictionList() {
    }
}
