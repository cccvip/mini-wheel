package design.proxy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserDao {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring.xml");
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);
        userDao.selectUser("1111111");
    }
}
