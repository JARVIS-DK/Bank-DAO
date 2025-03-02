package bank.puthusu.dao;

import bank.puthusu.model.Account;
import bank.puthusu.model.CurrentAccount;
import bank.puthusu.model.LoanAccount;
import bank.puthusu.model.SavingsAccount;
import bank.puthusu.util.DataBaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements AccountDaoInterface {


    public Account createAccount(int customerId,String accountType,double balance) {

        String sql = "INSERT INTO account (customer_id, account_type, balance) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, customerId);
            stmt.setString(2, accountType);
            stmt.setDouble(3, balance);

            Account account = null;

            if(accountType.equals("Savings"))
                account = new SavingsAccount(customerId,balance);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    account.setAccountNumber(rs.getLong(1));
                }
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Account getAccountByAccountNumber(long accountNumber) {
        String sql = "SELECT * FROM account WHERE account_number = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String accountType = rs.getString("account_type");
                if(accountType.equals("Savings"))
                {
                    return new SavingsAccount( rs.getLong("account_number"),rs.getDouble("balance"),
                            rs.getInt("customer_id")
                            );
                } else if (accountType.equals("Current")) {
                    return new CurrentAccount( rs.getLong("account_number"),rs.getDouble("balance"),
                            rs.getInt("customer_id")
                    );
                } else if (accountType.equals("Loan")) {
                    return new LoanAccount( rs.getLong("account_number"),rs.getDouble("balance"),
                            rs.getInt("customer_id")
                    );
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE customer_id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Account account = null;

                String accountType = rs.getString("account_type");
                if(accountType.equals("Savings"))
                {
                    account = new SavingsAccount( rs.getLong("account_number"),rs.getDouble("balance"),
                            rs.getInt("customer_id")
                    );
                } else if (accountType.equals("Current")) {
                    account = new CurrentAccount( rs.getLong("account_number"),rs.getDouble("balance"),
                            rs.getInt("customer_id")
                    );
                } else if (accountType.equals("Loan")) {
                    account = new LoanAccount( rs.getLong("account_number"),rs.getDouble("balance"),
                            rs.getInt("customer_id")
                    );
                }

                if(account!=null)
                    accounts.add(account);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


    public boolean updateBalance(long accountNumber, double newBalance) {
        String sql = "UPDATE account SET balance = ? WHERE account_number = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setLong(2, accountNumber);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteAccount(long accountNumber) {
        String sql = "DELETE FROM account WHERE account_number = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, accountNumber);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getBalanceByAccountNumber(long accountNumber) {
        String sql = "SELECT balance from account WHERE account_number = ?";

        try(Connection con = DataBaseConnection.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1,accountNumber);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
                return Double.parseDouble(rs.getString("balance"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
