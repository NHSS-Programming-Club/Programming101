package me.ankur.algorithms;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 8:21 PM
 * <p/>
 * VERY SLIGHTLY MODIFIED IMPLEMENTATION OF INFIX ALGORITHM at http://www.geeksforgeeks.org/expression-evaluation/
 * Couldn't do it myself :(
 */
public class InfixEvaluation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        StringTokenizer st = new StringTokenizer(input, "+-*/()", true);
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (Character.isDigit(t.charAt(0))) {
                values.push(Integer.parseInt(t));
            }
            // Current token is an opening brace, push it to 'ops'
            else if (t.equals("("))
                ops.push(t.charAt(0));

                // Closing brace encountered, solve entire brace
            else if (t.equals(")")) {
                while (ops.peek() != '(')
                    // push evaluation of (last operator, operand b, and operand a)
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (isOp(t)) {
                char op = t.charAt(0);
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(op, ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(op);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        System.out.println(values.pop());
    }

    public static boolean isOp(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
