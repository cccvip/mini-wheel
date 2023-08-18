/*
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener.event;


import mini.spring.beans.factory.exception.BeanException;

/**
 * ApplicationEventPublisher.
 *
 * @author Carl, 2023-08-18 11:48
 */
public interface ApplicationEventPublisher {

    void publishApplicationEvent(ApplicationEvent applicationEvent) throws BeanException;

}
