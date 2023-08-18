/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.listener.event;


import mini.spring.beans.factory.exception.BeanException;

/**
 * ApplicationEventMulticaster.
 * 事件监听机制的接口
 * 1 添加Listener
 * 2 移除Listener
 * 3 主动触发事件
 *
 * @author Carl, 2023-08-18 12:19
 */
public interface ApplicationEventMulticaster {
    void addListener(ApplicationListener applicationListener);

    void removeListener(ApplicationListener applicationListener);

    void multiEvent(ApplicationEvent applicationEvent) throws BeanException;
}
