/*
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener.event;


import java.util.EventObject;

/**
 * ApplicationEvent.
 *
 * @author Carl, 2023-08-18 11:47
 */
public class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }

}
