/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener.impl;


import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.listener.event.ApplicationEvent;
import mini.spring.beans.listener.event.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * SimpleApplicationEventMulticaster.
 *
 * @author Carl, 2023-08-18 13:36
 * @version CrisisGo v1.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @Override
    public void multiEvent(ApplicationEvent event) throws BeanException {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationContext(event);
            }
        }
    }
    /**
     * supportsEvent.
     * 判断当前Event是否对该Listener感兴趣
     * @param applicationListener
     * @param event
     * @return boolean
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) throws BeanException {
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeanException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }


}
