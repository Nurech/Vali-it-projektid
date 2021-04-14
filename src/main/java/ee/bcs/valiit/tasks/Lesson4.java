package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    private static HashMap<String, Double> accountBalanceMap = new HashMap<String, Double>();

    public static void main(String[] args) {
    }

    // TODO 1
    // Add command: "createAccount ${accountNr}"
    // this has to store accountNr with 0 balance
    public static String createAccount(String accountName, double money) {
        if (accountBalanceMap.get(checkIfAccountExists(accountName)) == null) {
            accountBalanceMap.put(accountName, money);
            return "Account added";
        } else {
            return "Account already present";
        }
    }

    // TODO 2
    // Add command: "getBalance ${accountNr}"
    // this has to display account balance of specific account
    public static String getBalance(String accountName) {

        if (accountBalanceMap.get(checkIfAccountExists(accountName)) != null) {
            return "Account = " + accountBalanceMap.get(accountName);
        }
        return "Account not present";
    }

    // TODO 3
    // Add command: "depositMoney ${accountNr} ${amount}
    // this has to add specified amount of money to account
    // You have to check that amount is positive number
    public static String depositMoney(String accountName, double money) {

        if (accountBalanceMap.get(checkIfAccountExists(accountName)) != null) {
            if (money > 0) {
                double currentBalance = accountBalanceMap.get(accountName);
                accountBalanceMap.put(accountName, money + currentBalance);
                return "Added the money";
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
            return "Can't withdraw from an account which doesn't exist.";
        } else  {
                if (money <= (accountBalanceMap.get(accountName))) {
                    double currentBalance = accountBalanceMap.get(accountName);
                    accountBalanceMap.put(accountName, currentBalance - money);
                 return "Old balance was = " + currentBalance + " withdrew " + money + " new balance is " + accountBalanceMap.get(accountName);
                } else {
                    return "You can't withdraw that much. You have: " + accountBalanceMap.get(accountName);
                }
            }
        }

    // TODO 5
    // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
    // This has to remove specified amount from fromAccount and add it to toAccount
    // Your application needs to check that toAccount is positive
    // And from account has enough money to do that transaction
    public static String transfer(String fromAccount, String toAccount, double transfer) {

        // does account 1 exist?
        if (accountBalanceMap.get(checkIfAccountExists(toAccount)) == null) {
          return  "Can't transfer form " + fromAccount + ", account which doesn't exist.";
        } else if (accountBalanceMap.get(checkIfAccountExists(toAccount)) == null) {
                return "Can't transfer to " + toAccount + ", account which doesn't exist.";
            } else if ((accountBalanceMap.get(fromAccount) >= transfer)) {
                    // take current balance
                    double balance = accountBalanceMap.get(fromAccount);
                    double account2Balance = accountBalanceMap.get(toAccount);
                    // update balance
                    double deductedMoney = account2Balance - transfer;
                    double addedMoney = balance + transfer;
                    // put new balances back for both
                    accountBalanceMap.put(fromAccount, addedMoney);
                    accountBalanceMap.put(toAccount, deductedMoney);
                    return "Transferred from " + toAccount + " to " + fromAccount + ", " + transfer +
                            "New balance of " + fromAccount + " is " + accountBalanceMap.get(fromAccount) +
                            "Remaining balance of " + toAccount + " is " + accountBalanceMap.get(toAccount);
                } else {
                    return "Oops, you don't have that much money.";
                }
            }

    //check if account is present
    public static String checkIfAccountExists(String accountName) {
        if (accountBalanceMap.get(accountName) == null) {
            return "Account not present";
        }
        return "Did your thing";
    }

    //print all assets
    public static Set<Map.Entry<String, Double>> allBankAssets() {
          return accountBalanceMap.entrySet();
        }

}




