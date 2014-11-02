package me.ankur.algorithms;

import java.util.Scanner;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 7:55 PM
 */
public class PrefixEvaluation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(evaluate(sc));
    }

    public static double evaluate(Scanner input) {
        if (input.hasNextDouble()) {
            return input.nextDouble();
        } else {
            String operator = input.next();
            double operand1 = evaluate(input);
            double operand2 = evaluate(input);
            return evaluate(operator, operand1, operand2);
        }
    }

    public static double evaluate(String operator, double operand1,
                                  double operand2) {
        if (operator.equals("+")) {
            return operand1 + operand2;
        } else if (operator.equals("-")) {
            return operand1 - operand2;
        } else if (operator.equals("*")) {
            return operand1 * operand2;
        } else if (operator.equals("/")) {
            return operand1 / operand2;
        } else if (operator.equals("%")) {
            return operand1 % operand2;
        } else {
            throw new RuntimeException("illegal operator " + operator);
        }
    }
}
