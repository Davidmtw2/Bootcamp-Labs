package org.example;

import java.util.Scanner;

public class mortgageCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Intro
        System.out.print("Hello please enter your name Here: ");
        String name = scanner.nextLine();
        System.out.print("Hello " + name + " welcome to ____ mortgage calculator");
        System.out.print("\nWhat is your load amount?: ");
        double principal = scanner.nextDouble();
        System.out.print("Ok!\nWhat is your interest rate?: ");
        double yearlyInterest = scanner.nextDouble();
        System.out.print("Great!\nWhat is your loan length in years?: ");
        double loanLengthYears = scanner.nextDouble();
        System.out.println("\n-------------------------");


        double interestMonths = interestMonths(yearlyInterest);
        double loanLengthMonths = loanLengthMonths(loanLengthYears);
        double monthlyPay = monthlyPayment(interestMonths, loanLengthMonths, principal);
        double totalInterest = totalInterest(monthlyPay, principal, loanLengthMonths);
        System.out.println("This is your information");
        System.out.println("Principle: " + String.format("%,.2f",principal) + "\nYearly interest%: " + String.format("%.2f",yearlyInterest)+"%" + "\nLoan length: " + String.format("%.0f",loanLengthYears) + "\n-------------------------\n" + "Total monthly payment: " + String.format("%.2f",monthlyPay) + "\nTotal interest payed: " + String.format("%.2f",totalInterest));


    }
    public static void information() {

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
