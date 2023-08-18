package mini.spring.ioc.strategy.impl;

import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.strategy.InstantiationStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class CglibInstantiationStrategy implements InstantiationStrategy {
    /**
     * 使用cglib动态生成子类
     * @param beanDefinition
     * @return
     * @throws BeanException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        //回调处理
        enhancer.setCallback((MethodInterceptor)
                (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj,argsTemp));

        return enhancer.create();
    }
}
