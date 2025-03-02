package bank.palasu.entity.Account;

public class CurrentAccount extends Account{




    public CurrentAccount(int customerId,int count){
        super(customerId,count);
    }

    public CurrentAccount(int customerId,String accountType, int amount, int count) {
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
