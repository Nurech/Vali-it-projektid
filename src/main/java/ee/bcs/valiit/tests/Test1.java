package ee.bcs.valiit.tests;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(booTest1(3));
        System.out.println(booTest1(7));
        System.out.println(booTest1(21));
        System.out.println(booTest1(4));
    }
//    ÜL 1
//    Tee funktsioon, mis tagastab boolean väärtuse ja võtab sisse ühe parameetri
//    funktsioon peab tagastama
//		true: kui sisend parameeter jagub 3 või 7 (aga ei jagu mõlema numbriga)
//      false: kui sisend parameeter ei jagu 3 ega 7 või jagub mõlema numbriga

    // ÜL2
    // lisa x igale array elemendile
    // Näiteks
    // sisend [1,2,3], 5
    // vastus [6,7,8]


    public static boolean booTest1(int x) {
        if ((x % 3 == 0) && (x % 7 != 0)) {
            return true;
        } else {
            if ((x % 3 != 0) && (x % 7 == 0)) {
                return false;
            }
        }
        return false;
    }

    public static int[] addToArray(int[] something, int x) {
        for (int i = 0; i < something.length; i++) {
            something[i] = something[i] + x;
        }
        return something;
    }

}
