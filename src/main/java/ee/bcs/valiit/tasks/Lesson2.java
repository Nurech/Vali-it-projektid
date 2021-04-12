package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2 {

    // send, ask back backwards
    public static void main(String[] args) {
        // ex #1 reverseArray
        reverseArray(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(reverseArray(new int[]{1, 2, 3, 4})));

        // ex #2 evenNumber
        System.out.println(Arrays.toString(evenNumbers(5)));

        //ex #3 minArrayNr
        System.out.println(min(new int[]{2, 4, 5, -2, -1}));

        //ex #4 maxArrayNr
        System.out.println(max(new int[]{2, 4, 5, -2, -1}));

        //ex #5 sumArrayNr
        System.out.println(sum(new int[]{2, 4, 5, -2, -1}));

        //ex #6 multiplyArray
        System.out.println(multiplyTable(5, 5));

        //ex #7 fibonacci
        System.out.println(fibonacci(15));

        //ex #8 sequence3
        System.out.println(sequence3n(1, 10));

    }

    // TODO loe funktsiooni sisendiks on täisarvude massiiv
    // TODO tagasta massiiv mille elemendid on vastupidises järiekorras
    // ex #1 reverseArray
    // main { reverseArray(new int[]{1, 2, 3, 4});
    // System.out.println(Arrays.toString(reverseArray(new int[]{1, 2, 3, 4}))); }
    // 1  2  3  4
    // E0 E1 E2 E3
    public static int[] reverseArray(int[] inputArray) {
        // how many elements in array?
        int maxArray = inputArray.length - 1;
        // split elements 4/2=2
        int halfArray = inputArray.length / 2;
        // loop 2 times till [i]2 = 2
        for (int i = 0; i < halfArray; i++) {
            // temp is first number
            int temp = inputArray[i];
            // first number is last number (and first number)
            inputArray[i] = inputArray[maxArray - i];
            // last number is taken from temp
            inputArray[maxArray - i] = temp;
        }
        return inputArray;
    }

    // TODO tagasta massiiv mis sisaldab n esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static int[] evenNumbers(int n) {
        // make new array with n elements
        int[] newArray = new int[n];
        // loop n*2+2 times (5*2+2=12 times to loop to get all evens)
        for (int i = 1; i < (n * 2 + 2); i++) {
            // if modulus is 0 is even
            if (i % 2 == 0) {
                // add int i to array position [(i/2)-1]
                // add i int to array [i] position
                newArray[(i / 2) - 1] = i;
            }
        }
        // Arrays.toString(evenNumbers(n));
        return newArray;
    }

    // TODO, leia massiivi kõige väiksem element
    // int[] input = {2, 4, 5, -2, -1};
    public static int min(int[] x) {
        // first min is array x[] 0 element
        int min = x[0];
        // how many elements in array?
        int elements = x.length;
        // loop x element times
        for (int i = 0; i < elements; i++) {
            // if current int is smaller than last min, then new min is current int
            if (x[i] < min) {
                min = x[i];
            }
        }
        return min;
    }

    // TODO, leia massiivi kõige suurem element
    // int[] input = {2, 4, 5, -2, -1};
    public static int max(int[] x) {
        // first min is array x[] 0 element
        int max = x[0];
        // how many elements in array?
        int elements = x.length;
        // loop x element times
        for (int i = 1; i < elements; i++) {
            // if current int is smaller than last max, then new max is current int
            if (x[i] > max) {
                max = x[i];
            }
        }
        return max;
    }

    // TODO, leia massiivi kõigi elementide summa
    // int[] input = {2, 4, 5, -2, -1};
    public static int sum(int[] x) {
        // first sum is array x[] 0 element
        int sum = x[0];
        // how many elements in array?
        int elements = x.length;
        // loop x element times
        for (int i = 1; i < elements; i++) {
            // new sum is old sum + new array int
            sum = sum + x[i];
        }
        return sum;
    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee

    public static String multiplyTable(int x, int y) {
        // x = rows
        int[] lineArray = new int[x];
        //1 row multiplier = 1, 2 row multiplier will be 2 etc.
        int multiplier = 1;
        // loop 5x5 times
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < lineArray.length; i++) {
                lineArray[i] = (i + 1) * multiplier;
            }
            multiplier++;
            System.out.println(Arrays.toString(lineArray));
        }
        // it needs to return something. Why not "" (nothing)
        return "";
    }

    //    Assertions.assertEquals(0, Lesson2.fibonacci(0));
//        Assertions.assertEquals(1, Lesson2.fibonacci(1));
//        Assertions.assertEquals(1, Lesson2.fibonacci(2));
//        Assertions.assertEquals(610, Lesson2.fibonacci(15));
    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    public static int fibonacci(int n) {
        int a = 0;
        int b = 1;
        int temp = 0;
        // because F1=F2 == 1 anyway
        if (n == 0) {
            return 0;
        }
        if ((n == 1) || (n == 2)) {
            return 1;
        }
        // loop n times
        for (int i = 0; i < n; i++) {
            // temp is sum of previous numbers
            a = b;
            b = temp;
            temp = a + b;
            System.out.println("Fibonacci from " + i + ", was = " + temp);
        }
        System.out.println("Fibonacci from " + n + ", was = " + temp);
        return temp;
    }
