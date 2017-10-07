package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;


class WeatherAppTest {

    WeatherApp weatherApp = new WeatherApp();

    WeatherAppTest() throws IOException, ClassNotFoundException {
    }

    @Test
    public void testIfDataUpdated() throws Exception{
        int lastUpdateTime = weatherApp.latestWeather.getDt();
        Date currentTime = new Date();
        Date currentTime2 = new Date();
        Date currentTime3 = new Date();
        currentTime2.setHours(currentTime2.getHours()-24);
        currentTime3.setHours(currentTime3.getHours()+3);
        Assertions.assertTrue(weatherApp.IsDataUpdated(currentTime, lastUpdateTime));
        Assertions.assertTrue(weatherApp.IsDataUpdated(currentTime2, lastUpdateTime));
        Assertions.assertFalse(weatherApp.IsDataUpdated(currentTime3, lastUpdateTime));

    }
    @Test
    public void testIsTemperatureInReasonableRange() throws Exception{
        double temperature = 300;
        double temperature2 = -100;
        double temperature3= -100.2;
        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature));
        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature2));
        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature3));
    }

    @Test
    public void testIsValidLatitudeCoordinate() throws Exception {
        double latitudeCoordinate = -89;
        double latitudeCoordinate2 = 89.9;
        Assertions.assertTrue(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate));
        Assertions.assertTrue(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate2));
    }

    @Test
    public void testIsValidLongitudeCoordinate() throws Exception {
        double longitudeCoordinate = 179;
        double longitudeCoordinate2 = -179.9;
        Assertions.assertTrue(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate));
        Assertions.assertTrue(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate2));
    }

    @Test
    public void testIfHighTemperatureIsHigherThanLowest() throws Exception {
        double highTemp = 200.4;
        double lowTemp = 200.39;
        double highTemp2 = 200.39;
        double lowTemp2 = 200.39;
        double highTemp3 = 200.38;
        double lowTemp3 = 200.39;
        Assertions.assertTrue(weatherApp.isHighTempHigherThanLowest(highTemp, lowTemp));
        Assertions.assertTrue(weatherApp.isHighTempHigherThanLowest(highTemp2, lowTemp2));
        Assertions.assertFalse(weatherApp.isHighTempHigherThanLowest(highTemp3, lowTemp3));

    }

    @Test
    public void testGetFutureTemperatures() throws Exception{
        double correctSize = 3;
        List<Double> testList;
        testList = weatherApp.getFutureTemperatures(weatherApp.forecastWeather, true);
        List<Double> testList2;
        testList2 = weatherApp.getFutureTemperatures(weatherApp.forecastWeather, false);
        Assertions.assertEquals(correctSize, testList.size());
        Assertions.assertEquals(correctSize, testList2.size());
    }

    @Test
    public void testIsSomeFieldIsnNull() throws Exception{
        Assertions.assertNotNull(weatherApp.latestWeather.getDt());
        Assertions.assertNotNull(weatherApp.latestWeather.getCoord().getLat());
        Assertions.assertNotNull(weatherApp.latestWeather.getCoord().getLon());
        Assertions.assertNotNull(weatherApp.latestWeather.getTemperatures());
        for(int i = 0; i<weatherApp.forecastWeather.size(); i++){
            Assertions.assertNotNull(weatherApp.forecastWeather.get(i).getTemperatures().getTempMax());
            Assertions.assertNotNull(weatherApp.forecastWeather.get(i).getTemperatures().getTempMin());
            Assertions.assertNotNull(weatherApp.forecastWeather.get(i).getDt());
        }
    }
}