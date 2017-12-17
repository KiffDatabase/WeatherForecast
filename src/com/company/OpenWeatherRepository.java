package com.company;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OpenWeatherRepository {


    public WeatherData getCurrentWeatherDataFromApi(String cityName) throws IOException, ClassNotFoundException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=fd012c6db32058a281c3a2f244beee95");
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(url, WeatherData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WeatherData getCurrentWeatherDataFromFile() throws IOException, ClassNotFoundException {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(new File("C:\\Users\\karlivar.pajula\\IdeaProjects\\WeatherForecast\\src\\com\\company\\CurrentWeatherData.json"), WeatherData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<WeatherData> getForecastDataFromApi(String cityName) throws IOException, ClassNotFoundException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=fd012c6db32058a281c3a2f244beee95");
        ObjectMapper mapper = getObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(url, WeatherResponse.class);
        return weatherResponse.getList();
    }

    public List<WeatherData> getForecastDataFromFile() throws IOException, ClassNotFoundException {
        File file = new File("C:\\Users\\karlivar.pajula\\IdeaProjects\\WeatherForecast\\src\\com\\company\\FutureWeatherData.json");
        ObjectMapper mapper = getObjectMapper();
        WeatherResponse weatherResponse = mapper.readValue(file, WeatherResponse.class);
        return weatherResponse.getList();
    }
}
