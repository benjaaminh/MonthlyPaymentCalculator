package com.example.MonthlyPaymentCalculator.models;

import jakarta.persistence.*;

@Entity
@Table
public class Prospect {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private double totalLoan;

    private double interest;

    private int years;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    private double monthlyPayment;


    //simple constructor

    public Prospect() {

    }
    //constructor with all fields
    public Prospect(String name, double totalLoan, double interest, int years, double monthlyPayment){
        this.name=name;
        this.totalLoan=totalLoan;
        this.interest=interest;
        this.years=years;
        this.monthlyPayment=monthlyPayment;
    }
}
