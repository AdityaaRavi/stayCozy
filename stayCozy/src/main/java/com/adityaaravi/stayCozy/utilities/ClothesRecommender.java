package com.adityaaravi.stayCozy.utilities;

import com.adityaaravi.stayCozy.objects.WeatherObj;

public class ClothesRecommender {
    public static double cToF(double c) {
        return c * 9 / 5 + 32;
    }

    public static double fToC(double f) {
        return (f - 32) * 5 / 9;
    }

    // clothing recomendation
    public static String clothRecomendation(WeatherObj o){
        // get the min temperature
        double minTemp = o.getMin_temp();

        // get the max temperature
        double maxTemp = o.getMax_temp();

        // convert both to fahrenheit to match information source
        minTemp = cToF(minTemp);
        maxTemp = cToF(maxTemp);

        // In the Morning:
        String morning = clothingHelper(minTemp);
        // In the Afternoon:
        String afternoon = clothingHelper(maxTemp);

        // return the string
        if (morning.equals(afternoon)) {
            return "You may prefer to wear " + morning + " through out the day.";
        } else {
            return "You may prefer to wear " + morning + " in the morning and a " + afternoon + " as it gets hotter.";
        }
    }

    private static String clothingHelper(double tempInF){
        if(tempInF < 25) return "a winter jacket";
        if(tempInF < 44) return "a light/medium Jacket";
        if(tempInF < 64) return "a fleece jacket or a long sleeved shirt"; 
        if(tempInF < 79) return "short sleeves";
        // If temp > 79
        return "short sleeves and shorts";
    }
}
