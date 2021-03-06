package ee.bcs.valiit.repository;

import ee.bcs.valiit.controller.AccountRowMapper;
import ee.bcs.valiit.controller.TransactionRowMapper;
import ee.bcs.valiit.dto.AccountDTOold;
import ee.bcs.valiit.hibernate.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jt;

//    public void AccountDAO(String accountNr, Double balance) {
//        String sql = "INSERT INTO table_accounts(account_number, balance) VALUES (:account_number, :balance)";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("account_number", accountNr);
//        paramMap.put("balance", balance);
//        jt.update(sql, paramMap);
//    }
// TODO remove after hibernate refactor

//    public Double getBalance(String accountNr) {
//        String sql = "SELECT balance FROM table_accounts WHERE account_number = :account_number";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("account_number", accountNr);
//        return jt.queryForObject(sql, paramMap, Double.class);
//    }

    public Boolean lockCheck(String accountNr) {
        String sql = "SELECT locked FROM table_accounts WHERE account_number = :account_number";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", accountNr);
        return jt.queryForObject(sql, paramMap, Boolean.class);
    }

    public void depositMoney(String accountNr, Double newBalance) {
        String sql = "UPDATE table_accounts SET balance = :balance WHERE account_number = :account_number";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", accountNr);
        paramMap.put("balance", newBalance);
        jt.update(sql, paramMap);
    }

    public void withdrawMoney(String accountNr, Double newBalance) {
        String sql = "UPDATE table_accounts SET balance = :balance WHERE account_number = :account_number";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_number", accountNr);
        paramMap.put("balance", newBalance);
        jt.update(sql, paramMap);
    }


    public List<AccountDTOold> getAllAccounts(AccountDTOold accountDTOold) {
        String sql = "SELECT * FROM table_accounts";
        Map<String, Object> accountMap = new HashMap<>();
        List<AccountDTOold> rs = jt.query(sql, accountMap, new AccountRowMapper());
        return rs;
    }

    public List<TransactionDAO> getAllTransactions(TransactionDAO transactionDAO) {
        String sql = "SELECT * FROM table_transfer";
        Map<String, Object> transactionMap = new HashMap<>();
        List<TransactionDAO> rs = jt.query(sql, transactionMap, new TransactionRowMapper());
        return rs;
    }

    public List<TransactionDAO> transactionHistoryCheck(TransactionDAO historyCheckReq) {
        String sql = "SELECT * FROM table_transfer WHERE from_account = :from_account";
        Map<String, Object> transactionMap = new HashMap<>();
        transactionMap.put("from_account", historyCheckReq.getFrom_account());
        List<TransactionDAO> rs = jt.query(sql, transactionMap, new TransactionRowMapper());
        return rs;
    }


    public void transactionRecorderDeduction(AccountDTOold withdrawMoneyReq) {
        String sql = "INSERT INTO table_transfer(from_account, date_time, deduction) VALUES (:from_account, :date_time, :deduction)";
        Map<String, Object> transactionMap = new HashMap<>();
        transactionMap.put("from_account", withdrawMoneyReq.getAccountnumber());
        transactionMap.put("date_time", LocalDateTime.now());
        transactionMap.put("deduction", withdrawMoneyReq.getBalance());
        jt.update(sql, transactionMap);
    }

    public void transactionRecorderAdd(AccountDTOold withdrawMoneyReq) {
        String sql = "INSERT INTO table_transfer(from_account, to_account, date_time, transfer) VALUES (:from_account, :to_account, :date_time, :transfer)";
        Map<String, Object> transactionMap = new HashMap<>();
        transactionMap.put("from_account", withdrawMoneyReq.getAccountnumber());
        transactionMap.put("to_account", withdrawMoneyReq.getAccountnumber2());
        transactionMap.put("date_time", LocalDateTime.now());
        transactionMap.put("transfer", withdrawMoneyReq.getBalance());
        jt.update(sql, transactionMap);
    }

}
