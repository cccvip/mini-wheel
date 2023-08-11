/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.test.ioc;


import mini.spring.beans.factory.BeanFactory;
import org.junit.Test;

/**
 * SimpleBeanContainerTest.
 *
 * @author Carl, 2023-08-11 9:23
 * @version CrisisGo v1.0
 */
public class SimpleBeanContainerTest {

    @Test
    public void testGetBean() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("hello", new AppService());
        AppService appService = (AppService) beanFactory.getBean("hello");
        String pong = appService.ping();
        System.out.println("pong".equals(pong));
    }

    class AppService {

        public String ping() {
            return "pong";
        }

    }


}
