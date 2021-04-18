package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private NamedParameterJdbcTemplate jt;

    private HashMap<String, AccountDTO> account = new HashMap<String, AccountDTO>();


    // CREATE ACCOUNT
    // http://localhost:8080/createAccount
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody AccountDTO createReq) {

        String sql = "INSERT INTO table_accounts(account_number, balance) VALUES (:account_number, :balance)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", createReq.getAccountnumber());
        paramMap.put("balance", createReq.getBalance());
        jt.update(sql, paramMap);
        return "Account added = " + createReq.getAccountnumber() + ", with balance of = " + createReq.getBalance();
    }

    // DEPOSIT MONEY
    // http://localhost:8080/depositMoney
    @PostMapping("/depositMoney")
    public String depositMoney(@RequestBody AccountDTO depositReq) {

        if (depositReq.getBalance() > 0) {

            // old balance for calculations
            String old = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("balance", depositReq.getBalance());
            paramMap1.put("account_number", depositReq.getAccountnumber());
            Double oldBalance = jt.queryForObject(old, paramMap1, Double.class);

            // update balance with deposit
            String deposit = "UPDATE table_accounts SET balance = :balance WHERE account_number = :account_number";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("account_number", depositReq.getAccountnumber());
            paramMap2.put("balance", depositReq.getBalance() + oldBalance);
            jt.update(deposit, paramMap2);

            // print new balance
            String newBalance = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
            Map<String, Object> paramMap3 = new HashMap<>();
            paramMap3.put("balance", depositReq.getBalance());
            paramMap3.put("account_number", depositReq.getAccountnumber());
            Double balance = jt.queryForObject(newBalance, paramMap1, Double.class);

            return "Added " + depositReq.getBalance() + " to " + depositReq.getAccountnumber() + ", new balance is = " + balance;
        }
        return "Enter a positive value.";

    }


    // GET ALL
    // http://localhost:8080/all
    @GetMapping("/all")
    public Iterator<AccountDTO> getAll() {
        String sql = "SELECT * FROM table_accounts";
        Map<String, Object> accountMap = new HashMap<>();
        List<AccountDTO> resultList = jt.query(sql, accountMap, new ObjectRowMapper());
        return resultList.stream().iterator();
    }


    // BALANCE REQUEST
    // http://localhost:8080/balance
    @GetMapping("/balance")
    public String getBalance(@RequestBody AccountDTO getBalanceReq) {

        String sql = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", getBalanceReq.getAccountnumber());
        Double balance = jt.queryForObject(sql, paramMap, Double.class);
        return "Balance of " + getBalanceReq.getAccountnumber() + " is = " + balance;

    }


    // WITHDRAW REQUEST
    // http://localhost:8080/withdrawMoney
    @PostMapping("/withdrawMoney")
    public String withdrawMoney(@RequestBody AccountDTO withdrawMoneyReq) {

        // old balance for calculations
        String old = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("balance", withdrawMoneyReq.getBalance());
        paramMap1.put("account_number", withdrawMoneyReq.getAccountnumber());
        Double oldBalance = jt.queryForObject(old, paramMap1, Double.class);

        if (withdrawMoneyReq.getBalance() <= oldBalance) {

            // update balance for withdraw
            String withdraw = "UPDATE table_accounts SET balance = :balance WHERE account_number = :account_number";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("account_number", withdrawMoneyReq.getAccountnumber());
            paramMap2.put("balance", oldBalance - withdrawMoneyReq.getBalance());
            jt.update(withdraw, paramMap2);

            // print new balance
            String newBalance = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
            Map<String, Object> paramMap3 = new HashMap<>();
            paramMap3.put("balance", withdrawMoneyReq.getBalance());
            paramMap3.put("account_number", withdrawMoneyReq.getAccountnumber());
            Integer balance = jt.queryForObject(newBalance, paramMap1, Integer.class);

            return "Withdrew " + withdrawMoneyReq.getBalance() + " from " + withdrawMoneyReq.getAccountnumber() + ", new balance is = " + (double) balance;
        }
        return "Can't withdraw that much. You have = " + oldBalance;
    }


    //TODO
    // TRANSFER REQUEST
    // http://localhost:8080/transferMoney
    @PostMapping("/transferMoney")
    public String transferMoney(@RequestBody AccountDTO transferMoneyReq) {

        // get initial data
        String data = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
        String lockData = "SELECT locked FROM table_accounts WHERE account_number = :account_number";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("balance", transferMoneyReq.getBalance());
        paramMap1.put("account_number", transferMoneyReq.getAccountnumber());
        paramMap1.put("locked", transferMoneyReq.isLocked());
        Double fromBalance = jt.queryForObject(data, paramMap1, Double.class);
        Boolean fromLock = jt.queryForObject(lockData, paramMap1, Boolean.class);

        // get initial data
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("balance", transferMoneyReq.getBalance());
        paramMap2.put("account_number", transferMoneyReq.getAccountnumber2());
        paramMap2.put("locked", transferMoneyReq.isLocked());
        Double toBalance = jt.queryForObject(data, paramMap2, Double.class);
        Boolean toLock = jt.queryForObject(lockData, paramMap2, Boolean.class);

        // lock check
        if (fromLock == true) {
            return "Account is locked = " + transferMoneyReq.getAccountnumber();
        } else {
            if (toLock == true) {
                return "Account is locked = " + transferMoneyReq.getAccountnumber2();
            } else {
                // enough funds check
                if (fromBalance >= transferMoneyReq.getBalance()) {
                    // make transfer, update balance's
                    String add = "UPDATE table_accounts SET balance = :balance WHERE account_number = :account_number";
                    String remove = "UPDATE table_accounts SET balance = :balance2 WHERE account_number = :account_number2";
                    Map<String, Object> paramMap3 = new HashMap<>();
                    paramMap3.put("account_number", transferMoneyReq.getAccountnumber());
                    paramMap3.put("account_number2", transferMoneyReq.getAccountnumber2());
                    paramMap3.put("balance", fromBalance - transferMoneyReq.getBalance());
                    paramMap3.put("balance2", toBalance + transferMoneyReq.getBalance());
                    jt.update(add, paramMap3);
                    jt.update(remove, paramMap3);
                    return "Transferred";
                }
                return "Not enough funds on = " + transferMoneyReq.getAccountnumber();
            }
        }
    }
}

