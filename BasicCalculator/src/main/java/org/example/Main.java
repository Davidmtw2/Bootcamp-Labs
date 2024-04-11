package org.example;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter first number: ");
        float firstNumber = scanner.nextFloat();

        System.out.print("Enter second number: ");
        float secondNumber = scanner.nextFloat();

        displayMenu();
        userInput(firstNumber, secondNumber);
    }
    public static void displayMenu() {
        System.out.println("Possible calculations: ");
        System.out.println("\t(A)dd");
        System.out.println("\t(S)ubtract");
        System.out.println("\t(M)ultiply");
        System.out.println("\t(D)ivide");
        System.out.print("Please select an option: ");

    }
    public static void userInput(float firstNumber, float secondNumber) {
        String option = scanner.next();

        if (option.equalsIgnoreCase("A") || option.equalsIgnoreCase("add")) {
            add(firstNumber, secondNumber);
        }
        else if (option.equalsIgnoreCase("S") || option.equalsIgnoreCase("subtract")) {
            sub(firstNumber, secondNumber);
        }
        else if (option.equalsIgnoreCase("M") || option.equalsIgnoreCase("multiply")) {
            multiply(firstNumber, secondNumber);
        }
        else if (option.equalsIgnoreCase("D") || option.equalsIgnoreCase("divide")) {
            divide(firstNumber, secondNumber);
        }

    }
    public static void add(float firstNumber, float secondNumber) {
        System.out.println(firstNumber + " + " + secondNumber + " = " + (firstNumber + secondNumber));
    }
    public static void sub(float firstNumber, float secondNumber) {
        System.out.println(secondNumber + " - " + firstNumber + " = " + (secondNumber - firstNumber));
    }
    public static void multiply(float firstNumber, float secondNumber) {
        System.out.println(firstNumber + " * " + secondNumber + " = " + (firstNumber * secondNumber));
    }
    public static void divide(float firstNumber, float secondNumber) {
        System.out.println(firstNumber + " / " + secondNumber + " = " + (firstNumber / secondNumber));
    }








//   public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter first number: ");
//        float firstNumber = scanner.nextFloat();
//
//        System.out.print("Enter second number: ");
//        float secondNumber = scanner.nextFloat();
//
//
//        System.out.println("Possible calculations: ");
//        System.out.println("\t(A)dd");
//
//        System.out.println("\t(S)ubtract");
//
//        System.out.println("\t(M)ultiply");
//
//        System.out.println("\t(D)ivide");
//
//        System.out.print("Please select an option: ");
//        String option = scanner.next();
//
//        if (option.equalsIgnoreCase("A") || option.equalsIgnoreCase("add")) {
//
//            System.out.println(firstNumber + " + " + secondNumber + " = " + (firstNumber + secondNumber));
//        }
//        else if (option.equalsIgnoreCase("S") || option.equalsIgnoreCase("subtract")) {
//            System.out.println(secondNumber + " - " + firstNumber + " = " + (secondNumber - firstNumber));
//        }
//        else if (option.equalsIgnoreCase("M") || option.equalsIgnoreCase("multiply")) {
//            System.out.println(firstNumber + " * " + secondNumber + " = " + (firstNumber * secondNumber));
//        }
//        else if (option.equalsIgnoreCase("D") || option.equalsIgnoreCase("divide")) {
//            System.out.println(firstNumber + " / " + secondNumber + " = " + (firstNumber / secondNumber));
//        }
//    }
}