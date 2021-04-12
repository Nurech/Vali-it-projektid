package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2b {
    public static void main(String[] args) {

        // #4
        System.out.println("\r Array to String = " + Arrays.toString(decreasingArray(-5)));
    }

    // TODO trüki välja n arvu
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static int exercise1(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }

        return n;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {

        int[] array = new int[Math.abs(n)+1];
        // create new variable to store count
        int index = 0;
        //loop while n is not 0
        while (n != 0) {
            array[index] = n;
            //if pos
            if (n > 0) {
                n--;
            } else {
                n++;
            }
            System.out.print(array[index] + " ");
            index++;
        }
        return array;
    }

    // TODO
    // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
    public static int[] yl3(int n) {
        int[] array = new int[n];
        for (int i = 0; i<n;i++) {
            array[i] = 3;
        }
        return array;
    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public int[] generateArray() {
        int[] array = new int[5];
        for (int i = 0; i < 5; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public int[] sampleArray() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        return array;
    }
}
