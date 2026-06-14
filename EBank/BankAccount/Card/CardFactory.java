package EBank.BankAccount.Card;

import EBank.Exceptions.InvalidCardCategoryException;

public class CardFactory {
    public CardDetails createCard(CardCategory cardCategory, String expiry){
        switch (cardCategory) {
            case STANDART:
                return new StandardCard(expiry);
            case GOLD:
                return new GoldCard(expiry);
            case PLATINUM:
                return new PlatinumCard(expiry);
            default:
                throw new InvalidCardCategoryException("Invalid card category " + cardCategory);
        }
    }
}
