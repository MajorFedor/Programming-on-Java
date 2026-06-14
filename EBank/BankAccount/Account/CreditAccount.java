package EBank.BankAccount.Account;

import EBank.Exceptions.BlockedAccountException;
import EBank.Exceptions.CreditlimitExceedException;
import EBank.Exceptions.InvalidAmountException;

public class CreditAccount extends BankAccount{
    private double interestRate;
    private double creditLimit;

    public CreditAccount(double balance, double inreestRate, double creditLimit){
        super(balance);
        this.interestRate = inreestRate;
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(double amount){
        if(isBlocked){
            throw new BlockedAccountException("Account blocked");
        }
        if(amount < 0){
            throw new InvalidAmountException("Amount must be positive");
        }
        if(amount > balance + creditLimit){
            throw new CreditlimitExceedException("Credit limit exceed!");
        }
        this.balance -= amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", creditLimit=" + creditLimit +
                ", interestRate=" + interestRate +
                ", blocked=" + isBlocked +
                "}";
    }
}
