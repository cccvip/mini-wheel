/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx;


/**
 * TransactionException.
 * 
 * @author Carl, 2023-08-24 9:41
 */
public class TransactionException extends Exception{
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
}
