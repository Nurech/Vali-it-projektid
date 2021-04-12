package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.*;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    private static HashMap<String, Double> accountBalanceMap = new HashMap<String, Double>();
    private static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        // initial values
        int choice = 0;
        boolean quit = false;

        while (!quit) {
            //print menu
            printInstructions();
            out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    createAccount();
                    break;
                case 2:
                    getBalance();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    transfer();
                    break;
                case 6:
                    allBankAssets();
                    break;
                case 7:
                    out.println("Good bye");
                    scanner.close();
                    quit = true;
                    break;
                default:
                    out.println("Not a valid option");
            }
        }

    }

    // TODO 1
    // Add command: "createAccount ${accountNr}"
    // this has to store accountNr with 0 balance
    public static HashMap createAccount() {
        out.println("Type exit to go back.");
        out.print("Enter account: ");
        String account = scanner.skip("\\R").nextLine();
        if (account.equalsIgnoreCase("exit")) {
            return accountBalanceMap;
        } else {
                if (accountBalanceMap.get(checkIfAccountExists(account)) == null) {
                    out.print("Enter value: ");
                    double money = Double.parseDouble(scanner.nextLine());
                    accountBalanceMap.put(account, money);
                } else {
                    out.println("Account already present mate.");
                }
            }
        return accountBalanceMap;

    }



    // TODO 2
    // Add command: "getBalance ${accountNr}"
    // this has to display account balance of specific account
    public static void getBalance() {
        out.print("What account balance to check?: ");
        String account = scanner.next();
        if (accountBalanceMap.get(checkIfAccountExists(account)) != null) {
            out.println("Account: " + account + " has " + accountBalanceMap.get(account));
        }
    }

    // TODO 3
    // Add command: "depositMoney ${accountNr} ${amount}
    // this has to add specified amount of money to account
    // You have to check that amount is positive number
    public static String depositMoney() {
        out.print("To what account you want to deposit to?: ");
        String account = scanner.next();
        if (accountBalanceMap.get(checkIfAccountExists(account)) != null) {
            out.print("Ok, so you want to add to " + account + ". How much?: ");
            double moneyToAdd = scanner.nextDouble();
            if (moneyToAdd > 0) {
                double currentBalance = accountBalanceMap.get(account);
                accountBalanceMap.put(account, moneyToAdd + currentBalance);
            }
            out.println("Enter a positive value.");
        }
        out.println("Can't add to an account which doesn't exist.");
        return account;
    }

    // TODO 4
    // Add command: "withdrawMoney ${accountNr} ${amount}
    // This has to remove specified amount of money from account
    // You have to check that amount is positive number
    // You may not allow this transaction if account balance would become negative
    public static void withdrawMoney() {
        out.print("To what account you want to withdraw from?: ");
        String account = scanner.next();

        if (accountBalanceMap.get(checkIfAccountExists(account)) == null) {
            out.println("Can't withdraw from an account which doesn't exist.");
        } else {
            out.print("Ok, so you want to withdraw from " + account + ". How much?: ");
            double moneyToWithdraw = scanner.nextDouble();

            if (moneyToWithdraw < 0) {
                out.println("Enter a positive value.");
            } else {
                if (moneyToWithdraw <= (accountBalanceMap.get(account))) {
                    double currentBalance = accountBalanceMap.get(account);
                    accountBalanceMap.put(account, currentBalance - moneyToWithdraw);
                    out.println("Withdraw " + moneyToWithdraw + " balance left: " + accountBalanceMap.get(account));
                } else {
                    out.println("You can't withdraw that much. You have: " + accountBalanceMap.get(account));
                }
            }
        }
    }

    // TODO 5
    // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
    // This has to remove specified amount from fromAccount and add it to toAccount
    // Your application needs to check that toAccount is positive
    // And from account has enough money to do that transaction
    public static void transfer() {
        out.print("To which account you want to transfer money to?: ");
        String account = scanner.next();

        // does account 1 exist?
        if (accountBalanceMap.get(checkIfAccountExists(account)) == null) {
            out.println("Can't transfer form " + account + ", account which doesn't exist.");
        } else {
            out.print("Which account you want to transfer from?: ");
            String account2 = scanner.next();

            // does account 2 exist?
            if (accountBalanceMap.get(checkIfAccountExists(account2)) == null) {
                out.println("Can't transfer to " + account2 + ", account which doesn't exist.");
            } else {
                out.println("Ok, so you want to transfer from " + account2 + " to " + account);
                out.print("Ok, how much?: ");
                double transferMoney = scanner.nextDouble();

                // is there enough to transfer from?
                if ((accountBalanceMap.get(account2) >= transferMoney)) {
                    // take current balance
                    double account1Money = accountBalanceMap.get(account);
                    double account2Money = accountBalanceMap.get(account2);
                    // update balance
                    double dedeucted1Money = account2Money-transferMoney;
                    double addedMoney = account1Money+transferMoney;
                    // put new balances back for both
                    accountBalanceMap.put(account,addedMoney);
                    accountBalanceMap.put(account2,dedeucted1Money);
                    out.println("Transferred from " + account2 + " to " + account + ", " + transferMoney);
                    out.println("New balance of " + account + " is " + accountBalanceMap.get(account));
                    out.println("Remaining balance of " + account2 + " is " + accountBalanceMap.get(account2));
                } else {
                    out.println("Oops, you don't have that much money.");
                }
            }
        }
    }

    //check if account is present
    public static String checkIfAccountExists(String account) {
        if (accountBalanceMap.get(account) == null) {
          //  out.println("Sorry no such account mate.");
        }
        return account;
    }

    //print all assets
    public static void allBankAssets() {
        for (String i : accountBalanceMap.keySet()) {
            System.out.println("Account: " + i + " Money: " + accountBalanceMap.get(i));
        }
    }

    //menu print
    public static void printInstructions() {
        out.println("\n Press ");
        out.println("\t 0 - To print choice options.");
        out.println("\t 1 - Create account.");
        out.println("\t 2 - Get balance.");
        out.println("\t 3 - Deposit money.");
        out.println("\t 4 - Withdraw money.");
        out.println("\t 5 - Transfer account.");
        out.println("\t 6 - Print all bank holdings.");
        out.println("\t 7 - To quit the application.");
    }
}



