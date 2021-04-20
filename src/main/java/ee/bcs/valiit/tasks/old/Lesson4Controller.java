package ee.bcs.valiit.tasks.old;//package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson4;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class Lesson4Controller {

    private HashMap<String, Double> accountBalanceMap = new HashMap<String, Double>();

    //check if account is present
    public  String checkIfAccountExists(String accountName) {
        if (accountBalanceMap.get(accountName) == null) {
            return "Account not present";
        }
        return "Did your thing";
    }


    //CREATE ACCOUNT
    // http://localhost:8080/tasks/Lesson4/createAccount
    @PostMapping("/tasks/Lesson4/createAccount")
    public String createAccount(@RequestBody Lesson4 createAccountRequest) {
        if (accountBalanceMap.get(checkIfAccountExists(createAccountRequest.getAccountName())) == null) {
            accountBalanceMap.put(createAccountRequest.getAccountName(), createAccountRequest.getMoney());
            return "Account added = " + createAccountRequest.getAccountName() + ", with balance of = " + createAccountRequest.getMoney();
        } else {
            return "Account already present";
        }
    }

    //DEPOSIT MONEY
    // http://localhost:8080/tasks/Lesson4/depositMoney
    @PostMapping("/tasks/Lesson4/depositMoney")
    public String depositMoney(@RequestBody Lesson4 depositRequest) {
        if (accountBalanceMap.get(checkIfAccountExists(depositRequest.getAccountName())) == null) {
            if (depositRequest.getMoney() > 0) {
                double currentBalance = accountBalanceMap.get(depositRequest.getAccountName());
                accountBalanceMap.put(depositRequest.getAccountName(), depositRequest.getMoney() + currentBalance);
                return "Added " + depositRequest.getMoney() + " to " + depositRequest.getAccountName() + ", new balance is " + accountBalanceMap.get(depositRequest.getAccountName());
            }
            return "Enter a positive value.";
        }
        return "Can't add to an account which doesn't exist.";
    }

    //GET ALL
    //http://localhost:8080/tasks/Lesson4
    @GetMapping("/tasks/Lesson4/")
    public Set<Map.Entry<String, Double>> getAll(){
        return accountBalanceMap.entrySet();
    }

    //BALANCE REQUEST
    //http://localhost:8080/tasks/Lesson4/getBalance
    @GetMapping("/tasks/Lesson4/getBalance")
    public String getBalance(@RequestBody Lesson4 getBalanceRequest) {
        if (accountBalanceMap.get(checkIfAccountExists(getBalanceRequest.getAccountName())) == null) {
            return "Account = " + accountBalanceMap.get(getBalanceRequest.getAccountName());
        }
        return "No account";
    }

    //WITHDRAW REQUEST
    //http://localhost:8080/tasks/Lesson4/withdrawMoney
    @PostMapping("/tasks/Lesson4/withdrawMoney")
    public String withdrawMoney(@RequestBody Lesson4 withdrawMoneyRequest) {
        if (accountBalanceMap.get(checkIfAccountExists(withdrawMoneyRequest.getAccountName())) == null) {
            if (withdrawMoneyRequest.getMoney() <= (accountBalanceMap.get(withdrawMoneyRequest.getAccountName()))) {
                double currentBalance = accountBalanceMap.get(withdrawMoneyRequest.getAccountName());
                accountBalanceMap.put(withdrawMoneyRequest.getAccountName(), currentBalance - withdrawMoneyRequest.getMoney());
                return "Old balance was = " + currentBalance + " withdrew " + withdrawMoneyRequest.getMoney() + " new balance is " + accountBalanceMap.get(withdrawMoneyRequest.getAccountName());
            } else {
                return "You can't withdraw that much. You have: " + accountBalanceMap.get(withdrawMoneyRequest.getAccountName());
            }
        }
        return "Can't withdraw from an account which doesn't exist.";
    }


    //TRANSFER REQUEST
    //http://localhost:8080/tasks/Lesson4/transferMoney
    @PostMapping("/tasks/Lesson4/transferMoney")
    public String transferMoney(@RequestBody Lesson4 transferMoneyRequest) {

        if ((accountBalanceMap.get(transferMoneyRequest.getFromAccount()) >= transferMoneyRequest.getMoney())) {
            // take current balance
            double account2Balance = accountBalanceMap.get(transferMoneyRequest.getFromAccount());
            double balance = accountBalanceMap.get(transferMoneyRequest.getAccountName());
            // update balance
            double deductedMoney = account2Balance - transferMoneyRequest.getMoney();
            double addedMoney = balance + transferMoneyRequest.getMoney();
            // put new balances back for both
            accountBalanceMap.put(transferMoneyRequest.getFromAccount(), addedMoney);
            accountBalanceMap.put(transferMoneyRequest.getAccountName(), deductedMoney);
            return "Transferred from " + transferMoneyRequest.getFromAccount() + " to " + transferMoneyRequest.getAccountName() + ", " + transferMoneyRequest.getMoney() + "\n" +
                    transferMoneyRequest.getFromAccount() + " is " + accountBalanceMap.get(transferMoneyRequest.getFromAccount()) + "\n" +
                    transferMoneyRequest.getAccountName() + " is " + accountBalanceMap.get(transferMoneyRequest.getAccountName());
        } else {
            return "Oops, you don't have that much money.";
        }
    }




}
