package bank.palasu;

import bank.palasu.entity.Account.Account;
import bank.palasu.entity.Bank;
import bank.palasu.entity.Customer;
import bank.palasu.exception.AccountException;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        Bank bank = new Bank();

        while(true){

            System.out.println("-".repeat(40));
            System.out.println("-".repeat(17) + " Bank " + "-".repeat(17));
            System.out.println("-".repeat(40));

            System.out.println("1. New Customer\n2. Existing Customer\n3. Exit");

            System.out.print("Enter your choice : ");
            int userChoice = Integer.parseInt(sc.nextLine());


            if(userChoice == 1)
            {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Mobile Number: ");
                long mobileNo = Long.parseLong(sc.nextLine());
                Customer customer = bank.createCustomer(name, email, mobileNo);
                System.out.println("Customer Created Successfully! ID: " + customer.getCustomerId());
            }
            else if (userChoice == 2) {

                System.out.print("Enter Customer ID: ");
                int customerId = Integer.parseInt(sc.nextLine());
                Customer customer = bank.getCustomer(customerId);
                if (customer == null) {
                    System.out.println("Customer not found!");
                    continue;
                }

                while(true){

                    System.out.println("-".repeat(40));
                    System.out.println("-".repeat(15) + " Customer " + "-".repeat(15));
                    System.out.println("-".repeat(40));

                    System.out.println("1. Create Account\n2. Manage Accounts \n3. Delete Account\n4. Go Back");

                    System.out.print("Enter you choice : ");
                    int operationChoice = Integer.parseInt(sc.nextLine());


                    if (operationChoice == 1) {
                        System.out.println("-".repeat(40));
                        System.out.println("-".repeat(10) + " Create Account " + "-".repeat(10));
                        System.out.println("-".repeat(40));

                        System.out.println("\nAccount Type: \n\t1. Savings Account\n\t2. Current Account\n\t3. Loan Account\n4. Go Back");
                        System.out.print("Enter your choice : ");

                        int accountTypeChoice = Integer.parseInt(sc.nextLine());

                        String accountType = switch (accountTypeChoice){
                            case 1 -> "Savings";
                            case 2 -> "Current";
                            case 3 -> "Loan";
                            default -> null;
                        };

                        if(accountType!=null) {
                            System.out.print("Enter the amount for initial deposit : ");
                            int balance = Integer.parseInt(sc.nextLine());
                            try {
                                Account account = customer.createAccount(accountType,balance);
                                System.out.println("Account Created! Number : " + account.getAccountNumber());
                            } catch (AccountException _) {

                            }
                        }
                    }
                    else if (operationChoice == 2) {
                        ArrayList<Account> accounts = customer.getAccounts();

                        if(accounts.isEmpty()){
                            System.out.println("No Accounts Found..!!");
                            continue;
                        }

                        System.out.println("Your Accounts : ");
                        int index = 1;
                        for(Account acc : accounts)
                            System.out.println((index++) + ". " + acc.getAccountNumber() + " - " + acc.getAccountType());

                        System.out.print("Enter you choice : ");

                        int accountChoice = Integer.parseInt(sc.nextLine());


                        Account account = accounts.get(accountChoice-1);

                        while (true){

                            System.out.println("-".repeat(40));
                            System.out.println("-".repeat(10) + " Account Operation " + "-".repeat(10));
                            System.out.println("-".repeat(40));

                            System.out.println("\n1. Withdraw Amount\n2. Deposit Amount\n3. Transfer Account\n4. Go Back");

                            System.out.print("Enter your choice : ");
                            int accountOperationChoice = Integer.parseInt(sc.nextLine());

                            if (accountOperationChoice == 1) {
                                //withdraw
                                int amount;
                                while(true){
                                    System.out.print("Enter the amount to Withdraw : ");
                                    amount = Integer.parseInt(sc.nextLine());
                                    if (amount < 0) {
                                        System.out.println("Enter a valid amount..!");
                                    }
                                    else{
                                        break;
                                    }
                                }

                                if(account.withdraw(amount)) {
                                    System.out.println("✅ Withdraw successfully... ☺️");
                                }
                                else {
                                    System.out.println("❌ Can't withdraw amount 😔");
                                }

                            } else if (accountOperationChoice == 2) {
                                //Deposit
                                int amount;
                                while(true){
                                    System.out.print("Enter the amount to Deposit : ");
                                    amount = Integer.parseInt(sc.nextLine());
                                    if (amount < 0) {
                                        System.out.println("Enter a valid amount..!");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                if(account.deposit(amount)){
                                    System.out.println("✅ Deposit successful..");
                                }
                                else{
                                    System.out.println("❌ Can't deposit amount ");
                                }

                            } else if (accountOperationChoice == 3) {
                                //transfer


                            } else if (accountOperationChoice == 4) {
                                //back
                                break;
                            }
                        }


                    }
                    else if (operationChoice == 3) {

                        ArrayList<Account> accounts = customer.getAccounts();

                        if(accounts.isEmpty()){
                            System.out.println("No Accounts Found..!!");
                            continue;
                        }

                        System.out.println("Your Accounts : ");
                        int index = 1;
                        for(Account acc : accounts)
                            System.out.println((index++) + ". " + acc.getAccountNumber() + " - " + acc.getAccountType());

                        System.out.print("Enter you choice : ");

                        int accountChoice = Integer.parseInt(sc.nextLine());

                        if(customer.deleteAccount(accountChoice)){
                            System.out.println("Account deleted successfully..!!");
                        }
                        else{
                            System.out.println("Can't delete the account..!");
                        }

                    }
                    else if (operationChoice == 4) {
                        break;
                    }
                }


            }
            else if (userChoice == 3) {
                System.out.println("Exiting..!!");
                break;
            }
            else{
                System.out.println("Enter a valid option..!!");
            }


        }


    }
}

