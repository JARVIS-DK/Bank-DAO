package bank.puthusu.dao;

import bank.puthusu.model.Customer;
import bank.puthusu.util.DataBaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerDaoInterface {

    private final Connection connection;

    public CustomerDAO() {
        connection = DataBaseConnection.getConnection();
    }


    public Customer createCustomer(String name,String email,long mobileNo) {

        String sql = "INSERT INTO Customer (name, email, mobile_no) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setLong(3, mobileNo);

            Customer customer = new Customer(name,email,mobileNo);


            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    customer.setCustomerId(rs.getInt(1)); // Set generated customer ID
                }
                return customer;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error Adding Customer: " + e.getMessage());
        }
        return null;
    }


    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getLong("mobile_no")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error Fetching Customer: " + e.getMessage());
        }
        return null;
    }


    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getLong("mobile_no")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error Fetching Customers: " + e.getMessage());
        }
        return customers;
    }


    public boolean updateCustomer(int customerId,String name,String email,long mobileNo) {
        String sql = "UPDATE Customer SET name = ?, email = ?, mobile_no = ? WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setLong(3, mobileNo);
            stmt.setInt(4, customerId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error Updating Customer: " + e.getMessage());
        }
        return false;
    }


    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error Deleting Customer: " + e.getMessage());
        }
        return false;
    }


}
