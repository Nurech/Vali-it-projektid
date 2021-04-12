package ee.bcs.valiit.tasks;

import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {
    public static void main(String[] args) throws Exception {
        // Siia sisse võid sa kirjutada koodi, et testida kas su meetodid töötavad ilusti
        // Katseta IntelliJ shortcuti. Olles intelliJ kirjuta "sout" ja siis vajuta enter
        // System.System.out.println(min(1, 3)); // trükib miinimumi 1 ja 3

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (exit == false) {
            System.out.print("What function you want to use?" + "\n" +
                    "Type \"exit\" to exit" +
                    "1 - min" + "\n" +
                    "2 - max" + "\n" +
                    "3 - abs" + "\n" +
                    "4 - isEven" + "\n" +
                    "5 - min3" + "\n" +
                    "6 - max3" + "\n" +
                    "enter option: ");
            String option = scanner.next();
            if (option.equals("exit")) {
                exit = true;
                System.out.println("Closing program...");
        }

        switch (option) {
            case "1": {
                System.out.print("Enter min a: ");
                int a = scanner.nextInt();
                System.out.print("Enter min b: ");
                int b = scanner.nextInt();
                System.out.println(min(a, b));
                break;
            }
            case "2": {
                System.out.print("Enter max a: ");
                int a = scanner.nextInt();
                System.out.print("Enter max b: ");
                int b = scanner.nextInt();
                System.out.println(max(a, b));
                break;
            }
            case "3": {
                System.out.print("Enter abs: ");
                int a = scanner.nextInt();
                System.out.println(abs(a));
                break;
            }
            case "4": {
                System.out.print("Enter isEven: ");
                int a = scanner.nextInt();
                System.out.println(isEven(a));
                break;
            }
            case "5": {
                System.out.print("Enter min3 a: ");
                int a = scanner.nextInt();
                System.out.print("Enter min3 b: ");
                int b = scanner.nextInt();
                System.out.print("Enter min3 c: ");
                int c = scanner.nextInt();
                System.out.println(min3(a, b, c));
                break;
            }
            case "6": {
                System.out.print("Enter max3 a: ");
                int a = scanner.nextInt();
                System.out.print("Enter max3 b: ");
                int b = scanner.nextInt();
                System.out.print("Enter max3 c: ");
                int c = scanner.nextInt();
                System.out.println(max3(a, b, c));
                break;
            }
            default:
                //System.out.println("Please enter valid value. " + option + " is not an option");;
                throw new Exception("Please enter valid value. " + option + " is not an option");
        }
    }

}

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
        if (a <= 0) {
           a = -a;
        } return a;
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        if (a % 2 == 0) {
            return true;
        }
        return false;
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        if ((a <= b) && (a <= c)) {
            return a;
        } else if (b < c) {
            return b;
        }
        return c;
    }

    //   public static int max3(int a, int b, int c) {
    //        return max(max(a, b), c);
    //    }
    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        if ((a >= b) && (a >= c)) {
            return a;
        } else if ((b >= c) && (b >= a)) {
            return b;
        }
        return c;
    }
}