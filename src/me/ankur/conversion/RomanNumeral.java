package me.ankur.conversion;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 2:42 PM
 */
public class RomanNumeral {
    private static int[] numbers = {1000, 900, 500, 400, 100, 90,
            50, 40, 10, 9, 5, 4, 1};

    private static String[] letters = {"M", "CM", "D", "CD", "C", "XC",
            "L", "XL", "X", "IX", "V", "IV", "I"};


    public static String numToString(int num) {
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            while (num > numbers[i]) {
                num -= numbers[i];
                roman.append(letters[i]);
            }
        }
        return roman.toString();
    }


    public static int stringToNum(String s) {
        int i = 0;
        int sum = 0;

        while (i < s.length()) {
            char letter = s.charAt(i);
            int number = letterToNumber(letter);

            i++;
            //if at last pos, add normally
            if (i == s.length()) {
                sum += number;
            } else {
                // handle subtraction, such as IX -> 9 or CM -> 900
                int nextNumber = letterToNumber(s.charAt(i));
                if (nextNumber > number) {
                    //subtract
                    sum += nextNumber - number;
                    i++;
                } else {
                    //dont subtract
                    sum += number;
                }
            }
        }
        return sum;
    }

    private static int letterToNumber(char letter) {
        switch (letter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
