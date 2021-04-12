package ee.bcs.valiit.tasks;
//test

public class Lesson0 {
    public static void main(String[] args) {
        excersie1();
        excersie2();
        excersie3();
        excersie4();
    }

    // TODO
    // defineeri 3 muutujat a = 1, b = 1, c = 3
    // Prindi välja a==b
    // Prindi välja a==c
    // Lisa rida a = c
    // Prindi välja a==b
    // Prindi välja a==c, mis muutus???
    public static void excersie1(){
        // defineeri 3 muutujat a = 1, b = 1, c = 3
        int a = 1;
        int b = 1;
        int c = 3;
        // Prindi välja a==b
        System.out.println(a==b);
        // Prindi välja a==c
        System.out.println(a==c);
        // Lisa rida a = c
        a = c;
        // Prindi välja a==b
        System.out.println(a==b);
        // Prindi välja a==c, mis muutus???
        // a = c muutis int 1 to int 3 seega 3 on 3
        System.out.println(a==c);
    }

    // TODO
    // Loo muutujad x1 = 10 ja x2 = 20, vali sobiv andmetüüp
    // Tekita muutuja y1 = ++x1, trüki välja nii x1 kui y1
    // Tekita muutuja y2 = x2++, trüki välja nii x2 ja y2
    // Analüüsi tulemusi
    public static void excersie2(){
        // Loo muutujad x1 = 10 ja x2 = 20, vali sobiv andmetüüp
        int x1 = 10;
        int x2 = 20;
        // Tekita muutuja y1 = ++x1, trüki välja nii x1 kui y1
        int y1 = ++x1;
        System.out.println(x1 + " " + y1);
        // Tekita muutuja y2 = x2++, trüki välja nii x2 ja y2
        int y2 = x2++;
        System.out.println(x2 + " " + y2);
        // Analüüsi tulemusi
        // ++ suurendab väärtust ühe võrra. ++ees suurendab ka eelmist,
        // taga++ suurendab ainult praegust

    }

    // TODO
    // Loo arvulised muutujad
    // a = 18 % 3
    // b = 19 % 3
    // c = 20 % 3
    // d = 21 % 3
    // Prindi välja kõigi muutujate väärtused
    public static void excersie3() {
        // Loo arvulised muutujad
        // a = 18 % 3
        int a = 18 % 3;
        // b = 19 % 3
        int b = 19 % 3;
        // c = 20 % 3
        int c = 20 % 3;
        // d = 21 % 3
        int d = 21 % 3;
        // Prindi välja kõigi muutujate väärtused
        System.out.println(a + " " + b + " " + c +" " +d);

    }

    // TODO
    // Defineeri String tüüpi muutuja mille sisu oleks "\"\\""
    // Trüki muutuja sisu välja
    public static void excersie4(){
        String a = "\\";
        String b = "\"";
       // char doubleQuote = 'u0022';
        // char slash = '\u005c';
        // char cast?
        System.out.println('\u0022');
        System.out.println(a+b+a+b+b+a+a);
    }
}
