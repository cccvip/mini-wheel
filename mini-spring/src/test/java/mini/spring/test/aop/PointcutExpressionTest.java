package mini.spring.test.aop;

import mini.spring.aop.aspectj.AspectJExpressionPointcut;
import mini.spring.aop.aspectj.MethodMatcher;
import mini.spring.aop.proxy.CglibAopProxy;
import mini.spring.aop.proxy.JdkDynamicAopProxy;
import mini.spring.aop.support.AopAdvised;
import mini.spring.test.service.AppService;
import mini.spring.test.service.HelloInterceptor;
import mini.spring.test.service.HelloService;
import mini.spring.test.service.HelloServiceImpl;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class PointcutExpressionTest {

    @Test
    public void testExecute() {
        //AspectJ 框架使用
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* mini.spring.test.service.AppService.*(..))");
        Class<AppService> clazz = AppService.class;
        try {
            Method method = clazz.getDeclaredMethod("ping");
            System.out.println(pointcut.matches(clazz));
            System.out.println(pointcut.matches(method, clazz));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testJdkProxy() {
        HelloService worldService = new HelloServiceImpl();

        AopAdvised advisedSupport = new AopAdvised();

        HelloInterceptor methodInterceptor = new HelloInterceptor();

        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* mini.spring.test.service.HelloService.*(..))").getMethodMatcher();
        advisedSupport.setTarget(worldService);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        HelloService proxy = (HelloService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.hello();
    }


    @Test
    public void testCglibDynamicProxy() throws Exception {

        HelloService worldService = new HelloServiceImpl();

        AopAdvised advisedSupport = new AopAdvised();

        HelloInterceptor methodInterceptor = new HelloInterceptor();

        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* mini.spring.test.service.HelloService.*(..))").getMethodMatcher();
        advisedSupport.setTarget(worldService);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        HelloService proxy = (HelloService) new CglibAopProxy(advisedSupport).getProxy();

        proxy.hello();

    }


}
