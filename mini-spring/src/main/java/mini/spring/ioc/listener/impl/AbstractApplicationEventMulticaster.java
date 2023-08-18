/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.listener.impl;


import mini.spring.ioc.listener.event.ApplicationEvent;
import mini.spring.ioc.listener.event.ApplicationEventMulticaster;
import mini.spring.ioc.listener.event.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractApplicationEventMulticaster.
 * 
 * @author Carl, 2023-08-18 13:10
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    @Override
    public void addListener(ApplicationListener applicationListener) {
        applicationListeners.add(applicationListener);
    }

    @Override
    public void removeListener(ApplicationListener applicationListener) {

    }
}
