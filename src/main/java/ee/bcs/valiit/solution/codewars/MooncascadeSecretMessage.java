package ee.bcs.valiit.solution.codewars;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class MooncascadeSecretMessage {

    public static void main(String[] args) {
//        //https://mooncascade.com/career
//        char[] msg = {125, 66, 69, 10, 67, 89, 10, 94, 66, 79, 10, 75, 95, 94, 66, 69, 88, 10, 69, 76, 10, 8, 126, 66, 79, 10, 98, 67, 94, 73, 66, 66, 67, 65, 79, 88, 13, 89, 10, 109, 95, 67, 78, 79, 10, 94, 69, 10, 94, 66, 79, 10, 109, 75, 70, 75, 82, 83, 8, 21, 10, 122, 70, 79, 75, 89, 79, 10, 89, 79, 68, 78, 10, 83, 69, 95, 88, 10, 75, 68, 89, 93, 79, 88, 10, 94, 69, 10, 64, 69, 72, 89, 106, 71, 69, 69, 68, 73, 75, 89, 73, 75, 78, 79, 4, 73, 69, 71, 6, 10, 93, 67, 94, 66, 10, 83, 69, 95, 88, 10, 105, 124, 10, 69, 88, 10, 102, 67, 68, 65, 79, 78, 99, 68, 10, 127, 120, 102, 4};
//        for (char c : msg) {
//            System.out.print(c + " ");

        boolean answer = false;
        while (!answer) {

            // swap key values randomly from lowercase assortment
            // a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
            char[] options = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            char[] result =  new char[26];

            char randomChar125 = 'a';
            Random r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar125 = result[i];
            }

            char randomChar66 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar66 = result[i];
            }
            char randomChar69 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar69 = result[i];
            }
            char randomChar67 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar67 = result[i];
            }
            char randomChar89 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar89 = result[i];
            }
            char randomChar94 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar94 = result[i];
            }
            char randomChar79 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar79 = result[i];
            }
            char randomChar75 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar75 = result[i];
            }
            char randomChar95 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar95 = result[i];
            }
            char randomChar88 = 'a';
            r=new Random();
            for(int i=0;i<result.length;i++){
                result[i]=options[r.nextInt(options.length)];
                randomChar88 = result[i];
            }


            char [] word1 = {randomChar125, randomChar66, randomChar69};
            char [] word2 = {randomChar67, randomChar89};
            char [] word3 = {randomChar94, randomChar66, randomChar79};
            char [] word4 = {randomChar75, randomChar95, randomChar94, randomChar66, randomChar69, randomChar88};

            String str1 = String.valueOf(word1);
            String str2 = String.valueOf(word2);
            String str3 = String.valueOf(word3);
            String str4 = String.valueOf(word4);

//            System.out.println(str1);
//            System.out.println(str2);
//            System.out.println(str3);
//            System.out.println(str4);

            String[] stringArray = {str1,str2,str3,str4};

            // loop find 4 words
            int wordMatcher = 0;
            for (int i = 0; i < 4; i++) {
                double countBuffer = 0;
                double countLine = 0;
                String lineNumber = "";
                String filePath = "C:\\Users\\Lenovo\\IdeaProjects\\vali-it\\src\\main\\java\\ee\\bcs\\valiit\\solution\\codewars\\words.txt";
                BufferedReader br;
                String inputSearch = stringArray[i];
                String line = "";
                try {
                    br = new BufferedReader(new FileReader(filePath));
                    try {
                        while ((line = br.readLine()) != null) {
                            countLine++;
                            //System.out.println(line);
                            String[] words = line.split(" ");
                            for (String word : words) {
                                if (word.equals(inputSearch)) {
                                    countBuffer++;
                                    wordMatcher += 1;
                                }
                            }
                            if (countBuffer > 0) {
                                countBuffer = 0;
                                lineNumber += countLine + ",";
                            }
                        }
                        br.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if (wordMatcher == 3) {
                    System.out.println(str1 + " " + str2 + " " + str3 + " " + str4);
                    wordMatcher = 0;
//                    answer = true;
                }

            }
        }
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
    // 10 is new line because for loop printed new lines
    // ASCII Line Feed is 10 https://theasciicode.com.ar/
    // printable ASCII codes are character code 32-127
    // assume sinlge row is word

    // make words into arrays
    // permute input for all possibilities from ASCII table
    // compare sample size of 4 first words for oxford english dictionary
    // if all 4 words are english save and present it for human review

    // 125, 66, 69,
    // 67, 89,
    // 94, 66, 79,
    // 75, 95, 94, 66, 69, 88,



}

//

//
//        char [] arr1 = {'}', 'B', 'E'};
//        char [] arr2 = {'C', 'Y'};
//        char [] arr3 = {'^', 'B', 'O'};
//        char [] arr4 = {'K', '_', '^', 'B', 'E', 'X'};
//


//        int counter = 0
//        for (int i = 0; i < msg.length;i++){
//            if (msg[i] == '\n'){
//                for (int j = counter; j < i; j++){
//                }
//            }
//        }

        /*DEC	Description
        32	Space
        33	Exclamation mark
        34	Double quotes (or speech marks)
        35	Number
        36	Dollar
        37	Per cent sign
        38	Ampersand
        39	Single quote
        40	Open parenthesis (or open bracket)
        41	Close parenthesis (or close bracket)
        42	Asterisk
        43	Plus
        44	Comma
        45	Hyphen
        46	Period, dot or full stop
        47	Slash or divide
        48	Zero
        49	One
        50	Two
        51	Three
        52	Four
        53	Five
        54	Six
        55	Seven
        56	Eight
        57	Nine
        58	Colon
        59	Semicolon
        60	Less than (or open angled bracket)
        61	Equals
        62	Greater than (or close angled bracket)
        63	Question mark
        64	At symbol
        65	Uppercase A
        66	Uppercase B
        67	Uppercase C
        68	Uppercase D
        69	Uppercase E
        70	Uppercase F
        71	Uppercase G
        72	Uppercase H
        73	Uppercase I
        74	Uppercase J
        75	Uppercase K
        76	Uppercase L
        77	Uppercase M
        78	Uppercase N
        79	Uppercase O
        80	Uppercase P
        81	Uppercase Q
        82	Uppercase R
        83	Uppercase S
        84	Uppercase T
        85	Uppercase U
        86	Uppercase V
        87	Uppercase W
        88	Uppercase X
        89	Uppercase Y
        90	Uppercase Z
        91	Opening bracket
        92	Backslash
        93	Closing bracket
        94	Caret - circumflex
        95	Underscore
        96	Grave accent
        97	Lowercase a
        98	Lowercase b
        99	Lowercase c
        100	Lowercase d
        101	Lowercase e
        102	Lowercase f
        103	Lowercase g
        104	Lowercase h
        105	Lowercase i
        106	Lowercase j
        107	Lowercase k
        108	Lowercase l
        109	Lowercase m
        110	Lowercase n
        111	Lowercase o
        112	Lowercase p
        113	Lowercase q
        114	Lowercase r
        115	Lowercase s
        116	Lowercase t
        117	Lowercase u
        118	Lowercase v
        119	Lowercase w
        120	Lowercase x
        121	Lowercase y
        122	Lowercase z
        123	Opening brace
        124	Vertical bar
        125	Closing brace
        126	Equivalency sign - tilde
        127	Delete*/


