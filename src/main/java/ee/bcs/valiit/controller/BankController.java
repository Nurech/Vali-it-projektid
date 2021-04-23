package ee.bcs.valiit.controller;

import ee.bcs.valiit.dto.AccountDTOold;
import ee.bcs.valiit.dto.TransactionDTO;
import ee.bcs.valiit.hibernate.AccountDTO;
import ee.bcs.valiit.hibernate.CreateAccount;
import ee.bcs.valiit.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    // CREATE ACCOUNT
    // http://localhost:8081/createAccount
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody CreateAccount createReq) {
        return bankService.createAccount(createReq);
    }

    // BALANCE REQUEST
    // http://localhost:8081/balance
    @GetMapping("/balance")
    public String getBalance(@RequestBody AccountDTO balanceReq) {
        return bankService.getBalanceReq(balanceReq);
    }

    // DEPOSIT MONEY
    // http://localhost:8081/deposit
    @PostMapping("/deposit")
    public void depositMoney(@RequestBody AccountDTOold updateBalanceReq) {
        bankService.depositMoney(updateBalanceReq);
    }

    // GET ALL
    // http://localhost:8081/allAccounts
    @GetMapping("/allAccounts")
    public List<AccountDTO> allAccounts() {
        return bankService.allAccounts();
    }

    // WITHDRAW REQUEST
    // http://localhost:8081/withdraw
    @PostMapping("/withdraw")
    public String withdrawMoney(@RequestBody AccountDTOold withdrawMoneyReq) {
        return bankService.withdrawMoney(withdrawMoneyReq);
    }

    // TRANSFER REQUEST
    // http://localhost:8081/transfer
    @PostMapping("/transfer")
    public String transferMoney(@RequestBody AccountDTOold transferMoneyReq) {
        return bankService.transferMoney(transferMoneyReq);
    }

    // TRANSACTION HISTORY ALL ACCOUNTS
    // http://localhost:8081/history
    @GetMapping("/history")
    public List<TransactionDTO> transactionHistory(TransactionDTO transactionDTO) {
        return bankService.transactionHistory(transactionDTO);
    }

    // TRANSACTION HISTORY FOR ONE ACCOUNT
    // http://localhost:8081/historyCheck
    @GetMapping("/historyCheck")
    public List<TransactionDTO> transactionHistoryCheck(@RequestBody TransactionDTO historyCheckReq) {
        return bankService.transactionHistoryCheck(historyCheckReq);
    }
}

