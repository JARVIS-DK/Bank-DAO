package bank.palasu.entity.Account;

import java.util.Calendar;
import java.util.Date;

public abstract class Account {

    final long accountNumber;
    private int customerId;
    private int balance;
    private String accountType;
    private String status;
    private Date createdAt;
    private int minimumBalance;



    Account(int customerId,int count){
        this.accountNumber = createAccountNumber(customerId,count);
        this.balance = 0;
    }

    Account(int customerId,String accountType,int amount,int count){
        this.customerId = customerId;
        this.accountNumber = createAccountNumber(customerId,count);
        this.accountType = accountType;
        this.balance = amount;
    }

    private long createAccountNumber(int customerId,int count) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return Long.parseLong(String.format("%d%04d%03d",year,customerId,count));
    }

    public long getAccountNumber() {
         return accountNumber;
    }
    public String getAccountType(){
        return accountType;
    }
    public void setBalance(int amount) {
        this.balance = amount;
    }
    public int getBalance() {
        return balance;
    }



    public abstract boolean withdraw(int amount);
    public abstract boolean deposit(int amount);
    public abstract boolean transfer(int amount,long accountNumber);

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


}
