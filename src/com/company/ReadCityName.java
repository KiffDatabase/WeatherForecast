package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCityName {
    String cityName = "xx";
    Scanner in = new Scanner(System.in);

    public String readUserInput(){
        System.out.println("Please choose input method(file or console):");
        String input = in.nextLine();
        switch (input) {
            case "file":
                readFile();
                break;
            case "console":
                System.out.println("Please enter city name: ");
                cityName = in.nextLine();
                if (!isCityNameCorrect(cityName)) {
                    readUserInput();
                }
                break;
            default:
                System.out.println("Wrong Method. Try again");
                readUserInput();
                break;
        }
        return cityName;
    }

    public void readFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("/User/alvinreyes"));
        int result = jFileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = new File(String.valueOf(jFileChooser.getSelectedFile()));
            try {
                in = new Scanner(selectedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (in.hasNextLine()) {
                cityName = in.nextLine();
                if (!isCityNameCorrect(cityName)) {
                    readUserInput();
                }
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
