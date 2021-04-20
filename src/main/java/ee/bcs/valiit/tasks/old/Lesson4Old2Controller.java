package ee.bcs.valiit.tasks.old;

import ee.bcs.valiit.tasks.Lesson4Old2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class Lesson4Old2Controller {

    // LESSON 4OLD2

    //CREATE
    // http://localhost:8080/tasks/Lesson4Old2Old2/createAccount?accountName=Joosep&money=100
    @GetMapping("/tasks/Lesson4Old2/createAccount")
    public String createAccount(@RequestParam("accountName") String accountName, @RequestParam("money") double money) {
        return (Lesson4Old2.createAccount(accountName,money));
    }
    //BALANCE
    // http://localhost:8080/tasks/Lesson4Old2/getBalance?accountName=Joosep
    @GetMapping("/tasks/Lesson4Old2/getBalance")
    public String getBalance(@RequestParam("accountName") String accountName) {
        return (Lesson4Old2.getBalance(accountName));
    }
    //DEPOSIT
    // http://localhost:8080/tasks/Lesson4Old2/depositMoney?accountName=Joosep&money=100
    @GetMapping("/tasks/Lesson4Old2/depositMoney")
    public String depositMoney(@RequestParam("accountName") String accountName, @RequestParam("money") double money) {
        return (Lesson4Old2.depositMoney(accountName, money));
    }
    //WITHDRAW
    // http://localhost:8080/tasks/Lesson4Old2/withdrawMoney?accountName=Joosep&money=100
    @GetMapping("/tasks/Lesson4Old2/withdrawMoney")
    public String withdrawMoney(@RequestParam("accountName") String accountName, @RequestParam("money") double money) {
        return (Lesson4Old2.withdrawMoney(accountName, money));
    }
    //TRANSFER
    // http://localhost:8080/tasks/Lesson4Old2/transfer?fromAccount=Joosep&toAccount=Sandra&money=100
    @GetMapping("/tasks/Lesson4Old2/transfer")
    public String transfer(@RequestParam("fromAccount") String fromAccount,@RequestParam("toAccount") String toAccount,@RequestParam("money") double money) {
        return (Lesson4Old2.transfer(fromAccount, toAccount, money));
    }
    //ALL ASSETS
    // http://localhost:8080/tasks/Lesson4Old2/allBankAssets
    @GetMapping("/tasks/Lesson4Old2/allBankAssets")
    public Set<Map.Entry<String, Double>> allBankAssets() {
        return (Lesson4Old2.allBankAssets());
    }

}
