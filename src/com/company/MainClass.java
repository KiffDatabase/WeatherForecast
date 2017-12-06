package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        ReadCityNames reader = new ReadCityNames();
        ExportWeatherForecast writer = new ExportWeatherForecast();
        List<List<String>> exportedList = new ArrayList<>();
        reader.readUserInput();

        List<String> cityNames = reader.getOutputCitiesList();
        for(String s: cityNames){
            WeatherApp weatherApp = new WeatherApp(s);
            weatherApp.ExportWeatherForecastInfo();
            List<String> outputData = weatherApp.getOutputList();
            writer.printForecastToConsole(outputData);
            exportedList.add(outputData);
        }
        writer.saveForecastToFile(exportedList);







    }
}
