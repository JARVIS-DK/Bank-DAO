package bank.palasu.entity.Account;

public class SavingsAccount extends Account {

    final int minimumBalance = 500;

    public SavingsAccount(int customerId,int count){
        super(customerId,count);
    }

    public SavingsAccount(int customerId,String accountType, int amount, int count) {
        super(customerId,accountType, amount, count);
    }

    @Override
    public boolean withdraw(int amount) {
        if(this.getBalance() > minimumBalance  + amount )
        {
            this.setBalance(this.getBalance() - amount);
            return true;
        }

        return false;
    }

    @Override
    public boolean deposit(int amount) {

        if(amount > 0) {
            this.setBalance(this.getBalance() + amount);
            return true;
        }

        return false;
    }

    @Override
    public boolean transfer(int amount, long accountNumber) {
        if(this.getBalance()  > minimumBalance + amount)
        {
            this.setBalance(this.getBalance() - amount);

            return true;
        }

        return false;
    }


}
