package bank.puthusu.controller;

import bank.puthusu.dao.CustomerDAO;
import bank.puthusu.model.Customer;

import java.util.List;

public class CustomerController {
    private final CustomerDAO customerDAO;

    public CustomerController() {
        this.customerDAO = new CustomerDAO();
    }

    public Customer createCustomer(String name, String email, long mobileNo) {
        return customerDAO.createCustomer(name, email, mobileNo);
    }

    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public boolean updateCustomer(int customerId, String name, String email, long mobileNo) {
        return customerDAO.updateCustomer(customerId, name, email, mobileNo);
    }

    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
}
