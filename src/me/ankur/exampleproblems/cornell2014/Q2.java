package me.ankur.exampleproblems.cornell2014;

import java.util.Scanner;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 6:47 PM
 */
public class Q2 {
    //lol, roman numerals

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        String[] toConvert = new String[num];

        for (int i = 0; i < num; i++) {
            toConvert[i] = sc.nextLine();
        }
        sc.close();

        for (String s : toConvert) {
            if (Character.isDigit(s.charAt(0))) {
                System.out.println(intToRoman(Integer.parseInt(s)));
            } else {
                System.out.println(romanToInt(s));
            }
        }
    }

    public static String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static String intToRoman(int num) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                ret.append(letters[i]);
            }
        }
        return ret.toString();
    }

    public static int romanToInt(String roman) {
        int sum = 0;
        for (int i = 0; i < roman.length(); i++) {
            char c = roman.charAt(i);
            int val = charToValue(c);

            if (i < roman.length() - 1) {
                char nextChar = roman.charAt(i + 1);
                int nextVal = charToValue(nextChar);

                if (nextVal > val) {
                    sum += nextVal - val;
                    i++;
                } else {
                    sum += val;
                }
            } else {
                sum += val;
            }
        }
        return sum;
    }

    public static int charToValue(char c) {
        switch (c) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }
}
