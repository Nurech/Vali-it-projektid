package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@RestController
public class Controller {

    // PostMapping
    // PutMapping
    // DeleteMapping
    @GetMapping("sample/hello-world/{nameInUrl}")
    public String helloWorld(@PathVariable("nameInUrl") String name,
                             @RequestParam("action") String action) {
        return action + " " + name;
    }

    // LESSON 1

    //http://localhost:8080/tasks/Lesson1/min/3/2
    @GetMapping("/tasks/Lesson1/min/{a}/{b}")
    public int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson1.min(a, b);
    }

    //http://localhost:8080/tasks/Lesson1/max/3/2
    @GetMapping("/tasks/Lesson1/max/{a}/{b}")
    public int max(@RequestParam("a") int a, @PathVariable("b") int b) {
        return Lesson1.max(a, b);
    }

    //http://localhost:8080/tasks/Lesson1/abs/3/2
    @GetMapping("/tasks/Lesson1/abs/{a}/{b}")
    public int abs(@PathVariable("a") int a) {
        return Lesson1.abs(a);
    }

    //http://localhost:8080/tasks/Lesson1/isEven/3/2
    @GetMapping("/tasks/Lesson1/isEven/{a}/{b}")
    public boolean isEven(@PathVariable("a") int a) {
        return Lesson1.isEven(a);
    }

    //http://localhost:8080/tasks/Lesson1/max/3/2/5
    @GetMapping("/tasks/Lesson1/max/{a}/{b}/{c}")
    public int max3(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        return Lesson1.max3(a, b, c);
    }

    // LESSON 2

    //http://localhost:8080/tasks/Lesson2/fibonacci/10
    @GetMapping("/tasks/Lesson2/fibonacci/{n}")
    public int fibonacci(@PathVariable("n") int n) {
        return Lesson2.fibonacci(n);
    }

    //http://localhost:8080/tasks/Lesson2/sequence3n/10/20
    @GetMapping("/tasks/Lesson2/sequence3n/{x}/{y}")
    public int sequence3n(@PathVariable("x") int x, @PathVariable("y") int y) {
        return Lesson2.sequence3n(x, y);
    }

    //http://localhost:8080/tasks/Lesson2/getSqnLength/10/20
    @GetMapping("/tasks/Lesson2/getSqnLength/{x}")
    public int getSqnLength(@PathVariable("x") int x) {
        return Lesson2.getSqnLength(x);
    }

    // http://localhost:8080/tasks/Lesson2/multiplyTable/5/5
    @GetMapping("/tasks/Lesson2/multiplyTable/{x}/{y}")
    public int[][] multiplyTable(@PathVariable("x") int x, @PathVariable("y") int y) {
        return Lesson2.multiplyTable(x, y);
    }

    // LESSON 3

    // http://localhost:8080/tasks/Lesson3/factorial/10
    @GetMapping("/tasks/Lesson3/factorial/{x}")
    public int factorial(@PathVariable("x") int x) {
        return Lesson3.factorial(x);
    }

    // http://localhost:8080/tasks/Lesson3/reverseString/JoosepParts
    @GetMapping("/tasks/Lesson3/reverseString/{x}")
    public String reverseString(@PathVariable("x") String x) {
        return Lesson3.reverseString(x);
    }

    // http://localhost:8080/tasks/Lesson3/isPrime/10
    @GetMapping("/tasks/Lesson3/isPrime/{x}")
    public boolean isPrime(@PathVariable("x") int x) {
        return Lesson3.isPrime(x);
    }

    // http://localhost:8080/tasks/Lesson3/sort/2,3,4,5,6
    @GetMapping("/tasks/Lesson3/sort/{a}")
    public String sort(@PathVariable("a") int[] a) {
        return Arrays.toString(Lesson3.sort(a));
    }

    //http://localhost:8080/tasks/Lesson3/evenFibonacci?x=100
    @GetMapping("/tasks/Lesson3/evenFibonacci")
    public int max(@RequestParam("x") int x) {
        return Lesson3.evenFibonacci(x);
    }

    // http://localhost:8080/tasks/Lesson3/morseCode/hello
    @GetMapping("/tasks/Lesson3/morseCode/{text}")
    public String sort(@PathVariable("text") String text) {
        return (Lesson3.morseCode(text));
    }


    // LESSON 3 HARD

    // http://localhost:8080/tasks/Lesson3Hard/guessNumber/10
    @GetMapping("/tasks/Lesson3Hard/guessNumber/{myNumber}")
    public String guessNumber(@PathVariable("myNumber") int myNumber) {
        return (Lesson3Hard.guessNumber(myNumber));
    }

    // LESSON 4

    // http://localhost:8080/tasks/Lesson4/createAccount?accountName=Joosep&money=100
    @GetMapping("/tasks/Lesson4/createAccount")
    public String createAccount(@RequestParam("accountName") String accountName, @RequestParam("money") double money) {
        return (Lesson4.createAccount(accountName,money));
    }
    // http://localhost:8080/tasks/Lesson4/getBalance?accountName=Joosep
    @GetMapping("/tasks/Lesson4/getBalance")
    public String getBalance(@RequestParam("accountName") String accountName) {
        return (Lesson4.getBalance(accountName));
    }


    // http://localhost:8080/tasks/Lesson4/allBankAssets
    @GetMapping("/tasks/Lesson4/allBankAssets")
    public Set<Map.Entry<String, Double>> allBankAssets() {
        return (Lesson4.allBankAssets());
    }
}
