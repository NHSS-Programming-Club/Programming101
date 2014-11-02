package me.ankur.exampleproblems.cornell2014;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 6:33 PM
 */
public class Q8 {
    //Postfix calculator! (mod 256)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        StringTokenizer st = new StringTokenizer(input);
        Stack<Integer> stack = new Stack<Integer>();

        //good way to read symbol by symbol
        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            //if adding, remove top 2 elements of stack and replace with their sum
            if (token.equals("+")) {
                stack.push((stack.pop() + stack.pop()) % 256);
            }
            //if multiplying, remove top 2 elements of stack and replace with their product
            else if (token.equals("*")) {
                stack.push((stack.pop() * stack.pop()) % 256);
            }
            //if see token =, print out last element in stack
            else if (token.equals("=")) {
                System.out.println(stack.peek());
            }
            //if token is "quit" , exit the program
            else if (token.equals("quit")) {
                return;
            }
            //if not an operator, add the number to the stack
            else {
                stack.push(Integer.parseInt(token));
            }
        }
    }
}
