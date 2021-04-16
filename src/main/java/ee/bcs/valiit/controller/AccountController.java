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

    //check if account is present
    public String checkIfAccountExists(String accountName) {
        if (account.get(accountName) == null) {
            return "Account not present";
        }
        return "Did your thing";
    }


    // CREATE ACCOUNT
    // http://localhost:8080/createAccount
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody AccountDTO createReq) {
        if (account.get(checkIfAccountExists(createReq.getAccountnumber())) == null) {
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
        if (account.get(checkIfAccountExists(depositReq.getAccountnumber())) == null) {
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
        if (account.get(checkIfAccountExists(getBalanceReq.getAccountnumber())) == null) {
            return "Account = " + account.get(getBalanceReq.getMoney());
        }
        return "No account";
    }
}
//
//    //WITHDRAW REQUEST
//    //http://localhost:8080/tasks/Lesson4/withdrawMoney
//    @PostMapping("/tasks/Lesson4/withdrawMoney")
//    public String withdrawMoney(@RequestBody Lesson4 withdrawMoneyRequest) {
//        if (account.get(checkIfAccountExists(withdrawMoneyRequest.getAccountName())) == null) {
//            if (withdrawMoneyRequest.getMoney() <= (account.get(withdrawMoneyRequest.getAccountName()))) {
//                double currentBalance = account.get(withdrawMoneyRequest.getAccountName());
//                account.put(withdrawMoneyRequest.getAccountName(), currentBalance - withdrawMoneyRequest.getMoney());
//                return "Old balance was = " + currentBalance + " withdrew " + withdrawMoneyRequest.getMoney() + " new balance is " + account.get(withdrawMoneyRequest.getAccountName());
//            } else {
//                return "You can't withdraw that much. You have: " + account.get(withdrawMoneyRequest.getAccountName());
//            }
//        }
//        return "Can't withdraw from an account which doesn't exist.";
//    }
//
//
//    //TRANSFER REQUEST
//    //http://localhost:8080/tasks/Lesson4/transferMoney
//    @PostMapping("/tasks/Lesson4/transferMoney")
//    public String transferMoney(@RequestBody Lesson4 transferMoneyRequest) {
//
//        if ((account.get(transferMoneyRequest.getFromAccount()) >= transferMoneyRequest.getMoney())) {
//            // take current balance
//            double account2Balance = account.get(transferMoneyRequest.getFromAccount());
//            double balance = account.get(transferMoneyRequest.getAccountName());
//            // update balance
//            double deductedMoney = account2Balance - transferMoneyRequest.getMoney();
//            double addedMoney = balance + transferMoneyRequest.getMoney();
//            // put new balances back for both
//            account.put(transferMoneyRequest.getFromAccount(), addedMoney);
//            account.put(transferMoneyRequest.getAccountName(), deductedMoney);
//            return "Transferred from " + transferMoneyRequest.getFromAccount() + " to " + transferMoneyRequest.getAccountName() + ", " + transferMoneyRequest.getMoney() + "\n" +
//                    transferMoneyRequest.getFromAccount() + " is " + account.get(transferMoneyRequest.getFromAccount()) + "\n" +
//                    transferMoneyRequest.getAccountName() + " is " + account.get(transferMoneyRequest.getAccountName());
//        } else {
//            return "Oops, you don't have that much money.";
//        }
//    }

