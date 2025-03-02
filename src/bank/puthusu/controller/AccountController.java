package bank.puthusu.controller;

import bank.puthusu.dao.AccountDAO;
import bank.puthusu.model.Account;

import java.util.List;

public class AccountController {
    private final AccountDAO accountDAO;

    public AccountController() {
        this.accountDAO = new AccountDAO();
    }

    public Account createAccount(int customerId, String accountType, int balance) {
        return accountDAO.createAccount(customerId, accountType, balance);
    }

    public double getBalanceByAccountNumber(long accountNumber){
        return accountDAO.getBalanceByAccountNumber(accountNumber);
    }

    public Account getAccountByAccountNumber(long accountNumber) {
        return accountDAO.getAccountByAccountNumber(accountNumber);
    }

    public List<Account> getAccountsByCustomerId(int customerId) {
        return accountDAO.getAccountsByCustomerId(customerId);
    }

    public boolean deposit(long accountNumber, int amount) {
        Account account = accountDAO.getAccountByAccountNumber(accountNumber);
        if (account == null || amount <= 0) {
            return false;
        }
        double newBalance = account.getBalance() + amount;
        return accountDAO.updateBalance(accountNumber, newBalance);
    }

    public boolean withdraw(long accountNumber, int amount) {
        Account account = accountDAO.getAccountByAccountNumber(accountNumber);
        if (account == null || amount <= 0 || account.getBalance() < amount) {
            return false;
        }
        double newBalance = account.getBalance() - amount;
        return accountDAO.updateBalance(accountNumber, newBalance);
    }

    public boolean transfer(long fromAccountNumber, long toAccountNumber, int amount) {
        Account fromAccount = accountDAO.getAccountByAccountNumber(fromAccountNumber);
        Account toAccount = accountDAO.getAccountByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null || amount <= 0 || fromAccount.getBalance() < amount) {
            return false;
        }

        double newFromBalance = fromAccount.getBalance() - amount;
        double newToBalance = toAccount.getBalance() + amount;

        return accountDAO.updateBalance(fromAccountNumber, newFromBalance) &&
                accountDAO.updateBalance(toAccountNumber, newToBalance);
    }

    public boolean deleteAccount(long accountNumber) {
        return accountDAO.deleteAccount(accountNumber);
    }
}
