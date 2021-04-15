package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson4;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class Lesson4Controller {

    private static HashMap<String, Double> accountBalanceMap = new HashMap<String, Double>();

    //check if account is present
    public static String checkIfAccountExists(String accountName) {
        if (accountBalanceMap.get(accountName) == null) {
            return "Account not present";
        }
        return "Did your thing";
    }

    //CREATE ACCOUNT
    // http://localhost:8080/tasks/Lesson4/Joosep/1000
    @PostMapping("/tasks/Lesson4/{accountName}/{money}")
    public String addAccount(@PathVariable("accountName") String accountName,@PathVariable("accountName") double money, @RequestBody Lesson4 lesson4) {
        if (accountBalanceMap.get(checkIfAccountExists(accountName)) == null) {
            accountBalanceMap.put(accountName, money);
            return "Account added = " + accountName + ", with balance of = " + money;
        } else {
            return "Account already present";
        }
    }

    //GET ALL
    // http://localhost:8080/tasks/Lesson4
    @GetMapping("/tasks/Lesson4/")
    public Set<Map.Entry<String, Double>> getAll(){
        return accountBalanceMap.entrySet();
    }

//    //print all assets
//    public static Set<Map.Entry<String, Double>> allBankAssets() {
//        return accountBalanceMap.entrySet();
//    }





    // TODO 1
    // Add command: "createAccount ${accountNr}"
    // this has to store accountNr with 0 balance
    public static String createAccount(String accountName, double money) {
        if (accountBalanceMap.get(checkIfAccountExists(accountName)) == null) {
            accountBalanceMap.put(accountName, money);
            return "Account added = " + accountName + ", with balance of = " + money;
        } else {
            return "Account already present";
        }
    }

    // TODO 2
    // Add command: "getBalance ${accountNr}"
    // this has to display account balance of specific account
    public static String getBalance(String accountName) {

        if (accountBalanceMap.get(checkIfAccountExists(accountName)) == null) {
            return "Account = " + accountBalanceMap.get(accountName);
        }
        return "No account";
    }

    // TODO 3
    // Add command: "depositMoney ${accountNr} ${amount}
    // this has to add specified amount of money to account
    // You have to check that amount is positive number
    public static String depositMoney(String accountName, double money) {

        if (accountBalanceMap.get(checkIfAccountExists(accountName)) == null) {
            if (money > 0) {
                double currentBalance = accountBalanceMap.get(accountName);
                accountBalanceMap.put(accountName, money + currentBalance);
                return "Added " + money + " to " + accountName + ", new balance is " + accountBalanceMap.get(accountName);
            }
            return "Enter a positive value.";
        }
        return "Can't add to an account which doesn't exist.";
    }

    // TODO 4
    // Add command: "withdrawMoney ${accountNr} ${amount}
    // This has to remove specified amount of money from account
    // You have to check that amount is positive number
    // You may not allow this transaction if account balance would become negative
    public static String withdrawMoney(String accountName, double money) {

        if (accountBalanceMap.get(checkIfAccountExists(accountName)) == null) {
            if (money <= (accountBalanceMap.get(accountName))) {
                double currentBalance = accountBalanceMap.get(accountName);
                accountBalanceMap.put(accountName, currentBalance - money);
                return "Old balance was = " + currentBalance + " withdrew " + money + " new balance is " + accountBalanceMap.get(accountName);
            } else {
                return "You can't withdraw that much. You have: " + accountBalanceMap.get(accountName);
            }
        }
        return "Can't withdraw from an account which doesn't exist.";
    }

    // TODO 5
    // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
    // This has to remove specified amount from fromAccount and add it to toAccount
    // Your application needs to check that toAccount is positive
    // And from account has enough money to do that transaction
    public static String transfer(String fromAccount, String accountName, double money) {


        if ((accountBalanceMap.get(fromAccount) >= money)) {
            // take current balance
            double balance = accountBalanceMap.get(fromAccount);
            double account2Balance = accountBalanceMap.get(accountName);
            // update balance
            double deductedMoney = account2Balance - money;
            double addedMoney = balance + money;
            // put new balances back for both
            accountBalanceMap.put(fromAccount, addedMoney);
            accountBalanceMap.put(accountName, deductedMoney);
            return "Transferred from " + accountName + " to " + fromAccount + ", " + money + "\n" +
                    "New balance of " + fromAccount + " is " + accountBalanceMap.get(fromAccount) + "\n" +
                    "Remaining balance of " + accountName + " is " + accountBalanceMap.get(accountName);
        } else {
            return "Oops, you don't have that much money.";
        }
    }




}
