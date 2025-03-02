package bank.puthusu.view;

import bank.puthusu.model.Account;
import bank.puthusu.model.Customer;

import java.util.List;

public class BankView {

    public void displayMainMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("-".repeat(17) + " Bank " + "-".repeat(17));
        System.out.println("-".repeat(40));
        System.out.println("1. New Customer");
        System.out.println("2. Existing Customer");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayCustomerMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("-".repeat(15) + " Customer " + "-".repeat(15));
        System.out.println("-".repeat(40));
        System.out.println("1. Create Account");
        System.out.println("2. Manage Accounts");
        System.out.println("3. Delete Account");
        System.out.println("4. Go Back");
        System.out.print("Enter your choice: ");
    }

    public void displayAccountTypes() {
        System.out.println("\nAccount Types:");
        System.out.println("\t1. Savings Account");
        System.out.println("\t2. Current Account");
        System.out.println("\t3. Loan Account");
        System.out.print("Enter your choice: ");
    }

    public void displayAccountOperations() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("-".repeat(10) + " Account Operations " + "-".repeat(10));
        System.out.println("-".repeat(40));
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Deposit Amount");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Go Back");
        System.out.print("Enter your choice: ");
    }

    public void promptCustomerDetails() {
        System.out.println("\nEnter Customer Details:");
    }

    public void displayCustomerCreated(Customer customer) {
        System.out.println("\n✅ Customer Created Successfully!");
        System.out.println("Customer ID: " + customer.getCustomerId());
    }

    public void displayCustomerNotFound() {
        System.out.println("\n❌ Customer not found! Please check the ID.");
    }

    public void displayInvalidChoice() {
        System.out.println("\n❌ Invalid choice! Please try again.");
    }

    public void displayNoAccounts() {
        System.out.println("\n❌ No accounts found for this customer.");
    }

    public void displayAccounts(List<Account> accounts) {
        System.out.println("\nYour Accounts:");
        int index = 1;
        for (Account account : accounts) {
            System.out.println(index++ + ". " + account.getAccountNumber() + " - " + account.getAccountType() +
                    " (Balance: " + account.getBalance() + ")");
        }
    }

    public void displayAccountCreated(Account account) {
        System.out.println("\n✅ Account Created Successfully!");
        System.out.println("Account ID: " + account.getAccountNumber());
    }

    public void displayTransactionSuccess(String operation) {
        System.out.println("\n✅ " + operation + " Successful!");
    }

    public void displayTransactionFailed(String operation) {
        System.out.println("\n❌ " + operation + " Failed! Please check your balance or account details.");
    }

    public void displayCustomerDeleted() {
        System.out.println("\n✅ Customer deleted successfully.");
    }

    public void displayActionFailed(String action) {
        System.out.println("\n❌ Failed to " + action + ". Please try again.");
    }

    public void displayExitMessage() {
        System.out.println("\n🔴 Exiting the application. Thank you for using our bank system!");
    }
}
