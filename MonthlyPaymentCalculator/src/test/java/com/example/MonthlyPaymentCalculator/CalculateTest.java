package com.example.MonthlyPaymentCalculator;

import com.example.MonthlyPaymentCalculator.baseApp.Calculate;
import com.example.MonthlyPaymentCalculator.baseApp.FileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateTest {

    @Test
    void power() {
        assertAll(
                "Testing power method",
                ()->assertEquals(49, Calculate.power(7,2)),
                ()->  assertEquals(10000000000L,Calculate.power(10,10)));
    }

    @Test
    void lineReader() {
        assertAll(
                "Testing line reader method",
                () -> assertEquals(5, FileReader.lineReader("prospects.txt")),
                () -> assertEquals(7, FileReader.lineReader("test.txt")));
    }

    @Test
    void round() {
        assertAll(
                "Testing rounding method",
                () -> assertEquals(5.14, Calculate.round(5.1359)),
                () -> assertEquals(7.19, Calculate.round(7.192)));
    }

    @Test
    void getMonthlyPayment() {
        assertAll(
                "Testing that monthly payment is calculated correctly",
                () -> assertEquals(4512.92, Calculate.getMonthlyPayment(50000,15,1)),
                 ()->assertEquals(46.06,Calculate.getMonthlyPayment(2000,5,4)));
    }

    @Test
    void printFormula() {
        assertAll(
                "Testing that the formula is printed out correctly",
                () -> assertEquals(String.format("wants to borrow 50000.0 € for a period of 1 years and pay 4512.92 € each month %n"), (Calculate.printFormula(50000,15,1))),
                ()->assertEquals(String.format("wants to borrow 2000.0 € for a period of 4 years and pay 46.06 € each month %n"),(Calculate.printFormula(2000,5,4))));
    }

}