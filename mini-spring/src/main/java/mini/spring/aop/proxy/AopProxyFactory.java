package mini.spring.aop.proxy;

import mini.spring.aop.support.AopAdvised;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public class AopProxyFactory {

    AopAdvised aopAdvised;

    public AopProxyFactory(AopAdvised aopAdvised) {
        this.aopAdvised = aopAdvised;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {

        if (aopAdvised.isCglibProxy()) {
            return new CglibAopProxy(aopAdvised);
        }
        return new JdkDynamicAopProxy(aopAdvised);
    }

}
