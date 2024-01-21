package com.example.MonthlyPaymentCalculator;

import com.example.MonthlyPaymentCalculator.models.Prospect;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Calculate {

    static String pathResolver() {
        Path path = Paths.get("prospects.txt");
        return path.toString();
    }


    static PrintStream out = new PrintStream(System.out, true, UTF_8); //prints out in utf-8 format

    //calculates power to base with input parameters, type as double for large numbers
    static double power(double base, double power) {
        double result = 1;
        //loop while power > 0
        while (power != 0) {
            result = result * base; //result becomes its value times base
            // power is reduced after each multiplication
            power--;
        }
        return result;
    }

    //reads how many lines in textfile
    static int lineReader(String path) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        while ((line = reader.readLine()) != null) {//if we have a line to read
            if (!line.trim().isEmpty() && !line.trim().equals(".")) {//if line contains text
                lines++;
            }
        }
        reader.close();
        return lines;
    }

    public static double round(double value) {
        // Multiply by 100, round to the nearest integer, and then divide by 100
        return (int) (value * 100 + 0.5) / 100.0;
    }

    public static double getMonthlyPayment(double totalLoan, double interest, int years) {
        double MonthlyInterest = (((interest) / 12) / 100);//monthly interest, divided by 100 for percentage
        double MonthlyPayments = years * 12; //how many months when year is given

        double formula = totalLoan * (MonthlyInterest * power((1 + MonthlyInterest), MonthlyPayments)) / (power((1 + MonthlyInterest), MonthlyPayments) - 1);//calculate the formula with given parameters
        return round(formula);
    }

    static String printFormula(double totalLoan, double interest, int years) {
        double formula = getMonthlyPayment(totalLoan, interest, years);

        return String.format("wants to borrow %s € for a period of %s years and pay %s € each month %n", totalLoan, years, formula);

    }

    private static final DecimalFormat df = new DecimalFormat("0.00");//set decimal formatting to two decimals

    public static String calculateFromFile(String path) {
        try {

            int lines = lineReader(path);
            String[] allProspects = new String[lines - 1];
            for (int i = 1; i < lines; i++) {//go through each line

                String line = Files.readAllLines(Paths.get(path)).get(i); //one line
                String[] lineContents = line.split(","); //make an array on each line, where elements separated by commas

                if ((lineContents[0]).startsWith("\"")) {//if name starts with ", like fourth line
                    lineContents[0] = lineContents[0].substring(1);//remove the " double quotes
                }

                String first = String.format("Prospect %d: %s ", i, lineContents[0]); //print out prospect number and name

                if ((lineContents[1]).endsWith("\"")) {//if name ends with " double quotes
                    lineContents[1] = lineContents[1].substring(0, lineContents[1].length() - 1);//delete last quote in surname
                    String lastName = String.format("%s ", lineContents[1]); //and print out last name


                    //in this case, every index is one number larger, because we have two separate names
                    String formula = printFormula(Double.parseDouble(lineContents[2]), Double.parseDouble(lineContents[3]), Integer.parseInt(lineContents[4]));
                    allProspects[i - 1] = first + lastName + formula;
                } else {

                    //normal case where name has not been separated by comma
                    String formula = printFormula(Double.parseDouble(lineContents[1]), Double.parseDouble(lineContents[2]), Integer.parseInt(lineContents[3]));
                    allProspects[i - 1] = first + formula;
                }

            }
            StringBuilder outString = new StringBuilder();
            for (String allProspect : allProspects) {
                outString.append(allProspect);
            }
            return outString.toString();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return path;
    }

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

    public static void main(String[] args) {
        out.printf(calculateFromFile(pathResolver()));
    }

}
