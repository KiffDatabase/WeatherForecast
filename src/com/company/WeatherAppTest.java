package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class WeatherAppTest{
    OpenWeatherRepository mockRepo = mock(OpenWeatherRepository.class);
    WeatherApp weatherApp = new WeatherApp("Tallinn", mockRepo);



    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException {

        WeatherData wd = mockRepo.getCurrentWeatherDataFromFile();
        List<WeatherData> dummyList = mockRepo.getForecastDataFromFile();

        when(mockRepo.getForecastDataFromApi("Tallinn")).thenReturn(dummyList);
        when(mockRepo.getCurrentWeatherDataFromApi("Tallinn")).thenReturn(wd);


    }


    WeatherAppTest() throws IOException, ClassNotFoundException {
    }


    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_DatesAreEqual() throws IOException, ClassNotFoundException {
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 4);

        Assertions.assertTrue(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateLessThanTwoDays(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 2);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateMoreThanOneMonth(){
        Date date1 = new Date(2017, 12, 4);
        Date date2 = new Date(2017, 11, 4);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_EmptyDate(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(0, 0, 0);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateLessThanOneMonth(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 10, 40);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateMoreThenOneday(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 5);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Positive() throws Exception {
        double temperature = 300;

        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature));
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Negative() throws Exception {
        double temperature2 = -100;

        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature2));
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Decimal() throws Exception {
        double temperature3 = -100.2;

        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature3));
    }


    @Test
    public void WeatherApp_IsValidLatitudeCoordinate_Positive() throws Exception {
        double latitudeCoordinate2 = 89.9;

        Assertions.assertTrue(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate2));
    }

    @Test
    public void WeatherApp_IsValidLatitudeCoordinate_Negative() throws Exception {
        double latitudeCoordinate = -89;

        Assertions.assertTrue(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate));
    }

    @Test
    public void WeatherApp_IsValidLongitudeCoordinate_Positive() throws Exception {
        double longitudeCoordinate = 179;

        Assertions.assertTrue(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate));
    }

    @Test
    public void WeatherApp_IsValidLongitudeCoordinate_Negative() throws Exception {
        double longitudeCoordinate2 = -179.9;

        Assertions.assertTrue(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate2));
    }

    @Test
    public void WeatherApp_IsHighTempHigherThanLowest_OneTenth() throws Exception {
        double highTemp = 200.4;
        double lowTemp = 200.3;

        Assertions.assertTrue(weatherApp.IsHighTempHigherThanLowest(highTemp, lowTemp));

    }

    @Test
    public void WeatherApp_IsHighTempHigherThanLowest_SameNumber() throws Exception {
        double highTemp2 = 200.39;
        double lowTemp2 = 200.39;

        Assertions.assertTrue(weatherApp.IsHighTempHigherThanLowest(highTemp2, lowTemp2));

    }

    @Test
    public void WeatherApp_getFutureTemperatures_HighTempsListSizeIsThree() throws Exception {
        OpenWeatherRepository owr1 = new OpenWeatherRepository();
        List<WeatherData> testList2 = owr1.getForecastDataFromFile();
        List<Double> testList = new ArrayList<>();
        testList.add((double) 1);
        testList.add((double) 2);
        testList.add((double) 3);
        double correctSize = testList.size();

        Assertions.assertEquals(correctSize, weatherApp.getFutureTemperatures(testList2,true).size());

    }

    @Test
    public void WeatherApp_getFutureTemperatures_LowTempsListSizeIsThree() throws Exception {
        OpenWeatherRepository owr2 = new OpenWeatherRepository();
        List<WeatherData> testList2 = owr2.getForecastDataFromFile();
        List<Double> testList = new ArrayList<>();
        testList.add((double) 1);
        testList.add((double) 2);
        testList.add((double) 3);
        double correctSize = testList.size();

        Assertions.assertEquals(correctSize, weatherApp.getFutureTemperatures(testList2,false).size());
    }

}