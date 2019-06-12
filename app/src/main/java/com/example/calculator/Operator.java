package com.example.calculator;

public class Operator {
    // TODO Add modulo and power operator

    public static int getPrecedence(String operator) {
        switch (operator) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return -1;
        }
    }

    public static double apply(String operator, double value1, double value2) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                return value1 / value2;
            default:
                return value1;
        }
    }

    public static boolean isOperator(String string){
        return string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/");
    }
}
