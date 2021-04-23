package ee.bcs.valiit.service;

import ee.bcs.valiit.ExeptionHandler.ApplicationExpetion;
import ee.bcs.valiit.dto.AccountDTOold;
import ee.bcs.valiit.dto.TransactionDTO;
import ee.bcs.valiit.hibernate.AccountDAO;
import ee.bcs.valiit.hibernate.AccountEntity;
import ee.bcs.valiit.hibernate.AccountREPO;
import ee.bcs.valiit.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountREPO accountREPO;

    // TODO hibernate
    public String createAccount(AccountDAO createReq) {
        if (createReq.getBalance() < 0) {
            throw new ApplicationExpetion("Deposit can't be negative");
        }
        AccountEntity account = new AccountEntity(createReq);
        account.setAccountNumber(createReq.getAccountNumber());
        account.setBalance(createReq.getBalance());
        accountREPO.save(account);
        return "Account added = " + createReq.getAccountNumber() + ", with balance of = " + createReq.getBalance();
    }


    public String getBalanceReq(AccountEntity balanceReq) {
        AccountEntity account = accountREPO.getOne(balanceReq.getAccountNumber());
        return "Balance of " + account.getAccountNumber() + " is = " + accountREPO.getOne(balanceReq.getAccountNumber()).getBalance();
    }


    public List<AccountEntity> allAccounts() {
        return accountREPO.findAll();
    }


    public String depositMoney(AccountDTOold updateBalanceReq) {
        Boolean locked = accountREPO.getOne(updateBalanceReq.getAccountnumber()).isLocked();
        if (locked == true) {
            throw new ApplicationExpetion("AccountEntity is locked");
        }
        if (updateBalanceReq.getBalance() < 0) {
            throw new ApplicationExpetion("Deposit can't be negative");
        }
        Double currentBalance = accountREPO.getOne(updateBalanceReq.getAccountnumber()).getBalance();
        Double newBalance = currentBalance + updateBalanceReq.getBalance();
        //TODO hibernate
        bankRepository.depositMoney(updateBalanceReq.getAccountnumber(), newBalance);
        //transaction recorder
        //TODO hibernate
        bankRepository.transactionRecorderAdd(updateBalanceReq);
        return "Added to account = " + accountREPO.getOne(updateBalanceReq.getAccountnumber()) + "Sum of = " + accountREPO.getOne(updateBalanceReq.getAccountnumber()).getBalance();
    }

    //TODO hibernate
    public String withdrawMoney(AccountDTOold withdrawMoneyReq) {
        Boolean locked = bankRepository.lockCheck(withdrawMoneyReq.getAccountnumber());
        if (locked == true) {
            return "AccountEntity is locked";
        }
        Double currentBalance = accountREPO.getOne(withdrawMoneyReq.getAccountnumber()).getBalance();
        if (withdrawMoneyReq.getBalance() <= currentBalance) {
            Double newBalance = currentBalance - withdrawMoneyReq.getBalance();
            bankRepository.depositMoney(withdrawMoneyReq.getAccountnumber(), newBalance);
            //transaction recorder
            bankRepository.transactionRecorderDeduction(withdrawMoneyReq);
            return "Withdrew " + withdrawMoneyReq.getBalance() + " from = " + withdrawMoneyReq.getAccountnumber() + ". Remaining = " + newBalance;
        }
        return "Can't withdraw, you don't have that much. You have = " + currentBalance;
    }

    //TODO hibernate
    public String transferMoney(AccountDTOold transferReq) {
        Boolean fromLock = bankRepository.lockCheck(transferReq.getAccountnumber());
        Boolean toLock = bankRepository.lockCheck(transferReq.getAccountnumber2());
        if (fromLock == true) {
            return "AccountEntity is locked = " + transferReq.getAccountnumber();
        } else if (toLock == true) {
            return "AccountEntity is locked = " + transferReq.getAccountnumber2();
        } else {
            Double fromAccountCurrentBalance = accountREPO.getOne(transferReq.getAccountnumber()).getBalance();
            if (fromAccountCurrentBalance > transferReq.getBalance()) {
                Double toAccountCurrentBalance = accountREPO.getOne(transferReq.getAccountnumber()).getBalance();
                Double moneyToTransfer = transferReq.getBalance();
                Double fromNewBalance = fromAccountCurrentBalance - moneyToTransfer;
                Double toNewBalance = toAccountCurrentBalance + moneyToTransfer;
                bankRepository.withdrawMoney(transferReq.getAccountnumber(), fromNewBalance);
                bankRepository.depositMoney(transferReq.getAccountnumber2(), toNewBalance);
                //transaction recorder
                bankRepository.transactionRecorderDeduction(transferReq);
                bankRepository.transactionRecorderAdd(transferReq);
                return "Transferred from " + transferReq.getAccountnumber() + " to " + transferReq.getAccountnumber2() + " sum of = " + transferReq.getBalance();
            }
            return "Not enough funds on = " + transferReq.getAccountnumber();
        }
    }

    //TODO hibernate
    public List<TransactionDTO> transactionHistory(TransactionDTO transactionDTO) {
        return bankRepository.getAllTransactions(transactionDTO);
    }

    //TODO hibernate
    public List<TransactionDTO> transactionHistoryCheck(TransactionDTO historyCheckReq) {
        return bankRepository.transactionHistoryCheck(historyCheckReq);
    }


}
