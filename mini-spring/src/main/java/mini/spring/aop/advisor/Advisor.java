package mini.spring.aop.advisor;

import org.aopalliance.aop.Advice;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public interface Advisor {

    Advice getAdvice();

}
