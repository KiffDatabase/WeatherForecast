package com.company;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCityNames {


    String[] inputCitiesList;
    List<String> outputCitiesList = new ArrayList<>();
    String cityName = null;
    Scanner in = new Scanner(System.in);


    public List<String> getOutputCitiesList() {

        return outputCitiesList;
    }

    public void readUserInput() throws IOException, ClassNotFoundException {
        System.out.println("Please choose input method(file or console):");
        String input = in.nextLine();
        switch (input) {
            case "file":
                try {
                    inputCitiesList = parseInputCitiesList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                readInputCities(inputCitiesList);
                break;
            case "console":
                System.out.println("Please enter city name: ");
                cityName = in.nextLine();
                if (isCityNameCorrect(cityName)) {
                    outputCitiesList.add(cityName);
                }else{
                    readUserInput();
                }
                break;
            default:
                System.out.println("Wrong Method. Try again");
                readUserInput();
                break;
        }
    }

    public String readFilePath() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("/User/alvinreyes"));
        int result = jFileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            return String.valueOf(jFileChooser.getSelectedFile().getAbsolutePath());
        }else {
            return "Error in file reading";
        }
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String[] parseInputCitiesList() throws IOException, ClassNotFoundException {
        String pathName = readFilePath();
        File file = new File(pathName);
        ObjectMapper mapper = getObjectMapper();
        try {
            CityResponse cityResponse = mapper.readValue(file, CityResponse.class);
            return cityResponse.getCities().getCity();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void readInputCities(String[] CitiesList){
        for (String s: CitiesList) {
            if (isCityNameCorrect(s)) {
                outputCitiesList.add(s);
            }
        }
    }


    public boolean isCityNameCorrect(String insertedCityName){
        if(insertedCityName.matches("^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")){
            return true;
        }else{
            System.out.println("Not valid city name");
            return false;
        }

    }
}
