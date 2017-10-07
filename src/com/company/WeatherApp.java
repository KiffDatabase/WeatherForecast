package com.company;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherApp {
    WeatherData latestWeather = new WeatherData();
    List<WeatherData> forecastWeather = new ArrayList<>();


    public WeatherApp() throws IOException, ClassNotFoundException {
        latestWeather = getCurrentWeatherData("C:\\Users\\karlivar.pajula\\IdeaProjects\\WeatherForecast\\src\\com\\company\\CurrentWeatherData.json");
        forecastWeather = getForecastData("C:\\Users\\karlivar.pajula\\IdeaProjects\\WeatherForecast\\src\\com\\company\\FutureWeatherData.json");
    }

    public void ShowWeatherForecastInfo() {
        int lastUpdateTime = latestWeather.getDt();
        Date currentTime = new Date();
        double currentTemp = latestWeather.getTemperatures().getTemp();
        double longitudeCoordinate = latestWeather.getCoord().getLon();
        double latitudeCoordinate = latestWeather.getCoord().getLat();
        if (IsTemperatureInReasonableRange(currentTemp)
                && IsDataUpdated(currentTime, lastUpdateTime)
                && IsValidLatitudeCoordinate(latitudeCoordinate)
                && IsValidLongitudeCoordinate(longitudeCoordinate)) {
            System.out.println("Current temperature is: " + currentTemp);
            System.out.println("Coordinates for Tallinn are: " + longitudeCoordinate + ":" + latitudeCoordinate);
            printFutureTemperatures();
        }
    }

    public WeatherData getCurrentWeatherData(String pathName) throws IOException, ClassNotFoundException {
        File jsonFile = new File(pathName);
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(jsonFile, WeatherData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<WeatherData> getForecastData(String pathName) throws IOException, ClassNotFoundException {
        File jsonFile = new File(pathName);
        ObjectMapper mapper = getObjectMapper();
        try {
            WeatherResponse weatherResponse = mapper.readValue(jsonFile, WeatherResponse.class);
            return weatherResponse.getList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean IsDataUpdated(Date currentTime, int lastUpdateTime) {
        Date lastUpdate = new Date(lastUpdateTime * 1000L);
        if (((currentTime.getTime()) - (lastUpdate.getTime())) <= (3 * 60 * 60 * 1000)) {
            return true;
        } else {
            System.out.println("Data is not updated");
            return false;

        }

    }

    public boolean IsTemperatureInReasonableRange(double temperature) {
        // väärtused tulevad planeet Maa kõigi aegade madalaimast ja kõrgeimast temperatuurist
        if (temperature > -185.15 && temperature < 331.15) {
            return true;
        } else {
            System.out.println("Temperature is not in correct range");
            return false;
        }
    }

    public boolean IsValidLatitudeCoordinate(double latitudeCoordinate) {
        if (latitudeCoordinate > -90 && latitudeCoordinate < 90) {
            return true;
        } else {
            System.out.println("Latitude coordinate is in the wrong range. Must be between -90 and +90");
            return false;
        }
    }

    public boolean IsValidLongitudeCoordinate(double longitudeCoordinate) {
        if (longitudeCoordinate > -180 && longitudeCoordinate < 180) {
            return true;
        } else {
            System.out.println("Longitude coordinate is in the wrong range. Must be between -180 and +180");
            return false;
        }
    }

    public boolean isHighTempHigherThanLowest(double highTemp, double lowTemp) {
        if (highTemp >= lowTemp) {
            return true;
        }
        System.out.println("The Highest Temperature is not higher than the lowest");
        return false;
    }

    public void printFutureTemperatures() {
        List<Double> temps;
        temps = getFutureTemperatures(forecastWeather, true);
        System.out.println("The highest temperatures for next three days temperatures are: ");
        printForecastTemperatures((ArrayList<Double>) temps);
        temps = getFutureTemperatures(forecastWeather, false);
        System.out.println("The lowest temperatures for next three days temperatures are: ");
        printForecastTemperatures((ArrayList<Double>) temps);
    }

    public List<Double> getFutureTemperatures(List<WeatherData> forecastWeather, boolean isHighTemp) {
        List<Double> temps = new ArrayList<>();
        Date currentTime = new Date();
        for (int i = 0; i < forecastWeather.size(); i++) {
            WeatherData weatherData = forecastWeather.get(i);
            Date timeToCheck = new Date(weatherData.getDt() * 1000L);
            long dayDifference = (timeToCheck).getTime() - currentTime.getTime();
            Temperatures temperatures = weatherData.getTemperatures();
            int dayInMilliSec = 60 * 60 * 60 * 1000;
            boolean isWithinTimeRange = dayDifference >= dayInMilliSec && dayDifference <= (3 * dayInMilliSec) && timeToCheck.getHours() == 12;
            boolean isDataUpdated = IsDataUpdated(new Date(), weatherData.getDt());
            double tempMax = temperatures.getTempMax();
            double tempMin = temperatures.getTempMin();
            if (isWithinTimeRange
                    && isDataUpdated
                    && isHighTempHigherThanLowest(tempMax, tempMin)
                    && IsTemperatureInReasonableRange(tempMax)
                    && IsTemperatureInReasonableRange(tempMin)) {
                temps.add(isHighTemp ? tempMax : tempMin);
            }

        }
        return temps;
    }

    public void printForecastTemperatures(ArrayList<Double> temperatures) {
        DecimalFormat df = new DecimalFormat("#.##");
        if(temperatures.size()==3){
            System.out.println("First Day: " + df.format(temperatures.get(0).doubleValue()));
            System.out.println("Second Day: " + df.format(temperatures.get(1).doubleValue()));
            System.out.println("Third Day: " + df.format(temperatures.get(2).doubleValue()));
        } else{
            System.out.println("Does not have enough data");
        }
    }
}

