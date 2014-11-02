package me.ankur.algorithms;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 7:40 PM
 */
public class ParenthesizedInfixEvaluation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        StringTokenizer st = new StringTokenizer(input);
        Stack<String> stack = new Stack<String>();
        while (st.hasMoreTokens()) {
            String t = st.nextToken();

            //if its an operation, open parenthesis, or number, add to stack
            if (t.equals("(")
                    || t.matches("[0-9]+")
                    || t.equals("*")
                    || t.equals("/")
                    || t.equals("+")
                    || t.equals("-")) {
                stack.push(t);
            }
            //if its a closing parenthesis, evaluate whatever we have in between the opening parenthesis and this
            else if (t.equals(")")) {
                //read the 2nd number
                int b = Integer.parseInt(stack.pop());
                //reads the operator
                String operator = stack.pop();
                //reads the 1st number
                int a = Integer.parseInt(stack.pop());
                //gets rid of dat opening parenthesis
                stack.pop();

                if (operator.equals("*")) {
                    stack.push(Integer.toString(a * b));
                } else if (operator.equals("/")) {
                    stack.push(Integer.toString(a / b));
                } else if (operator.equals("+")) {
                    stack.push(Integer.toString(a + b));
                } else if (operator.equals("-")) {
                    stack.push(Integer.toString(a - b));
                }
            }
        }

        //print result
        System.out.println(stack.pop());
    }
}
