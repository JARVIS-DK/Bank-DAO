package bank.puthusu.model;

public abstract class Account {
    private long accountNumber;
    private String accountType;
    private double balance;
    private int customerId;

    public Account(String accountType,double balance,int customerId)
    {
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
    }
    public Account(long accountNumber, String accountType, double balance, int customerId) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
    }

    public void setAccountNumber(long accountNumber){
        this.accountNumber = accountNumber;
    }
    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(double amount, Account recipient) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        }
        return false;
    }
}
