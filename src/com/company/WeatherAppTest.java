package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;


class WeatherAppTest {

    ReadCityName readCityName = new ReadCityName();
    String cityName = "London";
    WeatherApp weatherApp = new WeatherApp(cityName);


    WeatherAppTest() throws IOException, ClassNotFoundException {
    }


    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_DatesAreEqual(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 4);

        boolean isTimeSpanLessThanOneDay = weatherApp.IsTimeSpanLessThanOneDay(date1, date2);

        Assertions.assertTrue(isTimeSpanLessThanOneDay);
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateLessThanTwoDays(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 2);

        boolean isTimeSpanLessThanOneDay = weatherApp.IsTimeSpanLessThanOneDay(date1, date2);

        Assertions.assertFalse(isTimeSpanLessThanOneDay);
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateMoreThanOneMonth(){
        Date date1 = new Date(2017, 12, 4);
        Date date2 = new Date(2017, 11, 4);

        boolean isTimeSpanLessThanOneDay = weatherApp.IsTimeSpanLessThanOneDay(date1, date2);

        Assertions.assertFalse(isTimeSpanLessThanOneDay);
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_EmptyDate(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(0, 0, 0);

        boolean isTimeSpanLessThanOneDay = weatherApp.IsTimeSpanLessThanOneDay(date1, date2);

        Assertions.assertFalse(isTimeSpanLessThanOneDay);
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateLessThanOneMonth(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 10, 40);

        boolean isTimeSpanLessThanOneDay = weatherApp.IsTimeSpanLessThanOneDay(date1, date2);

        Assertions.assertFalse(isTimeSpanLessThanOneDay);
    }

    @Test
    public void WeatherApp_IsTimeSpanLessThanOneDay_OneDateMoreThenOneday(){
        Date date1 = new Date(2017, 11, 4);
        Date date2 = new Date(2017, 11, 5);

        boolean isTimeSpanLessThanOneDay = weatherApp.IsTimeSpanLessThanOneDay(date1, date2);

        Assertions.assertFalse(isTimeSpanLessThanOneDay);
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Positive() throws Exception {
        double temperature = 300;

        boolean isTemperatureInReasonableRange = weatherApp.IsTemperatureInReasonableRange(temperature);

        Assertions.assertTrue(isTemperatureInReasonableRange);
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Negative() throws Exception {
        double temperature2 = -100;

        boolean isTemperatureInReasonableRange = weatherApp.IsTemperatureInReasonableRange(temperature2);

        Assertions.assertTrue(isTemperatureInReasonableRange);
    }

    @Test
    public void WeatherApp_IsTemperature_InReasonableRange_Decimal() throws Exception {
        double temperature3 = -100.2;

        boolean isTemperatureInReasonableRange = weatherApp.IsTemperatureInReasonableRange(temperature3);

        Assertions.assertTrue(isTemperatureInReasonableRange);
    }


    @Test
    public void WeatherApp_IsValidLatitudeCoordinate_Positive() throws Exception {
        double latitudeCoordinate2 = 89.9;

        boolean isValidLatitudeCoordinate = weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate2);

        Assertions.assertTrue(isValidLatitudeCoordinate);
    }

    @Test
    public void WeatherApp_IsValidLatitudeCoordinate_Negative() throws Exception {
        double latitudeCoordinate = -89;

        boolean isValidLatitudeCoordinate = weatherApp.IsValidLatitudeCoordinate(latitudeCoordinate);

        Assertions.assertTrue(isValidLatitudeCoordinate);
    }

    @Test
    public void WeatherApp_IsValidLongitudeCoordinate_Positive() throws Exception {
        double longitudeCoordinate = 179;

        boolean isValidLongitudeCoordinate = weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate);

        Assertions.assertTrue(isValidLongitudeCoordinate);
    }

    @Test
    public void WeatherApp_IsValidLongitudeCoordinate_Negative() throws Exception {
        double longitudeCoordinate2 = -179.9;

        boolean isValidLongitudeCoordinate = weatherApp.IsValidLongitudeCoordinate(longitudeCoordinate2);

        Assertions.assertTrue(isValidLongitudeCoordinate);
    }

    @Test
    public void WeatherApp_IsHighTempHigherThanLowest_OneTenth() throws Exception {
        double highTemp = 200.4;
        double lowTemp = 200.3;

        boolean isHighTempHigherThanLowest = weatherApp.isHighTempHigherThanLowest(highTemp, lowTemp);

        Assertions.assertTrue(isHighTempHigherThanLowest);

    }

    @Test
    public void WeatherApp_IsHighTempHigherThanLowest_SameNumber() throws Exception {
        double highTemp2 = 200.39;
        double lowTemp2 = 200.39;

        boolean highTempHigherThanLowest = weatherApp.isHighTempHigherThanLowest(highTemp2, lowTemp2);

        Assertions.assertTrue(highTempHigherThanLowest);

    }

    @Test
    public void WeatherApp_getFutureTemperatures_HighTempsListSizeIsThree() throws Exception {
        double correctSize = 3;
        List<Double> testList;

        testList = weatherApp.getFutureTemperatures(weatherApp.forecastWeather, true);

        Assertions.assertEquals(correctSize, testList.size());

    }

    @Test
    public void WeatherApp_getFutureTemperatures_LowTempsListSizeIsThree() throws Exception {
        double correctSize = 3;
        List<Double> testList2;
        testList2 = weatherApp.getFutureTemperatures(weatherApp.forecastWeather, false);
        Assertions.assertEquals(correctSize, testList2.size());
    }

    @Test
    public void UserInput_IsCityNameCorrect_TwoNames() throws Exception {
        String cityName1 = "San Fransisco";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName1);

        Assertions.assertTrue(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithDash() throws Exception {
        String cityName2 = "Île-de-France";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName2);

        Assertions.assertTrue(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithTwoDashes() throws Exception {
        String cityName3 = "New York--";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName3);

        Assertions.assertFalse(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithWeirdCharacters() throws Exception {
        String cityName4 = "Þorlákshöfn";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName4);

        Assertions.assertTrue(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithDot() throws Exception {
        String cityName5 = "St. Catharines";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName5);

        Assertions.assertTrue(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_StartsWithSpace() throws Exception {
        String cityName6 = " Tallinn";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName6);

        Assertions.assertFalse(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_WrongCharacters() throws Exception {
        String cityName7 = "&&**";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName7);

        Assertions.assertFalse(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_DottedLetters() throws Exception {
        String cityName8 = "München";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName8);

        Assertions.assertTrue(cityNameCorrect);
    }

    @Test
    public void UserInput_IsCityNameCorrect_WhiteSpace() throws Exception {
        String cityName9 = " ";

        boolean cityNameCorrect = readCityName.isCityNameCorrect(cityName9);

        Assertions.assertFalse(cityNameCorrect);
    }

}