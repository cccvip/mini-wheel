/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener.impl;


import mini.spring.beans.listener.event.ApplicationEvent;
import mini.spring.beans.listener.event.ApplicationEventMulticaster;
import mini.spring.beans.listener.event.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractApplicationEventMulticaster.
 * 
 * @version CrisisGo v1.0
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
