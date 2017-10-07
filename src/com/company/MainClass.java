package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        WeatherApp weatherApp = new WeatherApp();
        List<WeatherData> forecastWeather = new ArrayList<>();
        weatherApp.ShowWeatherForecastInfo();



    }
}