//        int fn = n;
//        if (n == 1) {
//            fn = 1;
//        }
//        if (n == 2) {
//            fn = 2;
//        }
//        fn = ((fn * (n - 1)) + (fn * (n - 2)));
//        return fn;


    // TODO
    // Kujutame ette numbrite jada, kus juhul kui number on paaris arv siis me jagame selle 2-ga
    // Kui number on paaritu arv siis me korrutame selle 3-ga ja liidame 1. (3n+1)
    // Seda tegevust teeme me niikaua kuni me saame vastuseks 1
    // Näiteks kui sisend arv on 22, siis kogu jada oleks:
    // 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
    // Sellise jada pikkus on 16
    // Kirjutada programm, mis peab leidma kõige pikema jada pikkuse mis jääb kahe täis arvu vahele
    // Näiteks:
    // Sisend 10 20
    // Siis tuleb vaadata, kui pikk jada tuleb kui esimene numbr on 10. Järgmisen tuleb arvutada number 11 jada pikkus.
    // jne. kuni numbrini 20
    // Tagastada tuleb kõige pikem number
    // Näiteks sisendi 10 ja 20 puhul on vastus 20

    //

    // none of the print is necessary

    public static int sequence3n(int x, int y) {
        System.out.println("*******  START! NEW TEST! *******");
        int temp = 0;
        for (int i = 1; x <= y; x++) {
            //temp = temp + getSqnLength(x);
            temp = Math.max(getSqnLength(x), temp);
        }
        return temp;
    }

    public static int getSqnLength (int x){
        int sqnLen = 1;
        while (x != 1) {
            if (x % 2 == 0){
                System.out.println("Doing = " + x + "/2");
                x = x / 2;
            } else {
                System.out.println("Doing = 3*" + x + "+1");
                x = 3 * (x) + 1;
            }
            sqnLen++;
        }
        System.out.println("END! For sequence 3n = " + sqnLen);
        return sqnLen;
    }
    }


/*    public static int sequence3n(int x, int y) {
        System.out.println("*******  START! NEW TEST! *******");
        // need final initial values for print
        final int initalX = x;
        final int whatXCurrently = x;
        // track how many sequences have done
        int count = 1;
        // track what is highest sequence
        int maxSequence = 1;


        // loop until i reaches y
        for (int i = 1; i <= y; i++) {
            //calculating sequence length
            while (x != 1) {
                // if is even devise
                if (x % 2 == 0) {
                    System.out.print("Doing = " + x + "/2");
                    x = x / 2;
                    // if is odd 3n+1
                } else {
                    System.out.print("Doing = 3*" + x + "+1");
                    x = (3 * x + 1);
                }
                // print results to debug
                System.out.print(", now x = " + x);
                System.out.println(" sequence at = " + count);
                // count how many times looped (how long is sequence)
                count++;
            }
            // next sequence loop will start from one more than previous
            x = initalX + i;
            // after finding sequence length store highest sequence length in maxSequence
            if (maxSequence <= count) {
                maxSequence = count;
            }

            // reset count back to 1 so next sequence length will start from 1 again
            count = 1;
            System.out.println("END!" + "For sequence 3n [x=" + (whatXCurrently + i-1) + "] " + "and [y=" + y + "], " + "current maxSequence is = " + maxSequence);
        }
        return maxSequence;
    }*/

/*
  public static int sequence3n(int x, int y) {

        int count = 0;

        // loop until xLoop is 1. xLoop counts how many times found next number
        for (int i = 0; x >= 1; i++) {
            // if is even then division
            if (x % 2 == 0) {
                System.out.print("Doing = " + x + "/2, now x = ");
                x = x / 2;
                System.out.println(x + " loop = " + i);
                count++;
            }
        }
        for (int j = 0; x >= 1; j++) {
            // if it is not even number then multiply
            if (x % 2 != 0) {
                System.out.print("Doing = 3*" + x + "+1, now x = ");
                x = (3 * x + 1);
                System.out.println(x + " loop = " + j);
                count++;
            }

        }
            System.out.println("END! For sequence 3n [x=" + x + "] and [y=" + y + "], looped = " + count);
            return count;
    }
}*/


/*    public static int sequence3n(int x, int y) {

        int initalX = x;
        int yLoop = 0;
        int xLoop = 0;
        int whileLoopStart = 0;

        while (whileLoopStart != initalX) {
            // loop until xLoop is 1. xLoop counts how many times found next number
            for (xLoop = 0; x >= 1; xLoop++) {

                // if it is not even number then multiply
                if (x % 2 != 0) {
                    System.out.print("Doing = 3*" + x + "+1, now x = ");
                    x = (3 * x + 1);
                    System.out.println(x + " loop = " + xLoop);
                    xLoop++;
                }
                // if is even then division
                if (x % 2 == 0) {
                    System.out.print("Doing = " + x + "/2, now x = ");
                    x = x / 2;
                    System.out.println(x + " loop = " + xLoop);
                }
            }

            System.out.println("END! For sequence 3n [x=" + x + "] and [y=" + y + "], looped = " + xLoop);
            yLoop++;
            return whileLoopStart++;
        }
        System.out.println("END! Looped [x=" + initalX +"] and [y=" + y + "] from = " + yLoop);
        return yLoop;
    }
}*/
