package me.ankur.exampleproblems.cornell2014;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 7:04 PM
 */
public class Q3 {

    public static int[] fermatPrimes = new int[]{3, 5, 17, 257, 65537};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        int[] cases = new int[num];
        for (int i = 0; i < num; i++) {
            cases[i] = Integer.parseInt(sc.nextLine());
        }

        for (int n : cases) {
            System.out.println("Case " + n + ":");
            List<Integer> factors = primeFactors(n);

            boolean[] fermatCounts = new boolean[fermatPrimes.length];
            boolean isConstructable = true;
            for (int f : factors) {
                if (!isPowerOf2(f)) {
                    boolean matchedFermat = false;
                    for (int i = 0; i < fermatPrimes.length; i++) {
                        if (f == fermatPrimes[i]) {
                            //if that fermat prime has been used already
                            if (fermatCounts[i]) {
                                break;
                            } else {
                                matchedFermat = true;
                                fermatCounts[i] = true;
                                break;
                            }
                        }
                    }
                    if (!matchedFermat) {
                        isConstructable = false;
                        break;
                    }
                }
            }
            if (isConstructable) {
                System.out.println("constructuable");
            } else {
                System.out.println("non-constructable");
            }
        }
    }

    public static boolean isPowerOf2(int n) {
        while (n > 1) {
            if (n % 2 == 0)
                n /= 2;
            else {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1)
            factors.add(n);
        return factors;
    }
}
