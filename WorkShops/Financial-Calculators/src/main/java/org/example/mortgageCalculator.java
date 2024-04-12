package org.example;


import java.util.Scanner;

public class mortgageCalculator {
    // Shared variables among methods
    static double principal;
    static double yearlyInterest;
    static double loanLengthYears;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello, please enter your name here: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + ", welcome to our mortgage calculator.");
        System.out.print("What is your loan amount?: ");
        principal = scanner.nextDouble();
        System.out.print("Ok!\nWhat is your interest rate?: ");
        yearlyInterest = scanner.nextDouble();
        System.out.print("Great!\nWhat is your loan length in years?: ");
        loanLengthYears = scanner.nextDouble();
        System.out.println("\n-------------------------");

        information();  // Now calling information method to calculate and display the mortgage details
    }

    public static void information() {
        double interestMonths = interestMonths(yearlyInterest);
        double loanLengthMonths = loanLengthMonths(loanLengthYears);
        double monthlyPay = monthlyPayment(interestMonths, loanLengthMonths, principal);
        double totalInterest = totalInterest(monthlyPay, principal, loanLengthMonths);
        System.out.println("This is your information:");
        System.out.println("Principle: " + String.format("%,.2f", principal) +
                "\nYearly interest rate: " + String.format("%.3f", yearlyInterest) + "%" +
                "\nLoan length in years: " + String.format("%.0f", loanLengthYears) +
                "\n-------------------------" +
                "\nTotal monthly payment: " + String.format("%.2f", monthlyPay) +
                "\nTotal interest paid: " + String.format("%.2f", totalInterest));
    }
    //converting from yearly to monthly interest rate
    public static double interestMonths(double yearlyInterest){
        /*double interestMonths =*/ return (yearlyInterest / 12) / 100;
        //return interestMonths;
    }
    //converting from year to months on loan length
    public static double loanLengthMonths(double loanLengthYear){
        /*double loanLengthMonths =*/ return loanLengthYear * 12;
        //return loanLengthMonths;
    }
    //monthly payment calculations
    public static double monthlyPayment (double monthlyInterest, double loanLengthMonths, double principal){
        /*double monthlyPay =*/ return principal *(monthlyInterest *(Math.pow((1 + monthlyInterest), loanLengthMonths) / (Math.pow(1 + monthlyInterest, loanLengthMonths)-1)));
        //return monthlyPay;
    }
    //total interest calculations
    public static double  totalInterest(double monthlyPay, double principle, double loanLengthMonths){
        /*double totalInterest =*/ return ( monthlyPay * loanLengthMonths)- principle;
        //return totalInterest;
    }


}
