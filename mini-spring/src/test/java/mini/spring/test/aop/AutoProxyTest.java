package mini.spring.test.aop;

import mini.spring.ioc.context.ClassPathXmlApplicationContext;
import mini.spring.ioc.resources.Resources;
import mini.spring.ioc.resources.impl.DefaultResourceLoader;
import mini.spring.test.service.A;
import mini.spring.test.service.B;
import mini.spring.test.service.HelloService;
import org.junit.Test;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public class AutoProxyTest {

    @Test
    public void testAutoProxy() throws Exception {
        //当前最小版本只使用了无参构造函数

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(resource);

        //获取代理对象
        HelloService worldService = applicationContext.getBean("helloService", HelloService.class);

        System.out.println(worldService.hello());
    }


    @Test
    public void testCircularReference() throws Exception {

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("circular-reference-without-proxy-bean.xml");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(resource);
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void testCircularWithProxyReference() throws Exception {

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("circular-reference-proxy-bean.xml");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(resource);
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        System.out.println(a.getB());
        System.out.println(b);
    }


}
