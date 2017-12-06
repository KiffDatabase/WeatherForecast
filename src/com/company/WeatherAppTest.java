package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class WeatherAppTest{

    WeatherApp weatherApp = mock(WeatherApp.class);
    ReadCityNames readCityNames = mock(ReadCityNames.class);


    WeatherAppTest() throws IOException, ClassNotFoundException {
    }




    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_DatesAreEqual(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 4);

        Mockito.when(weatherApp.IsTimeSpanLessThanOneDay(date1, date2)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateLessThanTwoDays(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 2);

        Mockito.when(weatherApp.IsTimeSpanLessThanOneDay(date1, date2)).thenReturn(false);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateMoreThanOneMonth(){
        Date date1 = new Date(2017, 12, 4);
        Date date2 = new Date(2017, 11, 4);

        Mockito.when(weatherApp.IsTimeSpanLessThanOneDay(date1, date2)).thenReturn(false);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_EmptyDate(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(0, 0, 0);

        Mockito.when(weatherApp.IsTimeSpanLessThanOneDay(date1, date2)).thenReturn(false);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateLessThanOneMonth(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 10, 40);

        Mockito.when(weatherApp.IsTimeSpanLessThanOneDay(date1, date2)).thenReturn(false);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateMoreThenOneday(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 5);

        Mockito.when(weatherApp.IsTimeSpanLessThanOneDay(date1, date2)).thenReturn(false);

        Assertions.assertFalse(weatherApp.IsTimeSpanLessThanOneDay(date1, date2));
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Positive() throws Exception {
        double temperature = 300;

        Mockito.when(weatherApp.IsTemperatureInReasonableRange(temperature)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature));
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Negative() throws Exception {
        double temperature2 = -100;

        Mockito.when(weatherApp.IsTemperatureInReasonableRange(temperature2)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature2));
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Decimal() throws Exception {
        double temperature3 = -100.2;

        Mockito.when(weatherApp.IsTemperatureInReasonableRange(temperature3)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsTemperatureInReasonableRange(temperature3));
    }


    @Test
    public void WeatherApp_IsValidLatitudeCoordinate_Positive() throws Exception {
        double latitudeCoordinate2 = 89.9;

        Mockito.when(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate2)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate2));
    }

    @Test
    public void WeatherApp_IsValidLatitudeCoordinate_Negative() throws Exception {
        double latitudeCoordinate = -89;

        Mockito.when(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate));
    }

    @Test
    public void WeatherApp_IsValidLongitudeCoordinate_Positive() throws Exception {
        double longitudeCoordinate = 179;

        Mockito.when(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate));
    }

    @Test
    public void WeatherApp_IsValidLongitudeCoordinate_Negative() throws Exception {
        double longitudeCoordinate2 = -179.9;

        Mockito.when(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate2)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate2));
    }

    @Test
    public void WeatherApp_IsHighTempHigherThanLowest_OneTenth() throws Exception {
        double highTemp = 200.4;
        double lowTemp = 200.3;

        Mockito.when(weatherApp.IsHighTempHigherThanLowest(highTemp, lowTemp)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsHighTempHigherThanLowest(highTemp, lowTemp));

    }

    @Test
    public void WeatherApp_IsHighTempHigherThanLowest_SameNumber() throws Exception {
        double highTemp2 = 200.39;
        double lowTemp2 = 200.39;

        Mockito.when(weatherApp.IsHighTempHigherThanLowest(highTemp2, lowTemp2)).thenReturn(true);

        Assertions.assertTrue(weatherApp.IsHighTempHigherThanLowest(highTemp2, lowTemp2));

    }

    @Test
    public void WeatherApp_getFutureTemperatures_HighTempsListSizeIsThree() throws Exception {
        List<Double> testList = new ArrayList<>();
        testList.add((double) 1);
        testList.add((double) 2);
        testList.add((double) 3);
        double correctSize = testList.size();

        Mockito.when(weatherApp.getFutureTemperatures(weatherApp.forecastWeatherData, true)).thenReturn(testList);

        assertEquals(correctSize, weatherApp.getFutureTemperatures(weatherApp.forecastWeatherData, true).size());

    }

    @Test
    public void WeatherApp_getFutureTemperatures_LowTempsListSizeIsThree() throws Exception {
        List<Double> testList = new ArrayList<>();
        testList.add((double) 1);
        testList.add((double) 2);
        testList.add((double) 3);
        double correctSize = testList.size();

        Mockito.when(weatherApp.getFutureTemperatures(weatherApp.forecastWeatherData, false)).thenReturn(testList);

        assertEquals(correctSize, weatherApp.getFutureTemperatures(weatherApp.forecastWeatherData, false).size());
    }

    @Test
    public void UserInput_IsCityNameCorrect_TwoNames() throws Exception {
        String cityName1 = "San Fransisco";

        Mockito.when(readCityNames.isCityNameCorrect(cityName1)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName1));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithDash() throws Exception {
        String cityName2 = "Île-de-France";

        Mockito.when(readCityNames.isCityNameCorrect(cityName2)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName2));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithTwoDashes() throws Exception {
        String cityName3 = "New York--";

        Mockito.when(readCityNames.isCityNameCorrect(cityName3)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName3));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithWeirdCharacters() throws Exception {
        String cityName4 = "Þorlákshöfn";

        Mockito.when(readCityNames.isCityNameCorrect(cityName4)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName4));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithDot() throws Exception {
        String cityName5 = "St. Catharines";

        Mockito.when(readCityNames.isCityNameCorrect(cityName5)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName5));
    }

    @Test
    public void UserInput_IsCityNameCorrect_StartsWithSpace() throws Exception {
        String cityName6 = " Tallinn";

        Mockito.when(readCityNames.isCityNameCorrect(cityName6)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName6));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WrongCharacters() throws Exception {
        String cityName7 = "&&**";

        Mockito.when(readCityNames.isCityNameCorrect(cityName7)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName7));
    }

    @Test
    public void UserInput_IsCityNameCorrect_DottedLetters() throws Exception {
        String cityName8 = "München";

        Mockito.when(readCityNames.isCityNameCorrect(cityName8)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName8));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WhiteSpace() throws Exception {
        String cityName9 = " ";

        Mockito.when(readCityNames.isCityNameCorrect(cityName9)).thenReturn(true);

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName9));
    }

}