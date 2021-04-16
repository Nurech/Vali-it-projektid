package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.AccountDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class AccountController {

    private HashMap<String, AccountDTO> account = new HashMap<String, AccountDTO>();


    // CREATE ACCOUNT
    // http://localhost:8080/createAccount
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody AccountDTO createReq) {
        if (account.get(createReq.getAccountnumber()) == null) {
            account.put(createReq.getAccountnumber(), createReq);
            return "Account added = " + createReq.getAccountnumber() + ", with balance of = " + createReq.getMoney();
        } else {
            return "Account already present";
        }
    }

    // DEPOSIT MONEY
    // http://localhost:8080/depositMoney
    @PostMapping("/depositMoney")
    public String depositMoney(@RequestBody AccountDTO depositReq) {
        if (account.get(depositReq.getAccountnumber()) != null) {
            if (depositReq.getMoney() > 0) {
                // currentBalance Object is get money from inserted accountnumber
                AccountDTO currentBalance = account.get(depositReq.getAccountnumber());
                //currentBalance Object holds is inserted money + current money
                currentBalance.setMoney(depositReq.getMoney() + currentBalance.getMoney());
                // account getAccountNumber put new currentBalanace
                account.put(depositReq.getAccountnumber(), currentBalance);
                return "Added " + depositReq.getMoney() + " to " + depositReq.getAccountnumber() + ", new balance is " + currentBalance.getMoney();
            }
            return "Enter a positive value.";
        }
        return "Can't add to an account which doesn't exist.";
    }


    // GET ALL
    // http://localhost:8080/all
    @GetMapping("/all")
    public Set<Map.Entry<String, AccountDTO>> getAll() {
        return account.entrySet();
    }


    // BALANCE REQUEST
    // http://localhost:8080/balance
    @GetMapping("/balance")
    public String getBalance(@RequestBody AccountDTO getBalanceReq) {

        if (account.get(getBalanceReq.getAccountnumber()) != null) {
            return "Account = " + getBalanceReq.getAccountnumber() + " has = " + account.get(getBalanceReq.getAccountnumber()).getMoney();
        }
        return "No account";
    }


    // WITHDRAW REQUEST
    // http://localhost:8080/withdrawMoney
    @PostMapping("/withdrawMoney")
    public String withdrawMoney(@RequestBody AccountDTO withdrawMoneyReq) {
        if (account.get(withdrawMoneyReq.getAccountnumber()) != null) {
            // currentBalance Object is get money from inserted accountnumber
            AccountDTO currentBalance = account.get(withdrawMoneyReq.getAccountnumber());
            // if input money if less than balance then allow withdraw
            if (withdrawMoneyReq.getMoney() <= currentBalance.getMoney()) {
                currentBalance.setMoney(currentBalance.getMoney() - withdrawMoneyReq.getMoney());
                account.put(withdrawMoneyReq.getAccountnumber(), currentBalance);
                return "Withdrew " + withdrawMoneyReq.getMoney() + " new balance is " + currentBalance.getMoney();
            } else {
                return "You can't withdraw that much. You have: " + currentBalance.getMoney();
            }
        }
        return "Can't withdraw from an account which doesn't exist.";
    }



//    // TRANSFER REQUEST
//    // http://localhost:8080/transferMoney
//    @PostMapping("/transferMoney")
//    public String transferMoney(@RequestBody AccountDTO transferMoneyReq) {
//
//        if (account.get(transferMoneyReq.getAccountnumber()) != null) {
//            AccountDTO account1Balance = account.get(transferMoneyReq.getAccountnumber());
//            AccountDTO account2Balance = account.get(transferMoneyReq.getAccountnumber2());
//            // if account 1 balance has enough funds then do transfer
//            if (account.get(transferMoneyReq.getMoney() <= account))
//            if (ac)
//
//            if (account.get(transferMoneyReq.getAccountnumber()))
//
//            // take current balance
//            double account2Balance = account.get(transferMoneyReq.getFromAccount());
//            double balance = account.get(transferMoneyReq.getAccountName());
//            // update balance
//            double deductedMoney = account2Balance - transferMoneyReq.getMoney();
//            double addedMoney = balance + transferMoneyReq.getMoney();
//            // put new balances back for both
//            account.put(transferMoneyReq.getFromAccount(), addedMoney);
//            account.put(transferMoneyReq.getAccountName(), deductedMoney);
//            return "Transferred from " + transferMoneyReq.getFromAccount() + " to " + transferMoneyReq.getAccountName() + ", " + transferMoneyReq.getMoney() + "\n" +
//                    transferMoneyReq.getFromAccount() + " is " + account.get(transferMoneyReq.getFromAccount()) + "\n" +
//                    transferMoneyReq.getAccountName() + " is " + account.get(transferMoneyReq.getAccountName());
//        } else {
//            return "Oops, you don't have that much money.";
//        }
//    }
}

