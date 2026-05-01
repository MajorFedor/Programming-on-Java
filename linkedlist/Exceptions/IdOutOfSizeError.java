package linkedlist.Exceptions;

public class IdOutOfSizeError extends IndexOutOfBoundsException{
    public IdOutOfSizeError(Integer id){
        
        super("Invalid id" + id);
    }
}
