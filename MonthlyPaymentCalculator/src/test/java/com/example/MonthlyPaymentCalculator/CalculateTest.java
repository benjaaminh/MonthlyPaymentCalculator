package com.example.MonthlyPaymentCalculator;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {
    static PrintStream out = new PrintStream(System.out, true, UTF_8); //prints out in utf-8 format

    @Test
    void power() {
        assertAll(
                "Testing power method",
                ()->assertEquals(49,Calculate.power(7,2)),
                ()->  assertEquals(10000000000L,Calculate.power(10,10)));
    }

    @Test
    void lineReader() {
        assertAll(
                "Testing line reader method",
                () -> assertEquals(5, Calculate.lineReader("prospects.txt")),
                () -> assertEquals(7, Calculate.lineReader("test.txt")));
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