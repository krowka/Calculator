package com.example.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    public String evaluate(String string) throws Exception {
        ArrayList<String> arguments = parse(string.replaceAll("\\s+", ""));
        ArrayList<String> RPN_arguments = toRPN(arguments);
        String result = calculateRPN(RPN_arguments);
        if(result.charAt(result.length() - 1) == '0' && result.charAt(result.length() - 2) == '.')
            result = result.substring(0, result.length() - 2);
        return result;
    }

    // RPN - Reverse Polish Notation
    public ArrayList<String> toRPN(ArrayList<String> Strings) throws Exception {
        ArrayList<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();
        for (String arg : Strings) {
//            System.out.println("ARG " + arg);
//            System.out.println("OUTPUT:      " + output.toString());
//            System.out.println("OPERATORS:      " + operators.toString());
            if (isNumber(arg)) {
                output.add(arg);
            } else if (Operator.isOperator(arg)) {
                while (!operators.isEmpty() && !arg.equals("(") &&
                        Operator.getPrecedence(arg) <= Operator.getPrecedence(operators.peek())) {
                    output.add(operators.pop());
                }
                operators.push(arg);
            } else if (arg.equals("(")) {
                operators.push(arg);
            } else if (arg.equals(")")) {
                while (!operators.peek().equals("(") && !operators.isEmpty()) {
                    output.add(operators.pop());
                }
                if (operators.isEmpty())
                    throw new Exception("Mismatched parentheses");
                operators.pop();
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek().equals("("))
                throw new Exception("Mismatched parentheses");
            output.add(operators.pop());
        }
        return output;
    }

    private String calculateRPN(ArrayList<String> tokens) {
        Stack<Double> operands = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                Double number = Double.parseDouble(token);
                operands.push(number);
            } else if (Operator.isOperator(token)) {
                Double value2 = operands.pop();
                Double value1 = operands.pop();
                Double result = Operator.apply(token, value1, value2);
                operands.push(result);
            }
        }
        String result = operands.pop().toString();
        return operands.isEmpty() ? result : "BAD EXPRESSION";
    }

    private ArrayList<String> parse(String expression) {
        ArrayList<String> arguments = new ArrayList<>();
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                int lastIndexOfNumber = getLastIndexOfNumber(i, chars);
                String number = new String(Arrays.copyOfRange(chars, i, lastIndexOfNumber + 1));
                arguments.add(number);
                i = lastIndexOfNumber;
            } else if (Operator.isOperator(Character.toString(chars[i])) // case when operator has only one character
                    || chars[i] == '(' || chars[i] == ')') {
                arguments.add(Character.toString(chars[i]));
            } else { // case when operator has more than one character
                int lastIndexOfOperator = getLastIndexOfOperator(i, chars);
                String operator = new String(Arrays.copyOfRange(chars, i, lastIndexOfOperator + 1));
                arguments.add(operator);
                i = lastIndexOfOperator;
            }
        }

        //handle MINUS at the start of expression or at the start of parentheses
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).equals("-") && i == 0)
                arguments.add(i, "0");
            else if (arguments.get(i).equals("-") && arguments.get(i-1).equals("("))
                arguments.add(i, "0");
        }

        return arguments;
    }

    private boolean validateRPN(ArrayList<String> tokens) {
        int counter = 0;
        for (String token : tokens) {
            if (isNumber(token))
                counter++;
            else if (Operator.isOperator(token)) {
                counter -= 2;
                if (counter < 0)
                    return false;
                counter++;
            }
        }
        return (counter == 1);
    }

    private int getLastIndexOfNumber(int startIndex, char[] chars) {
        int lastIndex = startIndex;
        while (lastIndex < chars.length &&
                Character.toString(chars[lastIndex]).matches("[0-9\\.]")) {
            lastIndex++;
        }
        return lastIndex - 1;
    }

    private int getLastIndexOfOperator(int startIndex, char[] chars) {
        int lastIndex = startIndex;
        while (lastIndex < chars.length &&
                Character.toString(chars[lastIndex]).matches("^[0-9\\.\\(\\)]")) {
            lastIndex++;
        }
        return lastIndex - 1;
    }

    private boolean isNumber(String string) {
        return string.matches("[0-9\\.]+");
    }
}
