package bank.puthusu.dao;

import bank.puthusu.model.Account;

import java.util.List;

public interface AccountDaoInterface {
    public Account createAccount(int customerId, String accountType, double balance);
    public Account getAccountByAccountNumber(long accountNumber);
    public List<Account> getAccountsByCustomerId(int customerId);
    public boolean updateBalance(long accountNumber, double newBalance);
    public boolean deleteAccount(long accountNumber);
    public double getBalanceByAccountNumber(long accountNumber);
}
