package mini.spring.test.service;

import mini.spring.aop.advice.AfterAdvice;
import mini.spring.aop.advice.BeforeAdvice;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class HelloMethodAfterInterceptor implements AfterAdvice {

    @Override
    public Object afterAdvice(Object target) {
        System.out.println(target);
        System.out.println("after advice");
        return "advice";
    }
}
