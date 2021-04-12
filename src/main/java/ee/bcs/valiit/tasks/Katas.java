package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Katas {
    public static void main(String[] args) {
        encode("Romani ite domum2fdsg");
    }

    public static String encode(String s) {
        //https://www.codewars.com/kata/5a24a35a837545ab04001614/train/java

        // make perfect square for the string to fit in
        // perfect square is multiplication of itself
        int i = 0;
        for (i = 0; i*i <= s.length(); i++) {
        }
        i--;

        // if string (17 for example) almost fitted in last perfect square, make square one bigger (from 4x4 to 5x5 for example)
        if (s.length() != (i * i)) {
            i += 1;
            System.out.println("String length is = " + s.length() + " and perfect square for it is = " + i + " times " + i);
        }
        // print this if it was perfect (16 is 4x4 grid)
        System.out.println("String length is = " + s.length() + " and perfect square for it is = " + i + " times " + i);

        // make char array long as perfect square
        char[] ch = new char[i*i];

        // put string in char array, fill string end with spaces
        // so it will be easier to but string into spiral array later on
        for (int j = 0; j < (i*i); j++) {
            if (j < s.length()){
                ch[j] = s.charAt(j);
            } else {
            // if reached the end of string, start adding spaces
                ch[j] = ' ';
            }
        }
        System.out.println(i);
        System.out.println(Arrays.toString(ch));

        // i is number of row and columns
        int arr[][] = new int[i][i];
        //                    m n
        int test[][] = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println(Arrays.toString(test));

        for (int n = 0; n < test.length; n++) {
            for (int m = 0; m < test.length; m++) {
                System.out.print(test[n][m] + " ");
            }
            System.out.println();
        }

        // TODO
        //spiral array allocation
        //use TOP, LEFT, DOWN, RIGHT counter?

/* Encoding sequence for a 5 x 5 square:
[ 1  5  9 13  2]
[16 17 21 18  6]
[12 24 25 22 10]
[ 8 20 23 19 14]
[ 4 15 11  7  3]
*/


        return s;
    }

    public static String decode(String s) {
        // Your code here!
        return "";
    }
}