package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        float firstNumber = scanner.nextFloat();

        System.out.print("Enter second number: ");
        float secondNumber = scanner.nextFloat();


        System.out.println("Possible calculations: ");
        System.out.println("\t(A)dd");

        System.out.println("\t(S)ubtract");

        System.out.println("\t(M)ultiply");

        System.out.println("\t(D)ivide");

        System.out.print("Please select an option: ");
        String option = scanner.next();

        if (option.equalsIgnoreCase("A") || option.equalsIgnoreCase("add")) {

            System.out.println(firstNumber + " + " + secondNumber + " = " + (firstNumber + secondNumber));
        }
        else if (option.equalsIgnoreCase("S") || option.equalsIgnoreCase("subtract")) {
            System.out.println(secondNumber + " - " + firstNumber + " = " + (secondNumber - firstNumber));
        }
        else if (option.equalsIgnoreCase("M") || option.equalsIgnoreCase("multiply")) {
            System.out.println(firstNumber + " * " + secondNumber + " = " + (firstNumber * secondNumber));
        }
        else if (option.equalsIgnoreCase("D") || option.equalsIgnoreCase("divide")) {
            System.out.println(firstNumber + " / " + secondNumber + " = " + (firstNumber / secondNumber));
        }
    }
}