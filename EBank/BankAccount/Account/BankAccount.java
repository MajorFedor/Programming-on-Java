package EBank.BankAccount.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import EBank.BankAccount.Account.Transaction.Transaction;
import EBank.Exceptions.BlockedAccountException;
import EBank.Exceptions.InvalidAmountException;
import EBank.Memento.BankAccountMemento;
import EBank.Observer.BankEvent;
import EBank.Observer.EventManager;

public abstract class BankAccount implements Serializable{

    private static long nextId = 1;
    public EventManager events = new EventManager();

    protected long id;
    protected double balance;
    protected boolean isBlocked;
    protected List<Transaction> transactions;

    public BankAccount(double balance) {
        this.id = nextId++;
        this.balance = balance;
        this.isBlocked = false;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if(isBlocked){
            throw new BlockedAccountException("Account blocked");
        }
        if(amount < 0 ){
            throw new InvalidAmountException("Amount must be positive");
        }
        this.balance += amount;
        events.notify(BankEvent.DEPOSIT, "Amount: " + amount);
    }

    public void withdraw(double amount) {
        if (isBlocked) {
            throw new BlockedAccountException("Account blocked");
        }
        if (amount < 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        this.balance -= amount;
        events.notify(BankEvent.WITHDRAW, "Ammount: " + amount);
    }

    public void block() {
        this.isBlocked = true;
        events.notify(BankEvent.BLOCK, "");
    }

    public void unblock() {
        this.isBlocked = false;
        events.notify(BankEvent.UNBLOCK, "");
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public BankAccountMemento save() {
        return new BankAccountMemento(balance, isBlocked);
    }

    public void restore(BankAccountMemento memento) {
        this.balance = memento.getBalance();
        this.isBlocked = memento.isBlocked();
    }

    public long getId(){
        return id; 
    }

    public static long getNextId() {
        return nextId;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public static void setNextId(long nextId) {
        BankAccount.nextId = nextId;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id + 
                ", balance=" + balance + 
                ", blocked=" + isBlocked + 
                "}";
    }
}