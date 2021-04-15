package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.LinkedList;

public class KataArray5 {
    public static void main(String[] args) {
        deleteNth(new int[]{1, 2, 3, 1, 1, 2, 1, 2, 3, 3, 2, 4, 5, 3, 1}, 3);
    }

    public static int[] deleteNth(int[] elements, int maxOccurrences) {

        // to store occurrence
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        // put solution answer
        LinkedList<Integer> newElements = new LinkedList<Integer>();

        // go though all numbers one by one
        for (int j = 0; j < elements.length; j++) {
            //take initial occurrence for current number
            Integer occurrence = hashmap.get(elements[j]);
            // if empty then fill with zero, else it will crash
            if (occurrence == null) {
                occurrence = 0;
            }
            //increase number by one occurrence and add to hashmap
            occurrence++;
            hashmap.put(elements[j], occurrence);

            // if is less than add to list
            if (occurrence <= maxOccurrences) {
                hashmap.get(elements[j]);
                newElements.add(elements[j]);
            }
        }

        // linkedlist to array
        int[] array = new int[newElements.size()];
        for (int k = 0; k < array.length; k++) {
            array[k] = newElements.get(k);
        }

        // test print array
        // System.out.println(Arrays.toString(array));
        return array;
    }
}

