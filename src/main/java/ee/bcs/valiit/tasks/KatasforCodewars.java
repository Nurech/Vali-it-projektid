package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class KatasforCodewars {


    public static void main(String[] args) {
        //encode("1234567891123456ABCDEFGHa1234567a1234567891123456ABCDEFGHa1");
        encode("Sic transit gloria mundi");
        decode("Stsgiriuar i ninmd l otac");
    }


    public static String encode(String s) {
        //https://www.codewars.com/kata/5a24a35a837545ab04001614/train/java

        // make perfect square for the string to fit in
        // perfect square is multiplication of itself
        // i is size of one square side
        int i = 0;
        for (i = 0; i * i <= s.length(); i++) {
        }
        i--;

        // increase cube size by 1 if string didn't fit in a perfect square
        if (s.length() != (i * i)) {
            i += 1;
            // test
            System.out.println("String length is = " + s.length() + " and perfect square for it is = " + i + " times " + i);
        }
        // test
        System.out.println("String length is = " + s.length() + " and perfect square for it is = " + i + " times " + i);

        // make char array long as perfect square
        char[] charArray = new char[i * i];

        // put string in char array, fill string end with spaces
        // so it will be easier to but string into spiral array later on
        for (int j = 0; j < (i * i); j++) {
            if (j < s.length()) {
                charArray[j] = s.charAt(j);
            } else {
                // if reached the end of string, start adding spaces
                charArray[j] = ' ';
            }
        }
        System.out.println(Arrays.toString(charArray));

        // i is number of row and columns
        char square[][] = new char[i][i];

        // set initial values
        i--;
        int circle = i;
        int tracker = 0;
        int character = 0;

        int topY = 0;
        int topX = 0;

        int rightY = 0;
        int rightX = i;

        int downY = i;
        int downX = i;

        int leftY = i;
        int leftX = 0;

        //fill outer cube circle with letters from char array
        String row = "TOP";
        while (character < charArray.length) {

            if (row == "TOP" && character < charArray.length) {
                square[topY][topX++] = charArray[character];
                character++;
                row = "RIGHT";
            }
            if (row == "RIGHT" && character < charArray.length) {
                square[rightY++][rightX] = charArray[character];
                character++;
                row = "DOWN";
            }
            if (row == "DOWN" && character < charArray.length) {
                square[downY][downX--] = charArray[character];
                character++;
                row = "LEFT";
            }
            if (row == "LEFT" && character < charArray.length) {
                square[leftY--][leftX] = charArray[character];
                character++;
                row = "TOP";
            }

            // if outer circle is filled, move to inner circle
            // leftY is always last to be filled when filling clockwise
            if (leftY == (tracker)) {

                topY = 1 + tracker;
                topX = 1 + tracker;

                rightY = 1 + tracker;
                rightX = circle - (1 + tracker);

                downY = circle - (1 + tracker);
                downX = circle - (1 + tracker);

                leftY = circle - (1 + tracker);
                leftX = 1 + tracker;

                tracker++;
            }
        }
        // test print char array contents
        for (int n = 0; n < square.length; n++) {
            for (int m = 0; m < square.length; m++) {
                System.out.print(square[n][m] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //read 2d array back to one-liner
        int length = 0;
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < square.length; n++) {
            for (int m = 0; m < square.length; m++) {
                sb.insert(length, square[n][m]);
                length++;
            }
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    //TODO
    public static String decode(String s) {
        //https://www.codewars.com/kata/5a24a35a837545ab04001614/train/java

        // make perfect square for the string to fit in
        // perfect square is multiplication of itself
        // i is size of one square side
        int i = 0;
        while(i*i <= s.length()){
            i++;
        }
        i--;

        // increase cube size by 1 if string didn't fit in a perfect square
        if (s.length() != (i * i)) {
            i += 1;
            // test
            System.out.println("String length is = " + s.length() + " and perfect square for it is = " + i + " times " + i);
        }
        // test
        System.out.println("String length is = " + s.length() + " and perfect square for it is = " + i + " times " + i);

        // make char array long as perfect square
        char[] charArray = new char[i * i];

        // put string in char array, fill string end with spaces
        // so it will be easier to but string into spiral array later on
        // technically in decoding is not required to put spaces, since its guaranteed perfect square
        // REVERSE
        for (int j = 0; j < (i * i); j++) {
            if (j < s.length()) {
                charArray[j] = s.charAt(s.length() - j - 1);
            } else {
                // if reached the end of string, start adding spaces
                charArray[j] = ' ';
            }
        }
        System.out.println(Arrays.toString(charArray));

        // i is number of row and columns
        char square[][] = new char[i][i];


        // put char string in 2d array in reverse mode
        int counter = 0;
        for (int m = 0; m < i; m++) {
            for (int n = 0; n < i; n++) {
                square[i - 1 - m][i - 1 - n] = charArray[counter];
                counter++;
            }
        }

        //now use same encode algorithm to read/decode
        //instead of putting letters to place, we take them
        // set initial values
        i--;
        int circle = i;
        int tracker = 0;
        int character = 0;

        int topY = 0;
        int topX = 0;

        int rightY = 0;
        int rightX = i;

        int downY = i;
        int downX = i;

        int leftY = i;
        int leftX = 0;

        //fill outer cube circle with letters from char array
        String row = "TOP";
        while (character < charArray.length) {

            if (row == "TOP" && character < charArray.length) {
                charArray[character] = square[topY][topX++];
                character++;
                row = "RIGHT";
            }
            if (row == "RIGHT" && character < charArray.length) {
                charArray[character] = square[rightY++][rightX];
                character++;
                row = "DOWN";
            }
            if (row == "DOWN" && character < charArray.length) {
                charArray[character] = square[downY][downX--];
                character++;
                row = "LEFT";
            }
            if (row == "LEFT" && character < charArray.length) {
                charArray[character] = square[leftY--][leftX];
                character++;
                row = "TOP";
            }

            // if outer circle is filled, move to inner circle
            // leftY is always last to be filled when filling clockwise
            if (leftY == (tracker)) {

                topY = 1 + tracker;
                topX = 1 + tracker;

                rightY = 1 + tracker;
                rightX = circle - (1 + tracker);

                downY = circle - (1 + tracker);
                downX = circle - (1 + tracker);

                leftY = circle - (1 + tracker);
                leftX = 1 + tracker;

                tracker++;
            }
        }


        // test print char array contents
        for (int n = 0; n < square.length; n++) {
            for (int m = 0; m < square.length; m++) {
                System.out.print(square[n][m] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //read charArray with decoded message to string
        System.out.println(Arrays.toString(charArray));
        String string = new String (charArray);
        System.out.println("string:" + string);
        return string.trim();
    }
}