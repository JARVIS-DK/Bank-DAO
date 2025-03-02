package bank.puthusu.controller;

import bank.puthusu.model.Account;
import bank.puthusu.model.Customer;

import bank.puthusu.view.BankView;

import java.util.List;
import java.util.Scanner;

public class BankController {
    private final CustomerController customerController;
    private final AccountController accountController;
    private final BankView bankView;
    private final Scanner scanner;

    public BankController() {
        this.customerController= new CustomerController();
        this.accountController = new AccountController();
        this.bankView = new BankView();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            bankView.displayMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> createNewCustomer();
                case 2 -> existingCustomerMenu();
                case 3 -> exitApplication();
                default -> bankView.displayInvalidChoice();
            }
        }
    }

    private void createNewCustomer() {
        bankView.promptCustomerDetails();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Mobile Number: ");
        long mobileNo = Long.parseLong(scanner.nextLine());

        Customer customer = customerController.createCustomer(name, email, mobileNo);
        bankView.displayCustomerCreated(customer);
    }

    private void existingCustomerMenu() {
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer customer = customerController.getCustomerById(customerId);

        if (customer == null) {
            bankView.displayCustomerNotFound();
            return;
        }

        while (true) {
            bankView.displayCustomerMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> createAccount(customerId);
                case 2 -> manageAccounts(customerId);
                case 3 -> deleteCustomer(customerId);
                case 4 -> { return; }
                default -> bankView.displayInvalidChoice();
            }
        }
    }

    private void createAccount(int customerId) {
        bankView.displayAccountTypes();
        int accountChoice = Integer.parseInt(scanner.nextLine());

        String accountType = switch (accountChoice) {
            case 1 -> "Savings";
            case 2 -> "Current";
            case 3 -> "Loan";
            default -> null;
        };

        if (accountType == null) {
            bankView.displayInvalidChoice();
            return;
        }

        System.out.print("Enter Initial Deposit Amount: ");
        int balance = Integer.parseInt(scanner.nextLine());

        Account account = accountController.createAccount(customerId, accountType, balance);
        bankView.displayAccountCreated(account);
    }

    private void manageAccounts(int customerId) {
        List<Account> accounts = accountController.getAccountsByCustomerId(customerId);
        if (accounts.isEmpty()) {
            bankView.displayNoAccounts();
            return;
        }

        bankView.displayAccounts(accounts);
        System.out.print("Select an Account by Number: ");
        int accountChoice = Integer.parseInt(scanner.nextLine());

        if (accountChoice < 1 || accountChoice > accounts.size()) {
            bankView.displayInvalidChoice();
            return;
        }

        Account selectedAccount = accounts.get(accountChoice - 1);
        manageAccountOperations(selectedAccount);
    }

    private void manageAccountOperations(Account account) {
        while (true) {
            bankView.displayAccountOperations();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> checkBalance(account);
                case 2 -> withdrawAmount(account);
                case 3 -> depositAmount(account);
                case 4 -> transferAmount(account);
                case 5 -> deleteAccount(account.getAccountNumber());
                case 6 -> { return; }
                default -> bankView.displayInvalidChoice();
            }
        }
    }

    private void checkBalance(Account account){
        System.out.println("Your Current Balance : " + accountController.getBalanceByAccountNumber(account.getAccountNumber()));
    }

    private void withdrawAmount(Account account) {
        System.out.print("Enter Amount to Withdraw: ");
        int amount = Integer.parseInt(scanner.nextLine());

        if (accountController.withdraw(account.getAccountNumber(), amount)) {
            bankView.displayTransactionSuccess("Withdrawal");
        } else {
            bankView.displayTransactionFailed("Withdrawal");
        }
    }

    private void depositAmount(Account account) {
        System.out.print("Enter Amount to Deposit: ");
        int amount = Integer.parseInt(scanner.nextLine());

        if (accountController.deposit(account.getAccountNumber(), amount)) {
            bankView.displayTransactionSuccess("Deposit");
        } else {
            bankView.displayTransactionFailed("Deposit");
        }
    }

    private void transferAmount(Account fromAccount) {
        System.out.print("Enter Destination Account ID: ");
        long toAccountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Amount to Transfer: ");
        int amount = Integer.parseInt(scanner.nextLine());

        if (accountController.transfer(fromAccount.getAccountNumber(), toAccountId, amount)) {
            bankView.displayTransactionSuccess("Transfer");
        } else {
            bankView.displayTransactionFailed("Transfer");
        }
    }

    private void deleteAccount(long accountNumber){
        accountController.deleteAccount(accountNumber);
    }

    private void deleteCustomer(int customerId) {
        if (customerController.deleteCustomer(customerId)) {
            bankView.displayCustomerDeleted();
        } else {
            bankView.displayActionFailed("delete customer");
        }
    }

    private void exitApplication() {
        bankView.displayExitMessage();
        System.exit(0);
    }
}
