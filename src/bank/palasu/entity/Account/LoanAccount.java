package bank.palasu.entity.Account;

public class LoanAccount extends Account{


    public LoanAccount(int customerId,int count){
        super(customerId,count);
    }

    public LoanAccount(int customerId,String accountType, int amount, int count) {
        super(customerId,accountType, amount, count);
    }

    @Override
    public boolean withdraw(int amount) {
        return false;
    }

    @Override
    public boolean deposit(int amount) {
        return false;
    }

    @Override
    public boolean transfer(int amount, long accountNumber) {
        return false;
    }

}
