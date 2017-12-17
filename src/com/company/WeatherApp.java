package com.company;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherApp {
    String cityName;
    WeatherData currentWeatherData = new WeatherData();
    List<WeatherData> forecastWeatherData = new ArrayList<>();
    List<String> outputList = new ArrayList<>();

    public WeatherApp(String cityName, OpenWeatherRepository repository) throws IOException, ClassNotFoundException {
        this.cityName = cityName;
        currentWeatherData = repository.getCurrentWeatherDataFromApi(cityName);
        forecastWeatherData = repository.getForecastDataFromApi(cityName);
    }

    public List<String> getOutputList() {
        return outputList;
    }



    public void ExportWeatherForecastInfo() throws IOException, ClassNotFoundException {
        outputList.add(cityName + " Weather Forecast: ");
        Date lastUpdateTime = currentWeatherData.getDt();
        Date currentTime = new Date();
        double currentTemp = currentWeatherData.getTemperatures().getTemp();
        double longitude = currentWeatherData.getCoord().getLon();
        double latitude = currentWeatherData.getCoord().getLat();
        double longitudeCoordinate = currentWeatherData.getCoord().getLon();
        double latitudeCoordinate = currentWeatherData.getCoord().getLat();
        String currentTempString = "Current temperature is: " + currentTemp;
        String cityCoordinates = "Coordinates for the city are: " + longitude +":"+ latitude;
        if (IsTemperatureInReasonableRange(currentTemp)
                && IsTimeSpanLessThanOneDay(currentTime, lastUpdateTime)
                && IsValidLatitudeCoordinate(latitudeCoordinate)
                && IsValidLongitudeCoordinate(longitudeCoordinate)) {
            outputList.add(currentTempString);
            outputList.add(cityCoordinates);
            printFutureTemperatures();

        }
    }

    public boolean IsTimeSpanLessThanOneDay(Date date, Date comparedDate) {
        long difference = (date.getTime()) - (comparedDate.getTime());
        if (difference <= (3 * 60 * 60 * 1000) && difference >=0) {
            return true;
        } else {
            System.out.println("Data is not updated");
            return false;

        }

    }

    public boolean IsTemperatureInReasonableRange(double temperature) {
        // Planet Earth lowest and highest temps
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

    public boolean IsHighTempHigherThanLowest(double highTemp, double lowTemp) {
        if (highTemp >= lowTemp) {
            return true;
        }
        System.out.println("The Highest Temperature is not higher than the lowest");
        return false;
    }

    public void printFutureTemperatures() throws IOException, ClassNotFoundException {
        List<Double> temps;
        String highTemps = "The highest temperatures for next three days temperatures are: ";
        String lowTemps= "The lowest temperatures for next three days temperatures are: ";
        temps = getFutureTemperatures(forecastWeatherData,true);
        outputList.add(highTemps);
        printForecastTemperatures((ArrayList<Double>) temps);
        temps = getFutureTemperatures(forecastWeatherData,false);
        outputList.add(lowTemps);
        printForecastTemperatures((ArrayList<Double>) temps);
        outputList.add("\n");
    }

    public List<Double> getFutureTemperatures(List<WeatherData> forecastWeatherDataList, boolean isHighTemp) throws IOException, ClassNotFoundException {
        List<Double> temps = new ArrayList<>();
        Date currentTime = new Date();
        for (int i = 0; i < forecastWeatherDataList.size()  && temps.size() <3 ; i++) {
            WeatherData weatherData = forecastWeatherDataList.get(i);
            Date timeToCheck = weatherData.getDt();
            long dayDifference = Math.abs((timeToCheck).getDate() - currentTime.getDate());
            Temperatures temperatures = weatherData.getTemperatures();
            boolean isWithinTimeRange = (dayDifference >= 1 && timeToCheck.getHours() == 14);
            double tempMax = temperatures.getTempMax();
            double tempMin = temperatures.getTempMin();
            if (isWithinTimeRange
                    && IsHighTempHigherThanLowest(tempMax, tempMin)
                    && IsTemperatureInReasonableRange(tempMax)
                    && IsTemperatureInReasonableRange(tempMin)
                    ) {
                temps.add(isHighTemp ? tempMax : tempMin);
            }
        }
        return temps;
    }

    public void printForecastTemperatures(ArrayList<Double> temperatures) {

        DecimalFormat df = new DecimalFormat("#.##");
        String firstDay = "First Day: " + df.format(temperatures.get(0).doubleValue());
        String secondDay = "Second Day: " + df.format(temperatures.get(0).doubleValue());
        String thirdDay = "Third Day: " + df.format(temperatures.get(0).doubleValue());
        if(temperatures.size()==3){
            outputList.add(firstDay);
            outputList.add(secondDay);
            outputList.add(thirdDay);
        } else{
            System.out.println("Does not have enough data");
        }
    }

}

