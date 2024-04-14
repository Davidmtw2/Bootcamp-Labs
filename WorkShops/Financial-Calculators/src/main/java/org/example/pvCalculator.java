package org.example;

import java.util.Scanner;

public class pvCalculator {
    static double monthlyPayment;
    static double interestRate;
    static double years;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //INTRODUCTION
        System.out.print("Hello please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + ", welcome to our present value calculator.");
        System.out.print("What is the annuities monthly payment amount?: $");
        monthlyPayment = scanner.nextDouble();
        System.out.println("Okay!");
        System.out.print("What is your annual interest rate?: %");
        interestRate = scanner.nextDouble();
        System.out.print("How many years to pay out?: ");
        years = scanner.nextDouble();



        outPut();

    }
    public static void outPut() {
        double newInterest = newInterest(interestRate);
        double months = months(years);
        double presentValue = presentValue(monthlyPayment, newInterest, months);
        System.out.println("Monthly pay: " + String.format("$%,.2f", monthlyPayment));
        System.out.println("Interest Rate: " + String.format("%.2f", interestRate) + "%");
        System.out.println("Number of years: " + String.format("%.0f", years));
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("To fund an annuity that pays "+  String.format("$%,.2f", monthlyPayment) +" monthly for the next " + String.format("%.0f", years) +" years \nand earns an expected " + String.format("%.2f", interestRate) + "% interest, you would need to invest "+ String.format("$%,.2f", presentValue) +" today." );
    }



    public static double newInterest(double interestRate) {
        return (interestRate / 12) / 100;

    }


    public static double months(double years) {
        return years * 12;

    }


    public static double presentValue(double monthlyPayment, double newInterest, double months) {
        return monthlyPayment * (1 - Math.pow((1 + newInterest), (-months))) / newInterest;

    }


}
