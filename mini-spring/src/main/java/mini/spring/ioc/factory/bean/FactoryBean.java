package mini.spring.ioc.factory.bean;

/**
 * @description: 主要用途是为了更复杂的逻辑类,例如Mybaits创建类
 * @author：carl
 * @date: 2023/8/17
 */
public interface FactoryBean<T> {

    T getObject();

    boolean isSingleton();
}
