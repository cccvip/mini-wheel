/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener;


import mini.spring.beans.listener.event.ApplicationListener;

/**
 * ContextFinishedListener.
 *
 * @author Carl, 2023-08-18 14:05
 * @version CrisisGo v1.0
 */
public class ContextFinishedListener implements ApplicationListener<ContextFinishedContext> {

    @Override
    public void onApplicationContext(ContextFinishedContext event) {

        System.out.println("Spring finished");

    }
}
