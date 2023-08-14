/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.context;


import mini.spring.beans.factory.exception.BeanException;

/**
 * 读取配置文件,自动注入Bean
 * ConfigurableApplicationContext.
 *
 * @author Carl, 2023-08-14 10:55
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeanException;

}
