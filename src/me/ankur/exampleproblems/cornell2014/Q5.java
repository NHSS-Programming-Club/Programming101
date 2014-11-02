package me.ankur.exampleproblems.cornell2014;

import java.util.Scanner;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 6:10 PM
 */
public class Q5 {

    //INCOMPLETE

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            String[] strings = sc.nextLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int[] numColors = new int[n];
            for (int j = 0; j < n; j++) {
                numColors[j + 1] = Integer.parseInt(strings[j + 1]);
            }
            int x = Integer.parseInt(strings[n + 1]);


            while (x > 0) {
                int max = 0;
                int maxidx = -1;
                int max2 = 0;
                //int max2idx = -1;
                for (int j = 0; j < numColors.length; j++) {
                    int num = numColors[j];
                    if (num > max) {
                        max2 = max;
                        //max2idx = maxidx;
                        max = num;
                        maxidx = j;
                    } else if (num > max2) {
                        max2 = num;
                        //max2idx=j;
                    }
                }

                if (max != max2) {
                    //easy, just reduce highest to the second highest.
                    int diff = max - max2;
                    x -= diff;
                    numColors[maxidx] = max2;
                } else {
                    //crap
                }
            }
        }
    }
}
