package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Map;

public class Lesson3 {

    public static void main(String[] args) {
        //ex # 1 factorial
        System.out.println("Factorial is = " + factorial(5));

        // ex #2 reverseString
        System.out.println("Reverse string is = " + reverseString("Joosep Parts"));

        // ex # 3 isPrime
        isPrime(937);

        // ex # 4 sort
        sort(new int[]{6, 2, 7, 3, 1});

        //ex # 5 even Fibonacci
        System.out.println("Fibonacci = " + evenFibonacci(10));

        //ex # 6 morse
        //System.out.println(morseCode("hello"));
        //System.out.println(morseCode("sos"));
    }

    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
//    int a = 1;
//        for (int i = 1; i <= x; i++) {
//        a = a * i;
//    }
//       return a;
//}
    public static int factorial(int x) {
        int a = 1;
        for (int i = 1; i <= x; i++) {
            a = a * i;
            //!5?
        }
        return a;
    }


    // TODO tagasta string tagurpidi
    public static String reverseString(String a) {
//        StringBuilder reverse = new StringBuilder(a);
//        reverse.reverse();
//        return reverse.toString();

        // read from 0 array element
        int begin = 0;
        // make char array named reverse. Put string a into array as individual char elements
        // a is object.
        char[] reverse = a.toCharArray();
        // end is array len -1
        int end = reverse.length - 1;
        // 0 1 2 3 4 5 6 7 8 9 10 11
        // J o o s e p   P a r t  s
        // temp J
        // temp will store only single char type value
        char temp;
        //11   //0
        while (end > begin) {
            // now J from array element 0 be in temp
            temp = reverse[begin];
            // now s from array element 11 go into element 0
            reverse[begin] = reverse[end];
            // now J from temp, go into element 11 and delete s
            reverse[end] = temp;
            //next read wil be same but for elemenets 1 and 10.
            end--;
            begin++;
        }
        return new String(reverse);
    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {


        if ((x == 0) || (x == 1)) {
            return false;
        } else {
            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    return false;
                }
            }
        }
        System.out.println("isPrime = " + x);
        return true;
    }

    // TODO sorteeri massiiv suuruse järgi.
    // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {
        //6, 2, 7, 3, 1
        //temp
        //2, 6, 7, 3, 1
        //   temp
        //2, 6, 7, 3, 1
        //      temp
        //2, 6, 3, 7, 1
        //         temp
        //2, 6, 3, 1, 7
        //            temp

        // how many elements in array?
        int array = a.length-1;
        // sort array descending
        for (int j = 0; j < array; j++) {
            for (int i = 0; i < array; i++) {
                if (a[i] < a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                }
            }
        }
        return a;
    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        if (x == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= x; i++) {
            int temp = a + b;
            a = b;
            b = temp;
            System.out.println("Fibonacci from " + i + ", was = " + temp);
            if (b % 2 == 0) {
                //sum = Math.max(b+b);
                if (b < x) {
                    sum = sum + b;
                }
            }
        }
        System.out.println("Sum of even fib was = " + sum);
        return sum;
    }

    // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
    // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga
    public static String morseCode(String text) {

        Map<Character, String> morse = new HashMap<>();

        morse.put('h', "....");
        morse.put('e', ".");
        morse.put('l', ".-..");
        morse.put('o', "---");
        morse.put('s', "...");

        // h e l l o
        // 0 1 2 3 4
        char[] array = text.toCharArray();
        String test = "";

        int begin = 0;
        int end = array.length;
        while (end > begin) {
            System.out.print(morse.get(array[begin]) + " ");
            test = test + morse.get(array[begin]) + " ";
            begin++;
        }

        return test.trim();
    }



}
