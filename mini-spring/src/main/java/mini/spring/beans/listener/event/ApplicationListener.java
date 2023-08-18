/*
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener.event;


import java.util.EventListener;

/**
 * ApplicationListener.
 * 观察者模式中的Listner
 *
 * @author Carl, 2023-08-18 11:49
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    void onApplicationContext(T event);

}
