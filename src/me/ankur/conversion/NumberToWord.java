package me.ankur.conversion;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * User: bobacadodl
 * Date: 11/2/14
 * Time: 11:11 AM
 */
public class NumberToWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // insert commas
        int idx = s.length() - 3;
        while (idx > 0) {
            s = s.substring(0, idx) + "," + s.substring(idx);
            idx -= 3;
        }

        //break into tokens
        StringTokenizer st = new StringTokenizer(s, ",");

        String words = "";
        int base = 0;
        while (st.hasMoreTokens()) {
            String triplet = process(st.nextToken());
            if (triplet.length() > 0) {
                triplet += " BASE" + base;
            }

            // advance even if missing.
            base++;

            words = words + triplet;
            if (st.hasMoreElements()) {
                words += ",";
            }
        }

        // trim missing triplets.
        int dbl = words.indexOf(",,");
        while (dbl != -1) {
            words = words.replace(",,", ",");
            dbl = words.indexOf(",,");
        }

        // convert bases. Most could be BASE0 BASE1 BASE2
        if (base == 3) {
            words = words.replace(" BASE0", " MILLION");
            words = words.replace(" BASE1", " THOUSAND");
            words = words.replace(" BASE2", "");
        } else if (base == 2) {
            words = words.replace(" BASE0", " THOUSAND");
            words = words.replace(" BASE1", "");
        } else if (base == 1) {
            words = words.replace(" BASE0", "");
        }

        words = words.replace(",", ", ");
        words = words.trim();
        if (words.endsWith(",")) {
            words = words.substring(0, words.length() - 1);
        }
        System.out.println(words);
    }

    static String[] actualWords = {"ZERO", "ONE", "TWO", "THREE", "FOUR",
            "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"
    };
    static String[] tens = {"", "TEN", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"};

    static String[] special = {"", "", "", "", "", "", "", "", "", "", "",
            "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};


    private static String process(String triple) {
        String response = "";
        if (triple.length() == 1) {
            triple = "00" + triple;
        } else if (triple.length() == 2) {
            triple = "0" + triple;
        }
        if (triple.equals("000")) {
            return response;
        }

        int val = Integer.valueOf(triple);
        if (val >= 1 && val <= 9) {
            return actualWords[val];
        }
        if (val >= 11 && val <= 19) {
            return special[val];
        }

        boolean space = false;
        if (val >= 100) {
            int hund = val / 100;
            response = response + actualWords[hund] + " HUNDRED";
            val -= hund * 100;
            space = true;
        }

        if (val % 10 == 0 && val >= 10) {
            if (space) {
                response += " ";
                space = false;
            }
            response += tens[val / 10];
            return response;
        }

        if (val == 0) {
            return response;
        }
        if (space) {
            response += " ";
            space = false;
        }
        if (val >= 11 && val <= 19) {
            response += special[val];
            return response;
        }

        response += tens[val / 10];
        boolean addHyphen = true;
        if (val < 20) {
            addHyphen = false;
        }
        val = val % 10;
        if (val != 0 && addHyphen) {
            response += "-";
        }
        response += actualWords[val];

        return response;
    }
}
