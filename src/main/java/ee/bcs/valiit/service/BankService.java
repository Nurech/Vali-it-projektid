package ee.bcs.valiit.service;

import ee.bcs.valiit.dto.AccountDTO;
import ee.bcs.valiit.dto.TransactionDTO;
import ee.bcs.valiit.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private NamedParameterJdbcTemplate jt;

    @Autowired
    private BankRepository bankRepository;

    public String createAccount(AccountDTO createReq) {
        String accountNr = createReq.getAccountnumber();
        Double balance = createReq.getBalance();
        bankRepository.createAccount(accountNr, balance);
        return "Account added = " + createReq.getAccountnumber() + ", with balance of = " + createReq.getBalance();
    }


    public String getBalanceReq(AccountDTO balanceReq) {
        Double balance = bankRepository.getBalance(balanceReq.getAccountnumber());
        return "Balance of " + balanceReq.getAccountnumber() + " is = " + balance;
    }


    public List<AccountDTO> allAccounts(AccountDTO accountDTO) {
        return bankRepository.getAllAccounts(accountDTO);
    }


    public String depositMoney(AccountDTO updateBalanceReq) {
        Boolean locked = bankRepository.lockCheck(updateBalanceReq.getAccountnumber());
        if (locked == true) {
            return "Account is locked";
        }
        if (updateBalanceReq.getBalance() > 0) {
            Double currentBalance = bankRepository.getBalance(updateBalanceReq.getAccountnumber());
            Double newBalance = currentBalance + updateBalanceReq.getBalance();
            bankRepository.depositMoney(updateBalanceReq.getAccountnumber(), newBalance);
            //transaction recorder
            bankRepository.transactionRecorderAdd(updateBalanceReq);
            return "Deposited " + updateBalanceReq.getBalance() + " to = " + updateBalanceReq.getAccountnumber() + ". New balance is = " + newBalance;
        }
        return "Please add positive number. You tried adding = " + updateBalanceReq.getBalance();
    }


    public String withdrawMoney(AccountDTO withdrawMoneyReq) {
        Boolean locked = bankRepository.lockCheck(withdrawMoneyReq.getAccountnumber());
        if (locked == true) {
            return "Account is locked";
        }
        Double currentBalance = bankRepository.getBalance(withdrawMoneyReq.getAccountnumber());
        if (withdrawMoneyReq.getBalance() <= currentBalance) {
            Double newBalance = currentBalance - withdrawMoneyReq.getBalance();
            bankRepository.depositMoney(withdrawMoneyReq.getAccountnumber(), newBalance);
            //transaction recorder
            bankRepository.transactionRecorderDeduction(withdrawMoneyReq);
            return "Withdrew " + withdrawMoneyReq.getBalance() + " from = " + withdrawMoneyReq.getAccountnumber() + ". Remaining = " + newBalance;
        }
        return "Can't withdraw, you don't have that much. You have = " + currentBalance;
    }


    public String transferMoney(AccountDTO transferMoneyReq) {
        Boolean fromLock = bankRepository.lockCheck(transferMoneyReq.getAccountnumber());
        Boolean toLock = bankRepository.lockCheck(transferMoneyReq.getAccountnumber2());
        if (fromLock == true) {
            return "Account is locked = " + transferMoneyReq.getAccountnumber();
        } else if (toLock == true) {
            return "Account is locked = " + transferMoneyReq.getAccountnumber2();
        } else {
            Double fromAccountCurrentBalance = bankRepository.getBalance(transferMoneyReq.getAccountnumber());
            if (fromAccountCurrentBalance > transferMoneyReq.getBalance()) {
                Double toAccountCurrentBalance = bankRepository.getBalance(transferMoneyReq.getAccountnumber2());
                Double moneyToTransfer = transferMoneyReq.getBalance();
                Double fromNewBalance = fromAccountCurrentBalance - moneyToTransfer;
                Double toNewBalance = toAccountCurrentBalance + moneyToTransfer;
                bankRepository.withdrawMoney(transferMoneyReq.getAccountnumber(), fromNewBalance);
                bankRepository.depositMoney(transferMoneyReq.getAccountnumber2(), toNewBalance);
                //transaction recorder
                bankRepository.transactionRecorderDeduction(transferMoneyReq);
                bankRepository.transactionRecorderAdd(transferMoneyReq);
                return "Transferred from " + transferMoneyReq.getAccountnumber() + " to " + transferMoneyReq.getAccountnumber2() + " sum of = " +transferMoneyReq.getBalance();
            }
            return "Not enough funds on = " + transferMoneyReq.getAccountnumber();
        }
    }

    public List<TransactionDTO> transactionHistory(TransactionDTO transactionDTO) {
        return bankRepository.getAllTransactions(transactionDTO);
    }

    public List<TransactionDTO> transactionHistoryCheck(TransactionDTO historyCheckReq) {
                return bankRepository.transactionHistoryCheck(historyCheckReq);
    }


}
