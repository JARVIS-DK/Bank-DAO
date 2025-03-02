package bank.palasu.entity;

import bank.palasu.entity.Account.Account;
import bank.palasu.entity.Account.CurrentAccount;
import bank.palasu.entity.Account.LoanAccount;
import bank.palasu.entity.Account.SavingsAccount;
import bank.palasu.exception.AccountException;

import java.util.ArrayList;

public class Customer {
    private final int customerId;
    private String customerName;
    private String email;
    private long mobileNo;

    private ArrayList<Account> accounts;


    public Customer(int customerId,String customerName, String email,long mobileNo){
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.mobileNo = mobileNo;
        accounts = new ArrayList<>();
    }

    public int getCustomerId(){
        return this.customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }


    public Account createAccount(String type,int balance) throws AccountException {
        Account account = null;

        if(type.equals("Savings")) {
            account = new SavingsAccount(customerId,"Savings Account",balance,accounts.size()+1);
        }
        else if(type.equals("Current")) {
            account = new CurrentAccount(customerId,"Current Account",balance,accounts.size()+1);
        }
        else if(type.equals("Loan")){
            account = new LoanAccount(customerId,"Loan Account",balance,accounts.size()+1);
        }


        if(account!=null){
            accounts.add(account);
            return account;
        }
        else{
            throw new AccountException("Can't Create Account..!!");
        }
    }


    public boolean deleteAccount(int number){
        try{
            accounts.remove(number);
            return false;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                ", accounts=" + accounts +
                '}';
    }
}
