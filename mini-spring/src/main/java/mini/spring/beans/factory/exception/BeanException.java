package mini.spring.beans.factory.exception;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class BeanException extends Exception{

    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Exception ex) {
        super(message,ex);
    }
}
