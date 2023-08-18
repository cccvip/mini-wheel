/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.listener;


import mini.spring.ioc.listener.event.ApplicationListener;

/**
 * ContextFinishedListener.
 *
 * @author Carl, 2023-08-18 14:05
 */
public class ContextFinishedListener implements ApplicationListener<ContextFinishedContext> {

    @Override
    public void onApplicationContext(ContextFinishedContext event) {

        System.out.println("Spring finished");

    }
}
