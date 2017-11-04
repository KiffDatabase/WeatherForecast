package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        ReadCityName userinput = new ReadCityName();
        String cityName = userinput.readUserInput();
        WeatherApp weatherApp = new WeatherApp(cityName);
        List<WeatherData> forecastWeather = new ArrayList<>();
        weatherApp.ShowWeatherForecastInfo();
        weatherApp.saveForecastToFile();



    }
}
