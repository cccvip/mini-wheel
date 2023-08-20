package mini.spring.aop.advisor;

import mini.spring.aop.aspectj.ClassFilter;
import mini.spring.aop.aspectj.PointCut;
import mini.spring.aop.bean.InstantiationAwareBeanPostProcessor;
import mini.spring.aop.proxy.AopProxyFactory;
import mini.spring.aop.support.AopAdvised;
import mini.spring.ioc.factory.aware.BeanFactoryAware;
import mini.spring.ioc.factory.config.BeanFactory;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private Set<Object> earlyProxyReferences = new HashSet<>();

    @Override
    public Object postProcessBeforeInstantiation(Class bean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object getEarlyBeanReference(Object exposedObject, String beanName) throws BeanException {
        earlyProxyReferences.add(exposedObject);
        return wrapIfNecessary(exposedObject, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeanException {
        return true;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || PointCut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }


    @Override
    public Object postProcessBeforeInitialization(String beanName, Object object) throws BeanException {
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) throws BeanException {
        if (!earlyProxyReferences.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }

        return bean;
    }

    protected Object wrapIfNecessary(Object bean, String beanName) throws BeanException {
        //避免死循环
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        try {
            for (AspectJExpressionPointcutAdvisor advisor : advisors) {
                ClassFilter classFilter = advisor.getPointCut().getClassFilter();

                if (classFilter.matches(bean.getClass())) {
                    AopAdvised advisedSupport = new AopAdvised();
                    advisedSupport.setTarget(bean);
                    advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                    advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
                    advisedSupport.setCglibProxy(true);
                    return new AopProxyFactory(advisedSupport).getProxy();
                }
            }
        } catch (Exception ex) {
            throw new BeanException("Error create proxy bean for: " + beanName, ex);
        }

        return bean;
    }

}
