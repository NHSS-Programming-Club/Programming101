package me.ankur.conversion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * User: bobacadodl
 * Date: 11/2/14
 * Time: 12:08 PM
 */
public class WordToNumber2 {

    //Coded this on my own....
    //works but there may still be some issues with Invalid input checking

    //TODO make sure you can only use each magnitude once! this is a problem aka "five thousand six hundred thousand"

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.nextLine();
        sc.close();

        List<String> words = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(number, " ", false);

        int ret = 0;

        boolean error = false;

        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            int magnitude = getMagnitude(word);
            if (magnitude > 1) {

                if (words.size() > 0) {
                    int lessThanThousand = convertLessThanThousand(listToString(words));

                    if (lessThanThousand == -1) {
                        error = true;
                        break;
                    }

                    ret += Math.pow(10, magnitude) * lessThanThousand;
                    words.clear();
                } else {
                    error = true;
                    break;
                }
            } else {
                words.add(word);
            }
        }

        if (!error) {
            if (!words.isEmpty()) {
                int lessThanThousand = convertLessThanThousand(listToString(words));
                if (lessThanThousand == -1) {
                    error = true;
                } else {
                    ret += convertLessThanThousand(listToString(words));
                }
            }
        }
        System.out.println(error ? "I do not understand" : ret);
    }

    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            sb.append(word);
            sb.append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String[] MAGNITUDES = {"thousand", "million", "billion", "trillion", "quadrillion"};

    public static int getMagnitude(String word) {
        for (int i = 0; i < MAGNITUDES.length; i++) {
            if (MAGNITUDES[i].equals(word)) {
                return 3 * (i + 1);
            }
        }
        return 1;
    }

    public static int convertLessThanThousand(String word) {
        word = word.trim();
        if (word.contains("hundred")) {
            String[] split = word.split("hundred");

            if (split.length == 2) {
                String hundreds = split[0].trim();
                String lessThanHundredWord = split[1].trim();

                int lessThanHundred = convertLessThanHundred(lessThanHundredWord);
                if (lessThanHundred == -1) return -1;

                for (int i = 0; i < DIGITS.length; i++) {
                    if (DIGITS[i].equals(hundreds)) {
                        return lessThanHundred + 100 * (i + 1);
                    }
                }
                return -1;
            } else {
                String hundreds = split[0].trim();
                for (int i = 0; i < DIGITS.length; i++) {
                    if (DIGITS[i].equals(hundreds)) {
                        return 100 * (i + 1);
                    }
                }
                return -1;
            }
        } else {
            return convertLessThanHundred(word);
        }
    }

    public static final String[] DIGITS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static final String[] TENS = {null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    public static final String[] TEENS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    public static int convertLessThanHundred(String word) {
        word = word.trim();
        String[] split = word.split(" ");
        if (split.length == 2) { //ninety nine, twenty two, etc
            int ret = 0;

            boolean matchedTen = false;
            for (int i = 1; i < TENS.length; i++) {
                if (TENS[i].equals(split[0])) {
                    ret = 10 * (i + 1);
                    matchedTen = true;
                    break;
                }
            }
            if (!matchedTen) {
                return -1;
            }

            boolean matchedDigit = false;
            for (int i = 0; i < DIGITS.length; i++) {
                if (DIGITS[i].equals(split[1])) {
                    ret += i + 1;
                    matchedDigit = true;
                    break;
                }
            }
            if (!matchedDigit) {
                return -1;
            }
            return ret;
        } else { //singles, aka one two ten eleven, twenty
            for (int i = 0; i < DIGITS.length; i++) {
                if (DIGITS[i].equals(split[0])) {
                    return i + 1;
                }
            }

            for (int i = 0; i < TEENS.length; i++) {
                if (TEENS[i].equals(split[0])) {
                    return i + 10;
                }
            }

            for (int i = 1; i < TENS.length; i++) {
                if (TENS[i].equals(split[0])) {
                    return 10 * (i + 1);
                }
            }

            return -1;
        }
    }

}
