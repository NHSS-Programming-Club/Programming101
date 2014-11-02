package me.ankur.exampleproblems.acsl.intermediate;

import java.util.Scanner;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 11:24 PM
 *
 * @see <a href="Question prompt">http://www.acsl.org/acsl/sample_ques/c_1_code_int.pdf</a>
 */
public class C1 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            String input = sc.nextLine();
            String[] split = input.split(",");

            StringBuilder output = new StringBuilder();

            char lastVal = 'A';
            for (int j = 0; j < split.length - 1; j++) {
                char c = split[j].charAt(0);
                int val = getNumericalVal(c);

                int result;

                if (c <= 'E') {
                    result = val * 2;
                } else if (c <= 'J') {
                    result = (val % 3) * 5;
                } else if (c <= 'O') {
                    result = val / 4;
                    result *= 8;
                } else if (c <= 'T') {
                    result = 0;
                    while (val != 0) {
                        result += val % 10;
                        val /= 10;
                    }
                    result *= 10;
                } else if (c <= 'Z') {
                    result = 1;
                    for (int f = 2; f < val; f++) {
                        if (val % f == 0)
                            result = f;
                    }
                    result *= 12;
                } else {
                    result = 0;
                }
                lastVal = getCharVal((getNumericalVal(lastVal) + result - 1) % 26 + 1);
                output.append(lastVal);
                output.append(' ');
            }
            output.trimToSize();
            System.out.println(output.toString());
        }
    }

    public static int getNumericalVal(char c) {
        return (c - 'A') + 1;
    }

    public static char getCharVal(int i) {
        return (char) (i - 1 + 'A');
    }
}
