package bank.puthusu.dao;

import bank.puthusu.model.Customer;

import java.util.List;

public interface CustomerDaoInterface {
    public Customer createCustomer(String name, String email, long mobileNo);
    public Customer getCustomerById(int customerId);
    public List<Customer> getAllCustomers();
    public boolean updateCustomer(int customerId,String name,String email,long mobileNo);
    public boolean deleteCustomer(int customerId);
}
