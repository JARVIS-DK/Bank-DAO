package bank.puthusu.model;

public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500;

    public SavingsAccount(long accountNumber, double balance, int customerId) {
        super(accountNumber, "Savings", balance, customerId);
    }

    public SavingsAccount(int customerId,double balance){
        super("Savings",balance,customerId);

    }

    @Override
    public boolean withdraw(double amount) {
        if (getBalance() - amount >= MINIMUM_BALANCE) {
            return super.withdraw(amount);
        }
        return false;
    }
}
