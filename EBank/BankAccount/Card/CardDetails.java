package EBank.BankAccount.Card;

import java.io.Serializable;

public class CardDetails implements Serializable{
    
    protected static long nextCardNumber = 5374000000000000l;
    protected String cardNumber;
    protected String cardExpiry;
    protected long creditLimit;
    protected double cashback;
    protected CardCategory cardCategory;

    public CardDetails(String cardExpiry, CardCategory cardCategory, long creditLimit, double cashback){
        this.cardNumber = String.valueOf(nextCardNumber++);
        this.cardExpiry = cardExpiry;
        this.cardCategory = cardCategory;
        this.creditLimit = creditLimit;
        this.cashback = cashback;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public String getCardExpiry(){
        return cardExpiry;
    }

    public long getCreditLimit(){
        return creditLimit;
    }
    
    public double getCashback(){
        return cashback;
    }

    public CardCategory getCardCategory(){
        return cardCategory;
    }

    public void setCardExpiry(String expiry){
        this.cardExpiry = expiry;
    }

    public void setCardCategory(CardCategory cardCategory){
        this.cardCategory = cardCategory;
    }
}