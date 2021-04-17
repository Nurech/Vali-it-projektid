package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class AccountController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private HashMap<String, AccountDTO> account = new HashMap<String, AccountDTO>();


    // CREATE ACCOUNT
    // http://localhost:8080/createAccount
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody AccountDTO createReq) {
            String sql = "INSERT INTO table_accounts(account_number, balance) VALUES (:account_number, :balance)";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("account_number", createReq.getAccountnumber());
            paramMap.put("balance", createReq.getBalance());
            jdbcTemplate.update(sql,paramMap);
            return "Account added = " + createReq.getAccountnumber() + ", with balance of = " + createReq.getBalance();
        }

    // DEPOSIT MONEY
    // http://localhost:8080/depositMoney
    @PostMapping("/depositMoney")
    public String depositMoney(@RequestBody AccountDTO depositReq) {
        if (account.get(depositReq.getAccountnumber()) != null) {
            if (depositReq.getBalance() > 0) {
                // currentBalance Object is get money from inserted accountnumber
                AccountDTO currentBalance = account.get(depositReq.getAccountnumber());
                //currentBalance Object holds is inserted money + current money
                currentBalance.setBalance(depositReq.getBalance() + currentBalance.getBalance());
                // account getAccountNumber put new currentBalanace
                account.put(depositReq.getAccountnumber(), currentBalance);
                return "Added " + depositReq.getBalance() + " to " + depositReq.getAccountnumber() + ", new balance is " + currentBalance.getBalance();
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
            return "Account = " + getBalanceReq.getAccountnumber() + " has = " + account.get(getBalanceReq.getAccountnumber()).getBalance();
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
            if (withdrawMoneyReq.getBalance() <= currentBalance.getBalance()) {
                currentBalance.setBalance(currentBalance.getBalance() - withdrawMoneyReq.getBalance());
                account.put(withdrawMoneyReq.getAccountnumber(), currentBalance);
                return "Withdrew " + withdrawMoneyReq.getBalance() + " new balance is " + currentBalance.getBalance();
            } else {
                return "You can't withdraw that much. You have: " + currentBalance.getBalance();
            }
        }
        return "Can't withdraw from an account which doesn't exist.";
    }


    // TRANSFER REQUEST
    // http://localhost:8080/transferMoney
    @PostMapping("/transferMoney")
    public String transferMoney(@RequestBody AccountDTO transferMoneyReq) {

        if (account.get(transferMoneyReq.getAccountnumber()) != null) {
            AccountDTO fromAccount = account.get(transferMoneyReq.getAccountnumber());
            AccountDTO toAccount = account.get(transferMoneyReq.getAccountnumber2());

            // if fromAccount balance has enough funds then do transfer
            if (transferMoneyReq.getBalance() <= fromAccount.getBalance()) {
                double fromOldBalance = fromAccount.getBalance();
                double toOldBalance = toAccount.getBalance();

                // update balance
                fromAccount.setBalance(fromAccount.getBalance() - transferMoneyReq.getBalance());
                toAccount.setBalance(toAccount.getBalance() + transferMoneyReq.getBalance());
                account.put(transferMoneyReq.getAccountnumber(), fromAccount);
                account.put(transferMoneyReq.getAccountnumber2(), toAccount);

                return "Transferred from " + transferMoneyReq.getAccountnumber() + " to " + transferMoneyReq.getAccountnumber2() + ", " + transferMoneyReq.getBalance() + "\n" +
                        transferMoneyReq.getAccountnumber() + " had = " + fromOldBalance + ", now is = " + fromAccount.getBalance() + "\n" +
                        transferMoneyReq.getAccountnumber2() + " had = " + toOldBalance + ", now is = " + toAccount.getBalance();
            }
            return "Oops, you don't have that much money.";
        }
        return "One of the accounts is not present, can't transfer funds.";
    }
}

