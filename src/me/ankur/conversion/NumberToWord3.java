package me.ankur.conversion;

/**
 * User: bobacadodl
 * Date: 11/2/14
 * Time: 11:17 AM
 */
public class NumberToWord3 {

    /*

    LOGIC:

    After thousand number , there is a symmetry in special names like million,billion,trillion,quadrillion,quintillion.
    The symmetry is if you multiply 1000 with thousand than we will get a million.
    - If we multiply 1000 with million then we will get billion.
    - If we multiply 1000 with billion then we get trillion.
    - Similarly,1000 multiply trillion equals quadrillion.
    - 1000 multiply quadrillion equals quintillion.
    This symmetry starts after we reach 1000 number . So we will divide the program into two parts .

    - First part that is function convertLessThanOneThousand(int number) will convert any number smaller than 1000 into words.
    - Second part, starting from extreme-right of input number, we  will use modulus operator by1000,
      to get the last three extreme right digits of the input number.
      Taking three digits at a time from right to left ,
      we will scan the whole input number until it is fully converted into the word.

    */


    private static final String[] specialNames = {
            "",
            " thousand",
            " million",
            " billion",
            " trillion",
            " quadrillion",
            " quintillion"
    };

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " fourty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private static String convertLessThanOneThousand(int number) {
        String current;

        if (number % 100 < 20) {
            current = numNames[number % 100];
            number /= 100;
        } else {
            current = numNames[number % 10];
            number /= 10;

            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) return current;
        return numNames[number] + " hundred" + current;
    }

    public static String convert(int number) {

        if (number == 0) {
            return "zero";
        }

        String prefix = "";

        if (number < 0) {
            number = -number;
            prefix = "negative";
        }

        String current = "";
        int place = 0;

        do {
            int n = number % 1000;
            if (n != 0) {
                String s = convertLessThanOneThousand(n);
                current = s + specialNames[place] + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);

        return (prefix + current).trim();
    }

    public static void main(String[] args) {
        NumberToWord obj = new NumberToWord();
        System.out.println("*** " + convert(123456789));
        System.out.println("*** " + convert(-55));
    }
}
