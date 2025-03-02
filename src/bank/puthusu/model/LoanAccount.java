package bank.puthusu.model;

public class LoanAccount extends Account {

    public LoanAccount(long accountNumber, double balance, int customerId) {
        super(accountNumber, "Loan", balance, customerId);
    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }
}
