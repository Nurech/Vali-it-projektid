package ee.bcs.valiit.controller;

import ee.bcs.valiit.hibernate.AccountDAO;
import ee.bcs.valiit.hibernate.AccountEntity;
import ee.bcs.valiit.hibernate.TransactionEntity;
import ee.bcs.valiit.hibernate.TransferDAO;
import ee.bcs.valiit.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class BankController {

    @Autowired
    private BankService bankService;


    // CREATE ACCOUNT
    // http://localhost:8081/createAccount
    @PostMapping("/createAccount")
    @CrossOrigin
    public String createAccount(@RequestBody AccountDAO createReq) {
        return bankService.createAccount(createReq);
    }

    // BALANCE REQUEST
    // http://localhost:8081/balance
    @GetMapping("/balance/{accountNumber}")
    @CrossOrigin
    public String getBalance(@PathVariable("accountNumber") String accountNr) {
        return bankService.getBalanceReq(accountNr);
    }

    // DEPOSIT MONEY
    // http://localhost:8081/deposit
    @PostMapping("/deposit")
    @CrossOrigin
    public String depositMoney(@RequestBody AccountDAO updateBalanceReq) {
        return bankService.depositMoney(updateBalanceReq);
    }

    // GET ALL
    // http://localhost:8081/allAccounts
    @GetMapping("/allAccounts")
    @CrossOrigin
    public List<AccountEntity> allAccounts() {
        return bankService.allAccounts();
    }

    // WITHDRAW REQUEST
    // http://localhost:8081/withdraw
    @PostMapping("/withdraw")
    @CrossOrigin
    public String withdrawMoney(@RequestBody AccountDAO withdrawMoneyReq) {
        return bankService.withdrawMoney(withdrawMoneyReq);
    }

    // TRANSFER REQUEST
    // http://localhost:8081/transfer
    @PostMapping("/transfer")
    @CrossOrigin
    public String transferMoney(@RequestBody TransferDAO transferMoneyReq) {
        return bankService.transferMoney(transferMoneyReq);
    }

    // TRANSACTION HISTORY ALL ACCOUNTS
    // http://localhost:8081/history
    @GetMapping("/history")
    @CrossOrigin
    public List<TransactionEntity> allHistory() {
        return bankService.allHistory();
    }

    // TRANSACTION HISTORY FOR ONE ACCOUNT
    // http://localhost:8081/historyCheck
    @GetMapping("/historyCheck/{accountNumber}")
    @CrossOrigin
    public List<TransactionEntity> historyCheck(@PathVariable("accountNumber") String fromAccount) {
        return bankService.historyCheck(fromAccount);
    }
}

