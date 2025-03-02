package bank.palasu.entity;

import bank.palasu.entity.Account.Account;

import java.util.ArrayList;

public class Bank {
    private String bankName;
    private String bankId;

    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;
    public Bank(){
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public Customer createCustomer(String name,String email,long mobileNo){
        int custId = customers.size() + 1;
        Customer customer = new Customer(custId,name,email,mobileNo);
        customers.add(customer);

        return customer;
    }

    public Customer getCustomer(int custId){
        return customers.stream().filter(c->c.getCustomerId() == custId).findFirst().orElse(null);
    }



    public boolean moneyTransfer(int amount,long accountNumber) {
        Customer to = null;

            for(Account account : accounts) {
                if(account.getAccountNumber() == accountNumber) {

                }
            }

        return false;
    }

}
