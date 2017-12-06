package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportWeatherForecast {

    public void printForecastToConsole(List<String> outputList) throws IOException {
        for(String s : outputList) {
            System.out.println(s);
        }
    }

    public void saveForecastToFile(List<List<String>> outputFile) throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\karlivar.pajula\\Documents\\School\\Automaattestimine\\output.txt");
        BufferedWriter bw = new BufferedWriter(writer);
        for(List<String> list : outputFile) {
            for(String line : list) {
                bw.write(line + System.getProperty("line.separator"));
            }
        }
        bw.close();
    }

}


