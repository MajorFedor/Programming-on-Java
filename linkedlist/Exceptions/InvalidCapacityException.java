package linkedlist.Exceptions;

public class InvalidCapacityException extends NullPointerException {
    public InvalidCapacityException(int cap){
        super("Capacity mus t be  > 0 " + cap);
    }
}
