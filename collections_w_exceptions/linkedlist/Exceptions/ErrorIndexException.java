package collections_w_exceptions.linkedlist.Exceptions;

public class ErrorIndexException extends Exception{
    public ErrorIndexException(int index){
        super("Index error" + index);
    }
}
