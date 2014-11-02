package me.ankur.exampleproblems.acsl.intermediate;

import java.util.Scanner;

/**
 * User: bobacadodl
 * Date: 11/2/14
 * Time: 8:41 AM
 */
public class C3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lines = new String[5];
        for (int i = 0; i < 5; i++) {
            lines[i] = sc.nextLine();
        }
        sc.close();


        for (String line : lines) {
            int number = Integer.parseInt(line.split(",")[0].trim());
            int times = Integer.parseInt(line.split(",")[1].trim());

            boolean isPalindrome = false;
            for (int i = 0; i < times; i++) {
                number += Integer.parseInt(reverse(Integer.toString(number)));
                if (isPalindrome(number)) {
                    isPalindrome = true;
                }
            }

            if (isPalindrome) {
                System.out.println(number);
            } else {
                System.out.println("NONE," + number);
            }
        }
    }


    public static String reverse(String s) {
        return new StringBuilder().append(s).reverse().toString();
    }

    public static boolean isPalindrome(int num) {
        String s = Integer.toString(num);
        return reverse(s).equals(s);
    }
}
