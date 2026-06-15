package EBank.BankAccount.Account.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

import EBank.Exceptions.InvalidAmountException;

public class Transaction implements Serializable{

    private static long nextId = 1;
    private final long id;
    private final String fromAccount;
    private final String toAccount;
    private final double amount;
    private final LocalDateTime createdAt;
    private TransactionStatus status;
    private String description;

    public Transaction(String fromAccount, String toAccount, double amount, String description){
        this.id = nextId++;
        this.fromAccount= fromAccount;
        this.toAccount = toAccount;
        if ( amount <= 0) {
            throw new InvalidAmountException("Amount must be positive: " + amount);
        }
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        if(!description.isBlank()){
            this.description = description;
        } else {
            this.description = "-";
        }
    }

    public void complete() {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException("Only pending transactions can be complete");
        }
        this.status = TransactionStatus.COMPLETE;
    }

    public void cancel() {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException("Only pending transactions can be cancel");
        }
        this.status = TransactionStatus.CANCEL;
    }

    public long getId(){
        return id;
    }
    public static long getNextId() {
        return nextId;
    }

    public String getFromAccount(){
        return fromAccount;
    }

    public String getToAccount(){
        return toAccount;
    }

    public double getAmount(){
        return amount;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public String getDescription(){
        return description;
    }

    public static void setNextId(long nextId) {
        Transaction.nextId = nextId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", from='" + fromAccount + '\'' +
                ", to =" + toAccount + '\'' +
                ", amount =" + amount + '\'' +
                ", created =" + createdAt + '\'' +
                ", description =" + description + '\'' +
                '}';
    }
    
}
