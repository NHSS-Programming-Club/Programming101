package me.ankur.algorithms;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 7:38 PM
 */
public class PostfixEvaluation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        StringTokenizer st = new StringTokenizer(input);
        Stack<Integer> stack = new Stack<Integer>();

        //good way to read symbol by symbol
        while (st.hasMoreTokens()) {
            String operator = st.nextToken();

            //remove top 2 elements of stack, the 2 operands
            int b = stack.pop();
            int a = stack.pop();

            //if adding, remove top 2 elements of stack and replace with their sum
            if (operator.equals("+")) {
                stack.push(a + b);
            } else if (operator.equals("-")) {
                stack.push(a - b);
            } else if (operator.equals("*")) {
                stack.push(a * b);
            } else if (operator.equals("/")) {
                stack.push(a / b);
            }
            //if not an operator, add the number to the stack
            else {
                stack.push(Integer.parseInt(operator));
            }
        }
        System.out.println(stack.pop());
    }
}
