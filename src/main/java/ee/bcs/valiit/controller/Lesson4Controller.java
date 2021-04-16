package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Lesson4Controller {

    private List<Account> accounts = new List<Account>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Account> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Account account) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Account> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Account> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Account get(int index) {
            return null;
        }

        @Override
        public Account set(int index, Account element) {
            return null;
        }

        @Override
        public void add(int index, Account element) {

        }

        @Override
        public Account remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Account> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Account> listIterator(int index) {
            return null;
        }

        @Override
        public List<Account> subList(int fromIndex, int toIndex) {
            return null;
        }
    }

    //check if account is present
    public String checkIfAccountExists(Account checkRequest) {
        if (accounts.get(checkRequest) == null) {
            return "Account not present";
        }
        return "Did your thing";
    }

    //CREATE ACCOUNT
    // http://localhost:8080/tasks/Lesson4/createAccount
    @PostMapping("/tasks/Lesson4/createAccount")
    public String createAccount(@RequestBody Account accountRequest) {

        if (accounts.get(checkIfAccountExists(accountRequest.getId()) == null) {
             accounts.set(accountRequest.getId(Account));
            return "Account added = " + accountRequest.getAccountName() + ", with balance of = " + accountRequest.getMoney();
        } else {
            return "Account already present";
        }
    }

    //DEPOSIT MONEY
    // http://localhost:8080/tasks/Lesson4/depositMoney
    @PostMapping("/tasks/Lesson4/depositMoney")
    public String depositMoney(@RequestBody Account depositRequest) {
        if (accounts.get(checkIfAccountExists(depositRequest.getAccountName())) == null) {
            if (depositRequest.getMoney() > 0) {
                accounts.get(depositRequest.getAccountName());
                accounts.put(depositRequest.getAccountName(), depositRequest.getMoney() + currentBalance);
                return "Added " + depositRequest.getMoney() + " to " + depositRequest.getAccountName() + ", new balance is " + accounts.get(depositRequest.getAccountName());
            }
            return "Enter a positive value.";
        }
        return "Can't add to an account which doesn't exist.";
    }

    //GET ALL
    //http://localhost:8080/tasks/Lesson4
    @GetMapping("/tasks/Lesson4/")
    public Set<Map.Entry<String, Account>> getAll() {
        return accounts.entrySet();
    }

    //BALANCE REQUEST
    //http://localhost:8080/tasks/Lesson4/getBalance
    @GetMapping("/tasks/Lesson4/getBalance")
    public String getBalance(@RequestBody Account getBalanceRequest) {
        if (accounts.get(checkIfAccountExists(getBalanceRequest.getAccountName())) == null) {
            return "Account = " + accounts.get(getBalanceRequest.getAccountName());
        }
        return "No account";
    }

    //WITHDRAW REQUEST
    //http://localhost:8080/tasks/Lesson4/withdrawMoney
    @PostMapping("/tasks/Lesson4/withdrawMoney")
    public String withdrawMoney(@RequestBody Account withdrawMoneyRequest) {
        if (accounts.get(checkIfAccountExists(withdrawMoneyRequest.getAccountName())) == null) {
            if (withdrawMoneyRequest.getMoney() <= (accounts.get(withdrawMoneyRequest.getAccountName()))) {
                double currentBalance = accounts.get(withdrawMoneyRequest.getAccountName());
                accounts.put(withdrawMoneyRequest.getAccountName(), currentBalance - withdrawMoneyRequest.getMoney());
                return "Old balance was = " + currentBalance + " withdrew " + withdrawMoneyRequest.getMoney() + " new balance is " + accounts.get(withdrawMoneyRequest.getAccountName());
            } else {
                return "You can't withdraw that much. You have: " + accounts.get(withdrawMoneyRequest.getAccountName());
            }
        }
        return "Can't withdraw from an account which doesn't exist.";
    }


    //TRANSFER REQUEST
    //http://localhost:8080/tasks/Lesson4/transferMoney
    @PostMapping("/tasks/Lesson4/transferMoney")
    public String transferMoney(@RequestBody Account transferMoneyRequest) {

        if ((accounts.get(transferMoneyRequest.getFromAccount()) >= transferMoneyRequest.getMoney())) {
            // take current balance
            double account2Balance = accounts.get(transferMoneyRequest.getFromAccount());
            double balance = accounts.get(transferMoneyRequest.getAccountName());
            // update balance
            double deductedMoney = account2Balance - transferMoneyRequest.getMoney();
            double addedMoney = balance + transferMoneyRequest.getMoney();
            // put new balances back for both
            accounts.put(transferMoneyRequest.getFromAccount(), addedMoney);
            accounts.put(transferMoneyRequest.getAccountName(), deductedMoney);
            return "Transferred from " + transferMoneyRequest.getFromAccount() + " to " + transferMoneyRequest.getAccountName() + ", " + transferMoneyRequest.getMoney() + "\n" +
                    transferMoneyRequest.getFromAccount() + " is " + accounts.get(transferMoneyRequest.getFromAccount()) + "\n" +
                    transferMoneyRequest.getAccountName() + " is " + accounts.get(transferMoneyRequest.getAccountName());
        } else {
            return "Oops, you don't have that much money.";
        }
    }


}
