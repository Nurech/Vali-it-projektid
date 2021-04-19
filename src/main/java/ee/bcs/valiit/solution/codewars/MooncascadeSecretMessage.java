package ee.bcs.valiit.solution.codewars;

public class MooncascadeSecretMessage {

    public static void main(String[] args) {
        // https://mooncascade.com/career
        // msg[] = { 125, 66, 69, 10, 67, 89, 10, 94, 66, 79, 10, 75, 95, 94, 66, 69, 88, 10, 69, 76, 10, 8, 126, 66, 79, 10, 98, 67, 94, 73, 66, 66, 67, 65, 79, 88, 13, 89, 10, 109, 95, 67, 78, 79, 10, 94, 69, 10, 94, 66, 79, 10, 109, 75, 70, 75, 82, 83, 8, 21, 10, 122, 70, 79, 75, 89, 79, 10, 89, 79, 68, 78, 10, 83, 69, 95, 88, 10, 75, 68, 89, 93, 79, 88, 10, 94, 69, 10, 64, 69, 72, 89, 106, 71, 69, 69, 68, 73, 75, 89, 73, 75, 78, 79, 4, 73, 69, 71, 6, 10, 93, 67, 94, 66, 10, 83, 69, 95, 88, 10, 105, 124, 10, 69, 88, 10, 102, 67, 68, 65, 79, 78, 99, 68, 10, 127, 120, 102, 4 };
        char [] msg = { 125, 66, 69, 10, 67, 89, 10, 94, 66, 79, 10, 75, 95, 94, 66, 69, 88, 10, 69, 76, 10, 8, 126, 66, 79, 10, 98, 67, 94, 73, 66, 66, 67, 65, 79, 88, 13, 89, 10, 109, 95, 67, 78, 79, 10, 94, 69, 10, 94, 66, 79, 10, 109, 75, 70, 75, 82, 83, 8, 21, 10, 122, 70, 79, 75, 89, 79, 10, 89, 79, 68, 78, 10, 83, 69, 95, 88, 10, 75, 68, 89, 93, 79, 88, 10, 94, 69, 10, 64, 69, 72, 89, 106, 71, 69, 69, 68, 73, 75, 89, 73, 75, 78, 79, 4, 73, 69, 71, 6, 10, 93, 67, 94, 66, 10, 83, 69, 95, 88, 10, 105, 124, 10, 69, 88, 10, 102, 67, 68, 65, 79, 78, 99, 68, 10, 127, 120, 102, 4 };
        for (char c : msg){
            System.out.print(c + " ");
        }

/*      } B E
        C Y
        ^ B O
        K _ ^ B E X
        E L
        ~ B O
        Y
        m _ C N O
        ^ E
        ^ B O
        m K F K R S 
        z F O K Y O
        Y O D N
        S E _ X
        K D Y ] O X
        ^ E
        @ E H Y j G E E D I K Y I K N O  I E G 
        ] C ^ B
        S E _ X
        i |
        E X
        f C D A O N c D
         x f */

        // ok, looks like a hidden message
        // 10 appears to be new line because for loop printed new lines
        // ASCII Line Feed is 10 https://theasciicode.com.ar/
        // printable ASCII codes are character code 32-127

        //TODO
        // 1. separate words
        // 2. generate all possibilities (3 letter word from 95 ASCII char has = 778596 permutations
        // 3. compare permutation for a 3 letter word for all words in oxford dictionary
        // 4. if it is english word save result in array for possibilities
        // 5. make sentences from all permutations, present for human review

}

}
