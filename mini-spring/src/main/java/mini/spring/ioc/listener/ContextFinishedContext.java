/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.listener;


import mini.spring.ioc.listener.event.ApplicationEvent;

/**
 * ContextFinishedContext.
 * 
 * @author Carl, 2023-08-18 14:03
 */
public class ContextFinishedContext extends ApplicationEvent {
    public ContextFinishedContext(Object source) {
        super(source);
    }
}
