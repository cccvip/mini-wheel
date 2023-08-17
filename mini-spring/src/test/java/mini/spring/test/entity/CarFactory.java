package mini.spring.test.entity;

import mini.spring.beans.factory.bean.FactoryBean;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class CarFactory implements FactoryBean<Car> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Car getObject() {
        return new Car(name);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
