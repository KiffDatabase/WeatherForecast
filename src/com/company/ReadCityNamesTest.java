package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadCityNamesTest {

    ReadCityNames readCityNames = new ReadCityNames();

    @Test
    public void UserInput_IsCityNameCorrect_TwoNames() throws Exception {
        String cityName1 = "San Fransisco";

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName1));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithDash() throws Exception {
        String cityName2 = "Île-de-France";

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName2));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithTwoDashes() throws Exception {
        String cityName3 = "New York--";

        Assertions.assertFalse(readCityNames.isCityNameCorrect(cityName3));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithWeirdCharacters() throws Exception {
        String cityName4 = "Þorlákshöfn";

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName4));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WithDot() throws Exception {
        String cityName5 = "St. Catharines";

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName5));
    }

    @Test
    public void UserInput_IsCityNameCorrect_StartsWithSpace() throws Exception {
        String cityName6 = " Tallinn";

        Assertions.assertFalse(readCityNames.isCityNameCorrect(cityName6));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WrongCharacters() throws Exception {
        String cityName7 = "&&**";

        Assertions.assertFalse(readCityNames.isCityNameCorrect(cityName7));
    }

    @Test
    public void UserInput_IsCityNameCorrect_DottedLetters() throws Exception {
        String cityName8 = "München";

        Assertions.assertTrue(readCityNames.isCityNameCorrect(cityName8));
    }

    @Test
    public void UserInput_IsCityNameCorrect_WhiteSpace() throws Exception {
        String cityName9 = " ";

        Assertions.assertFalse(readCityNames.isCityNameCorrect(cityName9));
    }
}
