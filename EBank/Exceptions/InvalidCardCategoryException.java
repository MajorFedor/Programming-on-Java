package EBank.Exceptions;

public class InvalidCardCategoryException extends IllegalArgumentException {
    public InvalidCardCategoryException(String m){
        super(m);
    }
}