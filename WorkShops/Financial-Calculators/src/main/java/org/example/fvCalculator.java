package org.example;

import java.util.Scanner;

public class fvCalculator {
    static double depositAmount;
    static double interestRate;
    static double loanTimeInYears;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + ", welcome to our Future value calculator.");
        System.out.print("What is you deposit amount: ");
        depositAmount = scanner.nextDouble();
        System.out.print("What is you interest rate: ");
        interestRate = scanner.nextDouble();
        System.out.print("What is the amount of years of your loan: ");
        loanTimeInYears = scanner.nextDouble();


        outPut();

    }
    public static void outPut(){
        // function calling
        System.out.println("\n----------------------------------------------------");
        double dailyInterestRate = dailyInterestRate(interestRate);

        double futureValue = futureValue(depositAmount, interestRate, loanTimeInYears);
        System.out.println("This is your future value: " + String.format("%,.2f", futureValue));

        double totalInterest = totalInterest(futureValue, depositAmount);
        System.out.println("This is your total interest: " + String.format("%,.2f", totalInterest));
    }
    // mathematical calculations
    public static double dailyInterestRate(double interestRate) {
        return (interestRate / 365 / 100);
    }
    public static double futureValue(double depositAmount, double interestRate, double loanTimeInYears) {
        return Math.pow(1 + (interestRate/ 365 / 100), (365 * loanTimeInYears)) * depositAmount;
//        FV = P(1 + r/n)^(n*t)
//        FV = initialDeposit((1 + (annualInterest/365))^(365*numberOfYears))
//        FV is the future value of the deposit.
//        P is the initial deposit amount.
//        r is the annual interest rate (in decimal form, e.g., 0.0175 for 1.75%).
//        n is the number of times the interest is compounded per year (assuming daily compounding, n would be 365).
//        t is the number of years the money is invested for.

    }
    public static double totalInterest(double futureValue, double depositAmount) {
        return futureValue - depositAmount;
    }
}

