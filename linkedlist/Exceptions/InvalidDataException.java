
package linkedlist.Exceptions;

import java.util.InputMismatchException;

public class InvalidDataException extends InputMismatchException{
    public InvalidDataException(Integer data){
        super("Invalid Data " + data);
    }
}
