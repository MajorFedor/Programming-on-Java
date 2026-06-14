package EBank.BankAccount.Account;

import EBank.BankAccount.Card.CardDetails;

public class CardAccount extends BankAccount {

    private CardDetails card;

    public CardAccount(double balance, CardDetails card) {
        super(balance);
        this.card = card;
    }

    public CardDetails getCard() {
        return card;
    }

    public void setCard(CardDetails card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "CardAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", card=" + card +
                ", blocked=" + isBlocked +
                "}";
    }
}