package com.example.MonthlyPaymentCalculator;

import com.example.MonthlyPaymentCalculator.models.Prospect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.MonthlyPaymentCalculator.baseApp.Calculate.*;
import static com.example.MonthlyPaymentCalculator.baseApp.FileReader.lineReader;
import static com.example.MonthlyPaymentCalculator.baseApp.FileReader.pathResolver;

public class InitialData {
    //getting the initial data for spring app (reading prospects from file)

    public static Prospect[] initialData() throws IOException {
        String path = pathResolver();
        int lines = lineReader(path);
        Prospect[] allProspects = new Prospect[lines - 1];
        for (int i = 1; i < lines; i++) {//go through each line

            String line = Files.readAllLines(Paths.get(path)).get(i); //one line
            String[] lineContents = line.split(","); //make an array on each line, where elements separated by commas

            if ((lineContents[0]).startsWith("\"")) {//if name starts with ", like fourth line
                lineContents[0] = lineContents[0].substring(1);//remove the " double quotes
            }

            String name = lineContents[0];

            if ((lineContents[1]).endsWith("\"")) {//if name ends with " double quotes
                lineContents[1] = lineContents[1].substring(0, lineContents[1].length() - 1);//delete last quote in surname
                name += String.format(" %s", lineContents[1]);
                double totalLoan = Double.parseDouble(lineContents[2]);
                double interest = Double.parseDouble(lineContents[3]);
                int years = Integer.parseInt(lineContents[4]);


                //in this case, every index is one number larger, because we have two separate names
                double formula = getMonthlyPayment(Double.parseDouble(lineContents[2]), Double.parseDouble(lineContents[3]), Integer.parseInt(lineContents[4]));
                allProspects[i - 1] = new Prospect(name, totalLoan, interest, years, formula);
            } else {
                double totalLoan = Double.parseDouble(lineContents[1]);
                double interest = Double.parseDouble(lineContents[2]);
                int years = Integer.parseInt(lineContents[3]);
                //normal case where name has not been separated by comma
                double formula = getMonthlyPayment(Double.parseDouble(lineContents[1]), Double.parseDouble(lineContents[2]), Integer.parseInt(lineContents[3]));

                allProspects[i - 1] = new Prospect(name, totalLoan, interest, years, formula);
            }

        }


        return allProspects;
    }
}
