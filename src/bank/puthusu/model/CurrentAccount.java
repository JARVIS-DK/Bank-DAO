package bank.puthusu.model;

public class CurrentAccount extends Account {

    public CurrentAccount(long accountNumber, double balance, int customerId) {
        super(accountNumber, "Current", balance, customerId);
    }
}
