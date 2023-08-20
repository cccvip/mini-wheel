package mini.spring.test.aop;

import mini.spring.aop.advice.adapter.MethodAfterAdviceInterceptor;
import mini.spring.aop.advice.adapter.MethodBeforeAdviceInterceptor;
import mini.spring.aop.advisor.AspectJExpressionPointcutAdvisor;
import mini.spring.aop.aspectj.AspectJExpressionPointcut;
import mini.spring.aop.aspectj.ClassFilter;
import mini.spring.aop.aspectj.MethodMatcher;
import mini.spring.aop.proxy.AopProxyFactory;
import mini.spring.aop.proxy.CglibAopProxy;
import mini.spring.aop.proxy.JdkDynamicAopProxy;
import mini.spring.aop.support.AopAdvised;
import mini.spring.test.service.*;
import org.aopalliance.intercept.MethodInterceptor;
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


    @Test
    public void testProxyFactory() throws Exception {

        HelloService worldService = new HelloServiceImpl();

        AopAdvised advisedSupport = new AopAdvised();

        HelloInterceptor methodInterceptor = new HelloInterceptor();

        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* mini.spring.test.service.HelloService.*(..))").getMethodMatcher();
        advisedSupport.setTarget(worldService);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setCglibProxy(true);

        AopProxyFactory aopProxyFactory = new AopProxyFactory(advisedSupport);

        HelloService proxy = (HelloService) aopProxyFactory.getProxy();

        proxy.hello();
    }


    @Test
    public void testMethodBefore() throws Exception {

        HelloService worldService = new HelloServiceImpl();

        AopAdvised advisedSupport = new AopAdvised();

        HelloMethodBeforeInterceptor beforeInterceptor = new HelloMethodBeforeInterceptor();

        MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(beforeInterceptor);

        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* mini.spring.test.service.HelloService.*(..))").getMethodMatcher();
        advisedSupport.setTarget(worldService);
        advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setCglibProxy(true);

        AopProxyFactory aopProxyFactory = new AopProxyFactory(advisedSupport);

        HelloService proxy = (HelloService) aopProxyFactory.getProxy();
        proxy.hello();
    }


    @Test
    public void testMethodAfter() throws Exception {

        HelloService worldService = new HelloServiceImpl();

        AopAdvised advisedSupport = new AopAdvised();

        HelloMethodAfterInterceptor afterInterceptor = new HelloMethodAfterInterceptor();

        MethodAfterAdviceInterceptor methodAfterAdviceInterceptor = new MethodAfterAdviceInterceptor(afterInterceptor);

        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* mini.spring.test.service.HelloService.*(..))").getMethodMatcher();
        advisedSupport.setTarget(worldService);
        advisedSupport.setMethodInterceptor(methodAfterAdviceInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setCglibProxy(true);

        AopProxyFactory aopProxyFactory = new AopProxyFactory(advisedSupport);
        HelloService proxy = (HelloService) aopProxyFactory.getProxy();
        String hello = proxy.hello();
        System.out.println(hello);
    }

    @Test
    public void testPointCutExpression() throws Exception {

        HelloService helloService = new HelloServiceImpl();

        HelloMethodAfterInterceptor afterInterceptor = new HelloMethodAfterInterceptor();
        MethodAfterAdviceInterceptor methodAfterAdviceInterceptor = new MethodAfterAdviceInterceptor(afterInterceptor);

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* mini.spring.test.service.HelloService.*(..))");
        advisor.setAdvice(methodAfterAdviceInterceptor);

        ClassFilter classFilter = advisor.getPointCut().getClassFilter();
        if (classFilter.matches(helloService.getClass())) {

            AopAdvised advised = new AopAdvised();
            advised.setTarget(helloService);
            advised.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advised.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            advised.setCglibProxy(true);   //JDK or CGLIB
            HelloService proxy = (HelloService) new AopProxyFactory(advised).getProxy();
            System.out.println(proxy.hello());
        }
    }


}
