package EBank.Memento;

public class BankAccountMemento {

    private final double balance;
    private final boolean isBlocked;

    public BankAccountMemento(double balance, boolean isBlocked) {
        this.balance = balance;
        this.isBlocked = isBlocked;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}