package ee.bcs.valiit.service;

import ee.bcs.valiit.exeptionhandler.ApplicationExpetion;
import ee.bcs.valiit.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private AccountREPO accountREPO;
    @Autowired
    private TransactionREPO transactionREPO;

    public String createAccount(AccountDAO createReq) {
        if (createReq.getBalance() < 0) {
            throw new ApplicationExpetion("Deposit can't be negative");
        }
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(createReq.getAccountNumber());
        account.setBalance(createReq.getBalance());
        accountREPO.save(account);

        //transaction recorder
        TransactionEntity transfer = new TransactionEntity();
        transfer.setFromAccount(createReq.getAccountNumber());
        transfer.setTransfer(createReq.getBalance());
        transfer.setDateTime(LocalDateTime.now());
        transactionREPO.save(transfer);
        return "Account added = " + createReq.getAccountNumber() + ", with balance of = " + createReq.getBalance();
    }


    public String getBalanceReq(String accountNr) {

        AccountEntity account = accountREPO.getOne(accountNr);
        return "Balance of " + account.getAccountNumber() + " is = " + accountREPO.getOne(account.getAccountNumber()).getBalance();
    }


    public List<AccountEntity> allAccounts() {
        return accountREPO.findAll();
    }


    public String depositMoney(AccountDAO updateBalanceReq) {
        Boolean locked = accountREPO.getOne(updateBalanceReq.getAccountNumber()).isLocked();
        if (locked == true) {
            throw new ApplicationExpetion("Account is locked");
        } else if (updateBalanceReq.getBalance() < 0) {
            throw new ApplicationExpetion("Deposit can't be negative");
        } else {
            Double currentBalance = accountREPO.getOne(updateBalanceReq.getAccountNumber()).getBalance();
            Double newBalance = currentBalance + updateBalanceReq.getBalance();
            AccountEntity account = new AccountEntity();
            account.setAccountNumber(updateBalanceReq.getAccountNumber());
            account.setBalance(newBalance);
            accountREPO.save(account);

            //transaction recorder
            TransactionEntity transfer = new TransactionEntity();
            transfer.setFromAccount(updateBalanceReq.getAccountNumber());
            transfer.setTransfer(updateBalanceReq.getBalance());
            transfer.setDateTime(LocalDateTime.now());
            transactionREPO.save(transfer);
            return "Added to account = " + updateBalanceReq.getAccountNumber() + " Sum of = " + updateBalanceReq.getBalance();
        }
    }


    public String withdrawMoney(AccountDAO withdrawMoneyReq) {
        Boolean locked = accountREPO.getOne(withdrawMoneyReq.getAccountNumber()).isLocked();
        if (locked == true) {
            return "Account is locked";
        }
        Double currentBalance = accountREPO.getOne(withdrawMoneyReq.getAccountNumber()).getBalance();
        if (withdrawMoneyReq.getBalance() <= currentBalance) {
            Double newBalance = currentBalance - withdrawMoneyReq.getBalance();
            AccountEntity account = new AccountEntity();
            account.setAccountNumber(withdrawMoneyReq.getAccountNumber());
            account.setBalance(newBalance);
            accountREPO.save(account);

            //transaction recorder
            TransactionEntity transfer = new TransactionEntity();
            transfer.setFromAccount(withdrawMoneyReq.getAccountNumber());
            transfer.setDeduction(withdrawMoneyReq.getBalance());
            transfer.setDateTime(LocalDateTime.now());
            transactionREPO.save(transfer);
            return "Withdrew " + withdrawMoneyReq.getBalance() + " from = " + withdrawMoneyReq.getAccountNumber() + ". Remaining = " + newBalance;
        }
        return "Can't withdraw, you don't have that much. You have = " + currentBalance;
    }


    public String transferMoney(TransferDAO transferReq) {
        Boolean fromLock = accountREPO.getOne(transferReq.getAccountNumber()).isLocked();
        Boolean toLock = accountREPO.getOne(transferReq.getAccountNumber2()).isLocked();
        if (fromLock == true) {
            return "From Account is locked = " + transferReq.getAccountNumber();
        } else if (toLock == true) {
            return "To Account is locked = " + transferReq.getAccountNumber2();
        } else {
            Double fromAccountCurrentBalance = accountREPO.getOne(transferReq.getAccountNumber()).getBalance();
            if (fromAccountCurrentBalance >= transferReq.getBalance()) {
                Double toAccountCurrentBalance = accountREPO.getOne(transferReq.getAccountNumber()).getBalance();
                Double moneyToTransfer = transferReq.getBalance();
                Double fromNewBalance = fromAccountCurrentBalance - moneyToTransfer;
                Double toNewBalance = toAccountCurrentBalance + moneyToTransfer;
                AccountEntity account = new AccountEntity();
                account.setAccountNumber(transferReq.getAccountNumber());
                account.setBalance(fromNewBalance);
                accountREPO.save(account);
                account.setAccountNumber(transferReq.getAccountNumber2());
                account.setBalance(toNewBalance);
                accountREPO.save(account);

                //transaction recorder
                TransactionEntity transfer = new TransactionEntity();
                transfer.setFromAccount(transferReq.getAccountNumber());
                transfer.setToAccount(transferReq.getAccountNumber2());
                transfer.setDeduction(transferReq.getBalance());
                transfer.setTransfer(transferReq.getBalance());
                transfer.setDateTime(LocalDateTime.now());
                transactionREPO.save(transfer);

                return "Transferred from " + transferReq.getAccountNumber() + " to " + transferReq.getAccountNumber2() + " sum of = " + transferReq.getBalance();
            }
            return "Not enough funds on = " + transferReq.getAccountNumber();
        }
    }

    //TODO hibernate
    public List<TransactionEntity> allHistory() {
        return transactionREPO.findAll();
    }

    //TODO hibernate
    public List<TransactionEntity> historyCheck(String accountNr) {
        return transactionREPO.findAllByFromAccountContaining(accountNr);
    }

}
