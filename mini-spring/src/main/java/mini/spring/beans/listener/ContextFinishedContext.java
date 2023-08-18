/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener;


import mini.spring.beans.listener.event.ApplicationEvent;

/**
 * ContextFinishedContext.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-08-18 14:03
 */
public class ContextFinishedContext extends ApplicationEvent {
    public ContextFinishedContext(Object source) {
        super(source);
    }
}
