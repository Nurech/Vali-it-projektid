package ee.bcs.valiit.tasks;

import java.util.Random;

public class Lesson3Hard {


    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks

    final static Random random = new Random();
    final static int randomNumber = random.nextInt(100);

    public static String guessNumber(int myNumber) {
        while (myNumber != randomNumber) {
            if (myNumber < randomNumber) {
                return "Try bigger " +randomNumber;
            } else {
                return "Try smaller " +randomNumber;
            }
        }
        return "Great, you got it! it was " + randomNumber;
    }
}


//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        Random random = new Random();
//        int i = random.nextInt(100);
//        // System.out.println(i);
//        // ask first number, if wrong go into while loop
//        System.out.print("Think of a number = ");
//        int number = scanner.nextInt();
//        int j = 1;
//        // ask a number until it's guessed
//        while (number != i) {
//            System.out.print("Sorry, wrong nr. ");
//            if (number < i) {
//                System.out.println("Try bigger");
//            } else {
//                System.out.println("Try smaller");
//            }
//            System.out.print("Try again = ");
//            number = scanner.nextInt();
//            j++;
//            // give a hint after 10 tries
//            if (j == 10) {
//                System.out.println("A hint... it starts with " + i / 10);
//            }
//            // give a hint after 10 tries
//            if ((i < 10) && (j == 10)) {
//                System.out.println("A hint... it's one digit number.");
//            }
//        }
//        System.out.println("Great job, you got it!");
//    }
//}