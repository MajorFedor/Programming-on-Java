package EBank.BankAccount.Account;

public class DepositAccount extends BankAccount {

    private double interestRate;

    public DepositAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        this.balance += this.balance *(interestRate /100);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "DepositAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", blocked=" + isBlocked +
                "}";
    }
}