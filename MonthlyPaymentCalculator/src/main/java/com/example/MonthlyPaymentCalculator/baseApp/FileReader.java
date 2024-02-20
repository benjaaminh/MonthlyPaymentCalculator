package com.example.MonthlyPaymentCalculator.baseApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

    //reads how many lines in textfile
    public static int lineReader(String path) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new java.io.FileReader(path));
        int lines = 0;
        while ((line = reader.readLine()) != null) {//if we have a line to read
            if (!line.trim().isEmpty() && !line.trim().equals(".")) {//if line contains text
                lines++;
            }
        }
        reader.close();
        return lines;
    }

    //resolves the path for the prospect textfile
    public static String pathResolver() {
        Path path = Paths.get("prospects.txt");
        return path.toString();
    }

}
